package com.laptrinhjavaweb.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtil {
	private static final Integer ONE_YEAR_LIFE = 60 * 60 * 24 * 365 * 10;
	
	Cookie cookie = null;
    Cookie[] cookies = null;
	
	public void putValue(HttpServletResponse response, String key, String value) {
		try {
			cookie = new Cookie(key, URLEncoder.encode(value, "UTF-8"));
			cookie.setMaxAge(ONE_YEAR_LIFE);
			cookie.setVersion(1);
			response.addCookie(cookie);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}
	
	
	public String getValue(HttpServletRequest request, String key) {
		  Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if (cookie.getName().equals(key)) {
	                	try {
							return URLDecoder.decode(cookie.getValue(), "UTF-8");
						} catch (UnsupportedEncodingException e) {
							e.printStackTrace();
						}
	                }
	            }
	        }
			return null;
	}
	
	public void removeValue(HttpServletResponse response,HttpServletRequest request, String key) {
		Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(key)) {
                	cookie.setValue("");
                	cookie.setMaxAge(0);
                	response.addCookie(cookie);
                }
            }
        }
	}
}
