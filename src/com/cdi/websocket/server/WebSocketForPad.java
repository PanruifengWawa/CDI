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
				// TODO Auto-generated catch block
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
        //群发消息
        JSONObject json = null;
        Integer cmd = null;
        try {
			json = new JSONObject(message);
			cmd = json.getInt("cmd");
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return;
		}
        String toList = CommadEnume.parse(cmd);
        System.out.println("发送列表： " + toList);
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
    
    
}
