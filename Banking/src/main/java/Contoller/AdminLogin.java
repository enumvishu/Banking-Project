package Contoller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Bank_Dao;
import DTO.BankAccount;

@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		if (req.getParameter("adminmail").equals("admin@gmail.com") && req.getParameter("adminpwd").equals("admin")) {
			res.getWriter().print("<h1>Welcome to Admin Login Page<h1>");

			Bank_Dao bankdao = new Bank_Dao();

			List<BankAccount> list = bankdao.fetchAllBank();
			req.getSession().setAttribute("list", list);
			req.getRequestDispatcher("Account_Home.jsp").include(req, res);

		} else {
			res.getWriter().print("<h1>Invalid Credentials<h1>");
			req.getRequestDispatcher("admin.html").include(req, res);

		}

	}

}
