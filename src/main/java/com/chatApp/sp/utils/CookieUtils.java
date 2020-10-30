package com.chatApp.sp.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

@Component
public class CookieUtils {
	
	public String getEmail(HttpServletRequest req) {
		Cookie cookies[] = req.getCookies();
		
		for(Cookie c: cookies) {
			
			if(c.getName().equals("email")) {
				return c.getValue();
			}
		}
		
		return null;
	}
	
	public Cookie setCookie(String name, String value, boolean httpOnly, int age) {
		Cookie c = new Cookie(name, value);
		c.setHttpOnly(httpOnly);
		c.setMaxAge(age);
		return c;
	}
}
