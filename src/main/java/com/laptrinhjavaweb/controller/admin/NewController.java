package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.paging.PageRequest;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.sort.Sorter;
import com.laptrinhjavaweb.utils.FormUtils;

@WebServlet(urlPatterns = { "/admin-news" })
public class NewController extends HttpServlet {

	private static final long serialVersionUID = 3026486002489701343L;

	@Inject
	private INewService iNewService;
	
	@Inject 
	ICategoryService iCategoryService;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		NewModel newModel = FormUtils.toModel(NewModel.class, req);
		String view = "";
		if (newModel.getType().equalsIgnoreCase(SystemConstant.PAGE_TYPE_LIST)) {
			Sorter sorter = new Sorter(newModel.getSortName(), newModel.getSortBy());
			PageRequest pageRequest = new PageRequest(newModel.getPage(), newModel.getPageItem(), sorter);
			newModel.setList(iNewService.findAll(pageRequest));
			newModel.setTotalItem(iNewService.count());
			newModel.setTotalPage(newModel.getTotalItem(), newModel.getPageItem());
			view = "/views/admin/new/list.jsp";
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if(message != null && alert != null) {
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			req.setAttribute(SystemConstant.MODEL, newModel);
		} else if (newModel.getType().equalsIgnoreCase(SystemConstant.PAGE_TYPE_EDIT)) {
			if (newModel.getId() != null) {
				newModel = iNewService.findOneById(newModel.getId());
			}
			String message = req.getParameter("message");
			String alert = req.getParameter("alert");
			if(message != null && alert != null) {
				req.setAttribute("message", resourceBundle.getString(message));
				req.setAttribute("alert", alert);
			}
			req.setAttribute("categories", iCategoryService.findAll());
			view = "/views/admin/new/edit.jsp";
		}
		req.setAttribute(SystemConstant.MODEL, newModel);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	}
}
