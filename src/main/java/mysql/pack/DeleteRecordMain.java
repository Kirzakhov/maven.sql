package mysql.pack;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteRecordMain {

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con = null;
		Statement st = null;
		ResultSet rs = null;
		try {
			con = ConnectionUtilityClass.getConnection();
			st = con.createStatement();
			System.out.println("Enter id of record which needs to be deleted");
			int id = sc.nextInt();
			String sel = "select * from mylogin where id="+id;
			rs = st.executeQuery(sel);
			if(rs.next()) {
				String s = "delete from mylogin where id="+id;
				System.out.println(s);
				int i = st.executeUpdate(s);
				if(i>0)
					System.out.println("Data deleted successfully");
				else
					System.out.println("Not deleted");
			}
			else
				System.out.println(id+" doesn't exist");
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
