package atd.database;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import atd.domein.Bericht;
import atd.domein.StatusDB;
import atd.domein.User;

/**
 * @author Martijn
 * 
 *         TODO: Heel veel code kan hieruit weg
 *
 */

public class dbBerichten {
	private static Connection con = null;
	private static Statement st = null;
	private static ResultSet rs = null;

	private static Properties prop = new Properties();
	private static InputStream config = null;

	/**
	 * Maakt nieuwe Klant gebruiker aan in database
	 * 
	 * @return StatusDB Status
	 */
	public static StatusDB setBericht(String bericht, String tijd, User user) {
		try {
			config = new URL("http://db.plebian.nl/3c0nf1g/database.properties").openStream();
			prop.load(config);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://" + prop.getProperty("database") + ":3306/" + prop.getProperty("table"), prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
			st = con.createStatement();

			String query = "INSERT INTO Berichten(Bericht, Tijd, User) VALUES(?, ?, ?)";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, bericht);
			preparedStmt.setString(2, tijd);
			preparedStmt.setInt(3, user.getId());
			preparedStmt.execute();

		} catch (SQLException | IOException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
			return StatusDB.INCORRECT;
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return StatusDB.UNKOWN;
	}

	/**
	 * Geeft alle Berichten in de database terug als ArrayList
	 * 
	 * @return ArrayList<Klant>
	 * @throws SQLException
	 */
	public static ArrayList<Bericht> getAllBerichten() throws SQLException {
		ArrayList<Bericht> alleBerichten = new ArrayList<>();
		try {
			config = new URL("http://db.plebian.nl/3c0nf1g/database.properties").openStream();
			prop.load(config);
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://" + prop.getProperty("database") + ":3306/" + prop.getProperty("table"), prop.getProperty("dbuser"), prop.getProperty("dbpassword"));
			st = con.createStatement();
			rs = st.executeQuery("SELECT * FROM Berichten ORDER BY id DESC LIMIT 0, 6");

			while (rs.next()) {
				alleBerichten.add(new Bericht(rs.getString(2), rs.getString(3), dbUsers.getUser(rs.getInt(4))));
			}
			return alleBerichten;

		} catch (SQLException | IOException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} finally {
			try {
				if (rs != null) {
					rs.close();
				}
				if (st != null) {
					st.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
		return null;
	}
}