package com.cdi.websocket.server;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;
 
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.json.JSONException;
import org.json.JSONObject;

import com.cdi.web.enums.CommadEnume;
import com.cdi.web.utils.ScentysUtil;



 
//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。
@ServerEndpoint("/websocket/pad")
public class WebSocketForPad {
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
     
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识
    private static CopyOnWriteArraySet<WebSocketForPad> webSocketSet = new CopyOnWriteArraySet<WebSocketForPad>();
     
    //与某个客户端的连接会话，需要通过它来给客户端发送数据
    private Session session;
    
    private String id;
    /**
     * 连接建立成功调用的方法
     * @param session  可选的参数。session为与某个客户端的连接会话，需要通过它来给客户端发送数据
     */
    
    @OnOpen
    public void onOpen(Session session){
    	addOnlineCount();           //在线数加1
    	if(session.getRequestParameterMap().get("id") != null) {
    		this.id = session.getRequestParameterMap().get("id").get(0);
    	} else {
    		try {
				session.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		return;
    	}
    	
        this.session = session;
        webSocketSet.add(this);     //加入set中
        System.out.println("有新连接加入！当前在线人数为" + getOnlineCount()+" 新连接属性："+ this.id);
    }
     
    /**
     * 连接关闭调用的方法
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //从set中删除
        subOnlineCount();           //在线数减1    
        System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());
    }
     
    /**
     * 收到客户端消息后调用的方法
     * @param message 客户端发送过来的消息
     * @param session 可选的参数
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("来自客户端的消息:" + message + " 现有连接数量：" + webSocketSet.size());
        try {
			JSONObject json = new JSONObject(message);
			int cmd = json.getInt("cmd");
			CommadEnume commadEnume = CommadEnume.parse(cmd);
			//运行香氛
			switch (commadEnume) {
			case PlayScentys:
				int scentyType = json.getJSONObject("message").getInt("scentyType");
				if(scentyType >= 0 && scentyType <= 4) {
					boolean flag = ScentysUtil.sendCommand("PlayFragrance " + scentyType, "localhost");
					System.out.println(flag ? "成功有运行香氛" : "香氛连接错误");
				} else {
					System.out.println("scentyType值错误,值为" + scentyType);
				}
				break;
			case ScentysIntensity :
				int scentyIntensity = json.getJSONObject("message").getInt("scentyIntensity");
				if(scentyIntensity >= 1 && scentyIntensity <= 6) {
					boolean flag = ScentysUtil.sendCommand("SetBlower " + scentyIntensity, "localhost");
					System.out.println(flag ? "成功有设置香氛强度" : "香氛连接错误");
				} else {
					System.out.println("scentyIntensity值错误,值为" + scentyIntensity);
				}
				break;
			default:
				break;
			}
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
        
        //群发消息
        for(WebSocketForPad item: webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
     
    /**
     * 发生错误时调用
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("发生错误");
        error.printStackTrace();
    }
     
    /**
     * 这个方法与上面几个方法不一样。没有用注解，是根据自己需要添加的方法。
     * @param message
     * @throws IOException
     */
    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

    public static void sendMessageTo(String message,String toList) {
      System.out.println("发送消息: " + message + " 发送列表： " + toList);
      for(WebSocketForPad item: webSocketSet) {
    	  if(!toList.contains(item.getId()))
      		continue;
          try {
              item.sendMessage(message);
          } catch (IOException e) {
              e.printStackTrace();
              continue;
          }
      }
    }
    public static synchronized int getOnlineCount() {
        return onlineCount;
    }
 
    public static synchronized void addOnlineCount() {
        WebSocketForPad.onlineCount++;
    }
     
    public static synchronized void subOnlineCount() {
        WebSocketForPad.onlineCount--;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
//    public void handle(int cmd,String message) {
//    	CommadEnume commadEnume = CommadEnume.parse(cmd);
//    	switch (commadEnume) {
//		case M1: case M2: case M3: case M4: case M5: case M6: case M7: case M8:
//			System.out.println("音乐相关类命令，命令内容:" + message);
//			sendMessageTo(message,"music");
//			break;
//		case R1: case R2: case R3: case R4: case R5: case R6:
//			System.out.println("电台相关类命令，命令内容:" + message);
//			sendMessageTo(message,"radio");
//			break;
//		default:
//			break;
//		}
//    	
//    }
    
}
