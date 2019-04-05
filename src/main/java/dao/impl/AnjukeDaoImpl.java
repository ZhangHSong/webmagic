package dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import dao.AnjukeDao;
import entity.AnjukeMainInformation;
import entity.AnjukePhoto;

public class AnjukeDaoImpl implements AnjukeDao{

	private Connection conn = null;
	private Statement stmt = null;

	public AnjukeDaoImpl() {
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

	public int addMainInformation(AnjukeMainInformation anjukeMainInformation) {
		try {
			String sql = "INSERT INTO `webmagic`.`anjuke_main_information` (`number`, `name`, `address`, `area`, `house_type`, `rent_way`, `price`,`payment_method`, `contacts`, `release_time`, `description`) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, anjukeMainInformation.getNumber());
			ps.setString(2, anjukeMainInformation.getName());
			ps.setString(3, anjukeMainInformation.getAddress());
			ps.setDouble(4, anjukeMainInformation.getArea());
			ps.setString(5, anjukeMainInformation.getHouseType());
			ps.setString(6, anjukeMainInformation.getRentWay());
			ps.setDouble(7, anjukeMainInformation.getPrice());
			ps.setString(8, anjukeMainInformation.getPaymentMethod());
			ps.setString(9, anjukeMainInformation.getContacts());
			ps.setString(10, anjukeMainInformation.getReleaseTime());
			ps.setString(11, anjukeMainInformation.getDescription());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

	public int addPhotoInformation(AnjukePhoto anjukePhoto) {
		try {
			String sql = "INSERT INTO `webmagic`.`anjuke_photo` (`number`, `indoor_photo`, `floor_photo`, `environment_photo`) VALUES (?, ?, ?, ?);";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, anjukePhoto.getNumber());
			ps.setString(2, anjukePhoto.getIndoorPhoto());
			ps.setString(3, anjukePhoto.getFloorPhoto());
			ps.setString(4, anjukePhoto.getEnvironmentPhoto());
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}

}
