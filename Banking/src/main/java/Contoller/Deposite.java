package Contoller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.Bank_Dao;
import DTO.BankAccount;
import DTO.Transaction;

@WebServlet("/deposit")
public class Deposite extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		double amount = Double.parseDouble(req.getParameter("amount"));

		long acno = (long) req.getSession().getAttribute("acno");

		Bank_Dao bankdao = new Bank_Dao();

		BankAccount bankaccount = bankdao.fetchByAccno(acno);

		bankaccount.setAmount(bankaccount.getAmount() + amount); // before putting any data inside database we should set the data

		bankdao.update(bankaccount);

		Transaction transaction = new Transaction();
		transaction.setBalance(bankaccount.getAmount());
		transaction.setDeposit(amount);
		transaction.setLocalDateTime(LocalDateTime.now());
		transaction.setWithdrow(0);

		List<Transaction> list = bankaccount.getBankTransactions();
		list.add(transaction);

		res.getWriter().print("<h1>Amount has been deposited succesfully</h1>");
		res.getWriter().print("<h1>Updated Balance " + bankdao.fetchByAccno(acno).getAmount() + "</h1>");

	}

}
