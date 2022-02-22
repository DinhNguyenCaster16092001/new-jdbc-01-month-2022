package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IUserService;

@WebServlet(urlPatterns = "/api-admin-user")
public class UserAPI extends HttpServlet{
	
	private static final long serialVersionUID = 1L;

	@Inject
	private IUserService iUserService;
	
	
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		UserModel userModel = mapper.readValue(req.getInputStream(), UserModel.class);
		userModel = iUserService.save(userModel);
		//return to client JSON type
		mapper.writeValue(resp.getOutputStream(), userModel);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
}
