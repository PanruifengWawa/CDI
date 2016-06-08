package com.cdi.web.service;

import javax.servlet.http.HttpServletRequest;

import com.cdi.web.utils.DataWrapper;

public interface ScentysService {
	DataWrapper<Void> stop(String token,HttpServletRequest request);
	DataWrapper<Void> play(int type,String token,HttpServletRequest request);
	DataWrapper<Void> setBlower(int type,String token,HttpServletRequest request);
}
