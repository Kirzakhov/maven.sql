package mysql.pack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainCrudOperations {
	static Connection con = null;
	static Statement st = null;
	static ResultSet rs = null;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void insertRecord(String name) throws SQLException {
		try {
			con = ConnectionUtilityClass.getConnection();
			st = con.createStatement();
//			String sel = "select * from mylogin where id="+id;
//			rs = st.executeQuery(sel);
//			if(!rs.next()) {
				String s = "insert into mylogin(name) values("+"'"+name+"')";
				System.out.println(s);
				int i = st.executeUpdate(s);
				if(i>0)
					System.out.println("Data inserted successfully");
				else
					System.out.println("Not inserted");
//			}
//			else
//				System.out.println(id+" already exists");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
//			rs.close();
			st.close();
			con.close();
		}
	}
	public static void updateRecord(String name, int id) throws SQLException {
		try {
			con = ConnectionUtilityClass.getConnection();
			st = con.createStatement();
			String sel = "select * from mylogin where id="+id;
			rs = st.executeQuery(sel);
			if(rs.next()) {
				String s = "update mylogin set name='"+name+"' where id="+id;
				System.out.println(s);
				int i = st.executeUpdate(s);
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
			st.close();
			con.close();
		}
	}
	public static void deleteRecord(int id) throws SQLException {
		try {
			con = ConnectionUtilityClass.getConnection();
			st = con.createStatement();
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
	}
	public static void displayRecord() throws SQLException {
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
	}
	
	public static void main(String[] args) throws SQLException, IOException {
		// TODO Auto-generated method stub
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
