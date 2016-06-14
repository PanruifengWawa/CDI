package com.cdi.web.controller;

import java.io.IOException;
import java.net.URI;

import javax.websocket.ContainerProvider;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdi.websocket.client.MyClient;

@Controller
@RequestMapping(value="api/sandybox")
public class SandyBoxController {
	@RequestMapping(value="getData",method= RequestMethod.GET)
	@ResponseBody
	public String getData(
			@RequestParam(value = "speed", required = true) String speed,
			@RequestParam(value = "direction", required = true) String direction,
			@RequestParam(value = "distance", required = true) String distance,
			@RequestParam(value = "play", required = true) String play
	) throws IOException, JSONException {
		JSONObject carData = new JSONObject();
		carData.put("speed", speed);
		carData.put("direction", direction);
		carData.put("distance", distance);
		carData.put("play", play);
		JSONObject message = new JSONObject();
		message.put("carData", carData);
		JSONObject data = new JSONObject();
		data.put("cmd", 45);
		data.put("message", message);

		WebSocketContainer container = ContainerProvider.getWebSocketContainer();  
	  	   
	    String uri = "ws://localhost:8080/CDI/websocket/pad?id=web";  
	    System.out.println("Connecting to " + uri);  
	    Session session = null;
	    try {  
	    	session = container.connectToServer(MyClient.class, URI.create(uri));  
	    } catch (Exception e) {  
	    	e.printStackTrace();  
	    }
	    session.getBasicRemote().sendText(data.toString());
	    session.close();
	    
		return data.toString();
	 }
}
