package com.laptrinhjavaweb.controller.web;

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
import com.laptrinhjavaweb.model.CategoryModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.ICategoryService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtils;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/trang-chu", "/home", "/dang-nhap","/dang-xuat"})
public class HomeController extends HttpServlet {

	private static final long serialVersionUID = 2686801510274002166L;

	@Inject
	private ICategoryService icategoryService;

	@Inject
	private IUserService iUserService;
	
	ResourceBundle resourceBundle = ResourceBundle.getBundle("message");

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action != null && action.equals("login")) {
			String message = request.getParameter("message");
			String alert = request.getParameter("alert");
			if(message != null && alert != null) {
				request.setAttribute("message", resourceBundle.getString(message));
				request.setAttribute("alert", alert);
			}
			RequestDispatcher rd = request.getRequestDispatcher("/views/login.jsp");
			rd.forward(request, response);
		} else if (action != null && action.equals("logout")) {
			SessionUtil.getInstance().removeValue(request, "USERMODEL");
			response.sendRedirect(request.getContextPath()+"/trang-chu");
		} else {
			CategoryModel categoryModel = new CategoryModel();
			categoryModel.setList(icategoryService.findAll());
			request.setAttribute(SystemConstant.MODEL, categoryModel);
			RequestDispatcher rd = request.getRequestDispatcher("/views/web/home.jsp");
			rd.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action != null && action.equals("login")) {
			UserModel userModel = FormUtils.toModel(UserModel.class, req);
			userModel = iUserService.findOneByUserNameAndPassword(userModel.getUserName(), userModel.getPassWord());
			if (userModel != null) {
				SessionUtil.getInstance().puValue(req, "USERMODEL", userModel);
				if (userModel.getRole().getCode().equalsIgnoreCase("User")) {
					resp.sendRedirect(req.getContextPath() + "/trang-chu");
				} else if (userModel.getRole().getCode().equalsIgnoreCase("ADMIN")) {
					resp.sendRedirect(req.getContextPath() + "/admin-home");
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&alert=danger&message=user_password_invalid");
			}
		}
	}
}
