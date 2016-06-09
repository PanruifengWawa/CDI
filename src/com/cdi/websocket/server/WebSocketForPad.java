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

 
//��ע������ָ��һ��URI���ͻ��˿���ͨ�����URI�����ӵ�WebSocket������Servlet��ע��mapping��������web.xml�����á�
@ServerEndpoint("/websocket/pad")
public class WebSocketForPad {
    //��̬������������¼��ǰ������������Ӧ�ð�����Ƴ��̰߳�ȫ�ġ�
    private static int onlineCount = 0;
     
    //concurrent�����̰߳�ȫSet���������ÿ���ͻ��˶�Ӧ��MyWebSocket������Ҫʵ�ַ�����뵥һ�ͻ���ͨ�ŵĻ�������ʹ��Map����ţ�����Key����Ϊ�û���ʶ
    private static CopyOnWriteArraySet<WebSocketForPad> webSocketSet = new CopyOnWriteArraySet<WebSocketForPad>();
     
    //��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
    private Session session;
    
    private String id;
    /**
     * ���ӽ����ɹ����õķ���
     * @param session  ��ѡ�Ĳ�����sessionΪ��ĳ���ͻ��˵����ӻỰ����Ҫͨ���������ͻ��˷�������
     */
    
    @OnOpen
    public void onOpen(Session session){
    	addOnlineCount();           //��������1
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
        webSocketSet.add(this);     //����set��
        System.out.println("�������Ӽ��룡��ǰ��������Ϊ" + getOnlineCount()+" ���������ԣ�"+ this.id);
    }
     
    /**
     * ���ӹرյ��õķ���
     */
    @OnClose
    public void onClose() {
        webSocketSet.remove(this);  //��set��ɾ��
        subOnlineCount();           //��������1    
        System.out.println("��һ���ӹرգ���ǰ��������Ϊ" + getOnlineCount());
    }
     
    /**
     * �յ��ͻ�����Ϣ����õķ���
     * @param message �ͻ��˷��͹�������Ϣ
     * @param session ��ѡ�Ĳ���
     */
    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("���Կͻ��˵���Ϣ:" + message + " ��������������" + webSocketSet.size());
        //Ⱥ����Ϣ
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
        System.out.println("�����б��� " + toList);
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
     * ��������ʱ����
     * @param session
     * @param error
     */
    @OnError
    public void onError(Session session, Throwable error){
        System.out.println("��������");
        error.printStackTrace();
    }
     
    /**
     * ������������漸��������һ����û����ע�⣬�Ǹ����Լ���Ҫ���ӵķ�����
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