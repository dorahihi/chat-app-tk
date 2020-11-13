package com.chatApp.sp.authservice.service;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseCookie.ResponseCookieBuilder;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenAuthenticationService {
	
	static final long EXPIRATION_TIME = 864000000;
	
	static final String SECRET_KEY = "ThisIsSecretKey";
	
	static final String TOKEN_PREFIX = "Bearer";
	
	static final String HEADER_STRING = "Authorization";
	
	
	// add "authorization" to response header
	public static void addAuthentication(HttpServletResponse res, String username) {
		
		String JWT = Jwts.builder().setSubject(username)
						.setExpiration(new Date(System.currentTimeMillis()+ EXPIRATION_TIME))
						.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
						.compact();

		
		res.addHeader(HEADER_STRING, TOKEN_PREFIX + " " + JWT);
		
		
		/*Cookie cookie = new Cookie(HEADER_STRING, TOKEN_PREFIX+JWT);
		cookie.setMaxAge(24*2*60*60);
		cookie.setHttpOnly(true);
		//res.addCookie(cookie);*/
		
		/*Cookie email = new Cookie("email", username);
		email.setMaxAge(24*2*60*60);;
		//email.setHttpOnly(true);
		res.addCookie(email);*/
		
		System.out.println(HEADER_STRING + " " + JWT);
	}
	
	public static Authentication getAuthentication(HttpServletRequest req, HttpServletResponse res) {
		
		 
	/*Cookie cookies[] = req.getCookies();
	
	String token = null;
	
	if(cookies != null) {
		for(Cookie c: cookies) {
			String name = c.getName();
			if(name.equals(HEADER_STRING))
				token = c.getValue();
		}
	}
	
		//String token = req.getHeader(HEADER_STRING);
		
	if(req.getHeader("logout") != null) {
		System.out.println("logout from req: "+ req.getHeader("logout"));
		Cookie c = new Cookie(HEADER_STRING, "");
		c.setMaxAge(0);
		c.setHttpOnly(true);
		res.addCookie(c);
		
		Cookie email = new Cookie("email", "");
		email.setMaxAge(0);
		email.setHttpOnly(true);
		res.addCookie(email);
		
	}*/
		
		String token  = req.getHeader(HEADER_STRING);
	
		if(token != null) {
			String user = Jwts.parser().setSigningKey(SECRET_KEY)
								.parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody()
								.getSubject();
			//res.addHeader("email", user);
			return (user != null)? new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList()): null;
		}
		
		return null;
	}

}
