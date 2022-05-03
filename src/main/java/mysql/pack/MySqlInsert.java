package mysql.pack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class MySqlInsert {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtilityClass.getConnection();
			st = con.createStatement();
			System.out.println("Enter name");
			String name = sc.nextLine();
			System.out.println("Enter id");
			int id = sc.nextInt();
			String sel = "select * from mylogin where id="+id;
			rs = st.executeQuery(sel);
			if(!rs.next()) {
				String s = "insert into mylogin values("+id+",'"+name+"')";
				System.out.println(s);
				int i = st.executeUpdate(s);
				if(i>0)
					System.out.println("Data inserted successfully");
				else
					System.out.println("Not inserted");
			}
			else
				System.out.println(id+" already exists");
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
