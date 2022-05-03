package mysql.pack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DisplayRecordsMain {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtilityClass.getConnection();
			st = con.createStatement();
			String sel = "select * from mylogin";
			rs = st.executeQuery(sel);
			System.out.println("Id\tName");
			while(rs.next()) {
				int id = rs.getInt(1); //rs.getInt("id");
				String name = rs.getString(2); //rs.getString("name");
				System.out.println(id + "\t" +name);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			rs.close();
			st.close();
			con.close();
		}
		sc.close();

	}

}
