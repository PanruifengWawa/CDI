package com.cdi.web.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.cdi.web.enums.ErrorCodeEnum;
import com.cdi.web.service.ScentysService;
import com.cdi.web.utils.DataWrapper;
import com.cdi.web.utils.ScentysUtil;

@Service("scentysService")
public class ScentysServiceImpl implements ScentysService{

	@Override
	public DataWrapper<Void> stop(String token, HttpServletRequest request) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		if(token.equals("testToken")) {
			String command = "PlayFragrance 0";
			String host = request.getServletContext().getInitParameter("scentys-host");
			ScentysUtil.sendCommand(command, host);
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> play(int type, String token, HttpServletRequest request) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		if(token.equals("testToken") && type >= 1 && type <= 4) {
			String command = "PlayFragrance " + type;
			String host = request.getServletContext().getInitParameter("scentys-host");
			ScentysUtil.sendCommand(command, host);
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

	@Override
	public DataWrapper<Void> setBlower(int type, String token, HttpServletRequest request) {
		// TODO Auto-generated method stub
		DataWrapper<Void> dataWrapper = new DataWrapper<Void>();
		if(token.equals("testToken") && type >= 1 && type <= 6) {
			String command = "SetBlower " + type;
			String host = request.getServletContext().getInitParameter("scentys-host");
			ScentysUtil.sendCommand(command, host);
		} else {
			dataWrapper.setErrorCode(ErrorCodeEnum.Error);
		}
		return dataWrapper;
	}

}
