package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.codehaus.jackson.map.ObjectMapper;

import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/api-admin-news"})
public class NewAPI extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private INewService iNewService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		NewModel newModel = mapper.readValue(req.getInputStream(), NewModel.class);
		newModel.setCreatedBy(((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		newModel = iNewService.save(newModel);
		//return to client JSON type
		mapper.writeValue(resp.getOutputStream(), newModel);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		NewModel newModel = mapper.readValue(req.getInputStream(), NewModel.class);
		newModel.setModifiedBy(((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		newModel = iNewService.update(newModel);
		//return to client JSON type
		mapper.writeValue(resp.getOutputStream(), newModel);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		ObjectMapper mapper = new ObjectMapper();
		NewModel newModel = mapper.readValue(req.getInputStream(), NewModel.class);
		iNewService.delete(newModel.getIds());
		mapper.writeValue(resp.getOutputStream(), "{}");
	}
}
