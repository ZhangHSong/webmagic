package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dao.MainInformationDao;
import entity.MainInformation;

public class MainInformationDaoImpl implements MainInformationDao {

	private Connection conn = null;
	private Statement stmt = null;

	public MainInformationDaoImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/webmagic?user=root&password=";
			conn = DriverManager.getConnection(url);
			stmt = conn.createStatement();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public int addMainInformation(MainInformation mainInformation) {
		try {
			String sql = "INSERT INTO `webmagic`.`main_information` (`name`,  `site`, `city`, `area`, `house_type`, `rent_way`, `price`,`payment_method`, `address`,`row_col`,  `url`) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, mainInformation.getName());
			ps.setString(2, mainInformation.getSite());
			ps.setString(3, mainInformation.getCity());
			ps.setDouble(4, mainInformation.getArea());
			ps.setString(5, mainInformation.getHouseType());
			ps.setString(6, mainInformation.getRentWay());
			ps.setDouble(7, mainInformation.getPrice());
			ps.setString(8, mainInformation.getPaymentMethod());
			ps.setString(9, mainInformation.getAddress());
			ps.setString(10, mainInformation.getRow_col());
			ps.setString(11, mainInformation.getUrl());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
