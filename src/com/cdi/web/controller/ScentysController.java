package com.cdi.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cdi.web.service.ScentysService;
import com.cdi.web.utils.DataWrapper;

@Controller
@RequestMapping(value="api/scentys")
public class ScentysController {
	@Autowired
	ScentysService scentysService;
	
	@RequestMapping(value="stop",method= RequestMethod.GET)
    @ResponseBody
    public DataWrapper<Void> stop(
    		@RequestParam(value = "token", required = true) String token,
    		HttpServletRequest request
    ) {
    	return scentysService.stop(token,request);
    }
	
	@RequestMapping(value="play",method= RequestMethod.GET)
    @ResponseBody
    public DataWrapper<Void> play(
    		@RequestParam(value = "type", required = true) int type,
    		@RequestParam(value = "token", required = true) String token,
    		HttpServletRequest request
    ) {
		return scentysService.play(type, token, request);
    }
	
	@RequestMapping(value="setBlower",method= RequestMethod.GET)
    @ResponseBody
    public DataWrapper<Void> setBlower(
    		@RequestParam(value = "type", required = true) int type,
    		@RequestParam(value = "token", required = true) String token,
    		HttpServletRequest request
    ) {
		return scentysService.setBlower(type, token, request);
    }
}
