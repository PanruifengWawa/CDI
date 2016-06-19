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



 
@ServerEndpoint("/websocket/pad")
public class WebSocketForPad {
    private static int onlineCount = 0;
     
    private static CopyOnWriteArraySet<WebSocketForPad> webSocketSet = new CopyOnWriteArraySet<WebSocketForPad>();
     
    private Session session;
    
    private String id;
    
    @OnOpen
    public void onOpen(Session session){
    	addOnlineCount();           
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
        webSocketSet.add(this);     
        System.out.println("New connection！id："+ this.id + ".Current connection number:" + getOnlineCount());
    }
     
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  
        subOnlineCount();
        System.out.println("One connection closed！id："+ this.id + ".Current connection number:" + getOnlineCount());
    }
     
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Message from client " + this.id + ":" + message + ".Current connection number:" + webSocketSet.size());
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
					System.out.println(flag ? "Succeed in playing fragrance!" : "Fragrance connection error!");
					if(!flag) return;
				} else {
					System.out.println("ScentyType error :" + scentyType);
					return;
				}
				break;
			case ScentysIntensity :
				int scentyIntensity = json.getJSONObject("message").getInt("scentyIntensity");
				if(scentyIntensity >= 1 && scentyIntensity <= 6) {
					boolean flag = ScentysUtil.sendCommand("SetBlower " + scentyIntensity, "localhost");
					System.out.println(flag ? "Succeed in setting blower!" : "Fragrance connection error!");
					if(!flag) return;
				} else {
					System.out.println("ScentyIntensity error :" + scentyIntensity);
					return;
				}
				break;
			default:
				break;
			}
		} catch (JSONException e1) {
			e1.printStackTrace();
			return;
		}
        
        for(WebSocketForPad item: webSocketSet) {
            try {
                item.sendMessage(message);
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }
        }
    }
     
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("Conntion Error!");
        error.printStackTrace();
    }
     

    public void sendMessage(String message) throws IOException{
        this.session.getBasicRemote().sendText(message);
        //this.session.getAsyncRemote().sendText(message);
    }

//    public static void sendMessageTo(String message,String toList) {
//      System.out.println("发送消息: " + message + " 发送列表： " + toList);
//      for(WebSocketForPad item: webSocketSet) {
//    	  if(!toList.contains(item.getId()))
//      		continue;
//          try {
//              item.sendMessage(message);
//          } catch (IOException e) {
//              e.printStackTrace();
//              continue;
//          }
//      }
//    }
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
