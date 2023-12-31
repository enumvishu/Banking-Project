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

@WebServlet("/withdraw")
public class Withdraw extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		double amount = Double.parseDouble(req.getParameter("amount"));

		long acno = (long) req.getSession().getAttribute("acno");

		Bank_Dao bankdao = new Bank_Dao();

		BankAccount bankaccount = bankdao.fetchByAccno(acno);

		if (bankaccount.getAmount() < amount) {
			res.getWriter().print("<h1>Insufficient Balance. Your actual balance is</h1>" + bankaccount.getAmount());

		} else if (amount > bankaccount.getAccountLimit()) {
			res.getWriter().print("<h1>Limit Excedded. Your Actual limit is </h1>" + bankaccount.getAccountLimit());

		} else {
			Transaction transaction = new Transaction();

			bankaccount.setAmount(bankaccount.getAmount() - amount); // before putting any data inside database we should set the data

			transaction.setBalance(bankaccount.getAmount());
			transaction.setDeposit(0);
			transaction.setLocalDateTime(LocalDateTime.now());
			transaction.setWithdrow(amount);

			bankdao.update(bankaccount);

			List<Transaction> list = bankaccount.getBankTransactions();
			list.add(transaction);

			bankdao.update(bankaccount);

			res.getWriter().print("<h1>Amount has been Withdrawn succesfully. Paisa gaya tumhara</h1>");
			res.getWriter().print("<h1>Updated Balance " + bankdao.fetchByAccno(acno).getAmount() + "</h1>");

		}
	}
}
