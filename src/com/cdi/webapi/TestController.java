package com.cdi.webapi;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value="api/")
public class TestController {

    @RequestMapping(value="test",method= RequestMethod.GET)
    @ResponseBody
    public void login(

            @RequestParam(value = "photo", required = true) String photo
    ) throws IOException {
    	
    	WebSocketContainer container = ContainerProvider.getWebSocketContainer();  
    	   
        String uri = "ws://localhost:8080/Soecket/websocket/webservice";  
        System.out.println("Connecting to " + uri);  
        Session session = null;
        try {  
        	session = container.connectToServer(MyClient.class, URI.create(uri));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }
        session.getBasicRemote().sendText(photo);
        session.close();
    }
}
