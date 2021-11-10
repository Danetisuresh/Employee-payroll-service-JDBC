package com.bridgelabz;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmployeePayrollService {
	public static void main(String[] args) {
		EmployeePayrollService service = new EmployeePayrollService();
		service.getConnection();
	}

	private void getConnection() {
		String url = "jdbc:mysql://127.0.0.1:3306/ payroll_service";
		String username = "root";
		String password = "root";
		Connection connection = null;
		try {

			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
