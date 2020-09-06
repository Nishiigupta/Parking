package xyz.willz.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import xyz.willz.dao.LoginDao;


@WebServlet("/login")
public class Login extends HttpServlet {
    
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("In Login");
		String buyer_seller = request.getParameter("buyer_seller");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		LoginDao dao = new LoginDao();
		Integer id = dao.valid(username, password, buyer_seller);
		System.out.println(id);
		if(id != null) {
			HttpSession session = request.getSession();
			
			session.setAttribute("id", id);
			
			if(buyer_seller.equals("seller")) {	
				System.out.println("A Seller");
				session.setAttribute("admin", username);
				session.removeAttribute("user");
				response.sendRedirect("admin");
				return;
			} else {
				System.out.println("A Customer");
				session.setAttribute("user", username);
				session.removeAttribute("admin");
				response.sendRedirect("index.jsp");
			}
			
			return;
		}
		
		response.sendRedirect("login.jsp");
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.sendRedirect("login.jsp");
	}

}
