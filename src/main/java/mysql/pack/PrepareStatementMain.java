package mysql.pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class PrepareStatementMain {
	static Connection con = null;
	static PreparedStatement pst = null;
	static ResultSet rs = null;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void insertRecord(String name) throws SQLException {
		try {
			con = ConnectionUtilityClass.getConnection();
			String s = "insert into mylogin(name) values(?)";
			pst = con.prepareStatement(s);
			pst.setString(1, name);
			int i = pst.executeUpdate();
			if(i>0)
				System.out.println("Data inserted successfully");
			else
				System.out.println("Not inserted");
//			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
//			rs.close();
			pst.close();
			con.close();
		}
	}
	public static void updateRecord(String name, int id) throws SQLException {
		try {
			con = ConnectionUtilityClass.getConnection();
			String sel = "select * from mylogin where id=?";
			pst = con.prepareStatement(sel);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String s = "update mylogin set name=? where id=?";
				pst = con.prepareStatement(s);
				pst.setString(1, name);
				pst.setInt(2, id);
				int i = pst.executeUpdate();
				if(i>0)
					System.out.println("Data updated successfully");
				else
					System.out.println("Not updated");
			}
			else
				System.out.println(id+" doesn't exist");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			rs.close();
			pst.close();
			con.close();
		}
	}
	public static void deleteRecord(int id) throws SQLException {
		try {
			con = ConnectionUtilityClass.getConnection();
			String sel = "select * from mylogin where id=?";
			pst = con.prepareStatement(sel);
			pst.setInt(1, id);
			rs = pst.executeQuery();
			if(rs.next()) {
				String s = "delete from mylogin where id=?";
				pst = con.prepareStatement(s);
				pst.setInt(1, id);
				int i = pst.executeUpdate();
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
			pst.close();
			con.close();
		}
	}
	public static void displayRecord() throws SQLException {
		try {
			con = ConnectionUtilityClass.getConnection();
			String sel = "select * from mylogin";
			pst = con.prepareStatement(sel);
			rs = pst.executeQuery();
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
			pst.close();
			con.close();
		}
	}
	public static void main(String[] args) throws SQLException, NumberFormatException, IOException {
		while(true) {
			System.out.println("************MENU************");
			System.out.println("1.Insert Record");
			System.out.println("2.Update Record");
			System.out.println("3.Delete Record");
			System.out.println("4.Display Record");
			String name;
			int id;
			int n = Integer.parseInt(br.readLine());
			switch(n) {
			case 1: System.out.println("Enter name");
					name = br.readLine();
//					System.out.println("Enter id");
//					id = Integer.parseInt(br.readLine());
					insertRecord(name);
					break;
			case 2: System.out.println("Enter name");
					name = br.readLine();
					System.out.println("Enter id");
					id = Integer.parseInt(br.readLine());
					updateRecord(name, id);
					break;
			case 3: System.out.println("Enter id");
					id = Integer.parseInt(br.readLine());
					deleteRecord(id);
					break;
			case 4: displayRecord();
					break;
			}
			System.out.println("Do you wanna continue? Y/N");
			char ch = br.readLine().toLowerCase().charAt(0);
			if(ch == 'n')
				break;
		}

	}

}
