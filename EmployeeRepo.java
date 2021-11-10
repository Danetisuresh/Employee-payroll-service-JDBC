package com.bridgelabz;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class EmployeeRepo {
	Connection connection;

	private Connection getConnection() {
		String url = "jdbc:mysql://127.0.0.1:3306/payroll_service";
		String username = "root";
		String password = "rootpassword";
		Connection connection = null;
		try {

			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

			connection = DriverManager.getConnection(url, username, password);
			System.out.println("Connection Established");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public void insertData(EmployeeInfo info) {
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			String sql = "insert into payroll_service(name,gender,startDate,phone,address)" +
					"values('" + info.getName() + "','" + info.getGender() + "','" + info.getStartDate() + "','" + info.getPhone() + "','" + info.getAddress() + "');";

			int result = statement.executeUpdate(sql);
			if (result == 1) {
				System.out.println("Insert Query");
			} else System.out.println("Data Not inserted");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<EmployeeInfo> retrieveData() {
		ResultSet resultSet = null;
		List<EmployeeInfo> employeeInfoList = new ArrayList<EmployeeInfo>();
		try (Connection connection = getConnection()) {
			Statement statement = connection.createStatement();
			String sql = "select * from employees_Payroll";
			resultSet = statement.executeQuery(sql);
			int count = 0;
			while (resultSet.next()) {
				EmployeeInfo employeeInfo = new EmployeeInfo();
				employeeInfo.setId(resultSet.getInt("id"));
				employeeInfo.setName(resultSet.getString("name"));
				employeeInfo.setGender(resultSet.getString("gender").charAt(0));
				employeeInfo.setStartDate(resultSet.getDate("startDate").toLocalDate());
				employeeInfo.setPhone(resultSet.getString("phone"));
				employeeInfo.setAddress(resultSet.getString("address"));
				employeeInfoList.add(employeeInfo);
			}
		} catch (SQLException e) {
			System.out.println(e);
		}
		return employeeInfoList;
	}

}
