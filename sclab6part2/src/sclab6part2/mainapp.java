package sclab6part2;

import java.sql.*;
import java.util.Scanner;

public class mainapp 
{
	public static void main(String [] args)
	{
		String url="jdbc:mysql://localhost/";
		String user="root";
		String Password= "1234";
		Scanner sc = new Scanner(System.in);
		String slct,se,de,nm,reg;
		try
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con=DriverManager.getConnection(url,user,Password);
			
			Statement stt=con.createStatement();
			
			stt.execute("CREATE DATABASE IF NOT EXISTS University");
			stt.execute("USE University");
			
			stt.execute("DROP TABLE IF EXISTS Student");
			stt.execute("CREATE TABLE Student("+
			"Id BIGINT NOT NULL AUTO_INCREMENT,"+
			"RegNo VARCHAR(25),"+
			"Name VARCHAR(25),"+
			"Class VARCHAR(25),"+
			"Section VARCHAR(25),"+
			"Contact VARCHAR(25),"+
			"Address VARCHAR(50),"+
			"PRIMARY KEY(Id)"
					+")");
			
			
			stt.execute("INSERT INTO Student (RegNo,Name,Class,Section,Contact,Address) VALUES" +
			"('147257','Umar','BESE1','B','1245789','house no 1'),"+
			"('123455','Ahmad','BESE2','B','1234618','HOUSE NO 11'),"+
			"('123453','Saim','BESE1','A','1234468','HOUSE NO 13'),"+
			"('123451','Mohsin','BESE4','C','1234678','HOUSE NO 17'),"+
			"('123458','Talha','BESE5','B','1234118','HOUSE NO 19')");
			
			System.out.println("Current records:\n");
			ResultSet res=stt.executeQuery("SELECT * FROM Student");
			while(res.next())
			{
				System.out.println("Registration no.:"+ res.getString("RegNo")+
						" Name:"+res.getString("Name")+
						" Class:"+res.getString("Class")+
						" Section:"+res.getString("Section")+
						" Contact:"+res.getString("Contact")+
						" Address:"+res.getString("Address")+"\n");
			}
			
			System.out.println("Search record:1 | Delete record:2 | Exit:3");
			slct = sc.next();
			while(slct!="3")
			{
				if(slct.equals("1"))
				{
					System.out.println("Search by name:1 | Search by Registration no.:2");
					se = sc.next();
					if(se.equals("1"))
					{
						System.out.println("Enter Name:");
						nm = sc.next();
						ResultSet sbyname=stt.executeQuery("SELECT * FROM Student WHERE Name='" + nm +"'");
						while(sbyname.next())
							{
							System.out.println("Registration no.:"+ sbyname.getInt("RegNo")+
							" Name:"+sbyname.getString("Name")+
							" Class:"+sbyname.getString("Class")+
							" Section:"+sbyname.getString("Section")+
							" Contact:"+sbyname.getString("Contact")+
							" Address:"+sbyname.getString("Address")+"\n");
							}
					}
					else
					{
						System.out.println("Enter Registration no.:");
						reg = sc.next();
						ResultSet sbyreg=stt.executeQuery("SELECT * FROM Student WHERE RegNo='"+reg+"'");
						while(sbyreg.next())
							{
							System.out.println("Registration no.:"+ sbyreg.getInt("RegNo")+
							" Name:"+sbyreg.getString("Name")+
							" Class:"+sbyreg.getString("Class")+
							" Section:"+sbyreg.getString("Section")+
							" Contact:"+sbyreg.getString("Contact")+
							" Address:"+sbyreg.getString("Address")+"\n");
							}
					}
				}
				else
				{
					System.out.println("Delete by name:1 | Delete by Registration no.:2");
					de = sc.next();
					if(de.equals("1"))
					{
						System.out.println("Enter Name:");
						nm = sc.next();
						stt.execute("DELETE FROM Student WHERE Name='"+nm+"'");
						System.out.println("Record has been deleted.Current records contains:\n");
						ResultSet res2=stt.executeQuery("SELECT * FROM Student");
						while(res2.next())
							{
								System.out.println("Registration no.:"+ res2.getString("RegNo")+
								" Name:"+res2.getString("Name")+
								" Class:"+res2.getString("Class")+
								" Section:"+res2.getString("Section")+
								" Contact:"+res2.getString("Contact")+
								" Address:"+res2.getString("Address")+"\n");
							}
					}
					else
					{
						System.out.println("Enter Registration no.:");
						reg = sc.next();
						stt.execute("DELETE FROM Student WHERE Regno='"+reg+"'");
						System.out.println("Record has been deleted.Current records contains:\n");
						ResultSet res3=stt.executeQuery("SELECT * FROM Student");
						while(res3.next())
							{
								System.out.println("Registration no.:"+ res3.getString("RegNo")+
								" Name:"+res3.getString("Name")+
								" Class:"+res3.getString("Class")+
								" Section:"+res3.getString("Section")+
								" Contact:"+res3.getString("Contact")+
								" Address:"+res3.getString("Address")+"\n");
							}
					}
				}
				System.out.println("Search record:1 | Delete record:2 | Exit:3");
				slct = sc.next();
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}
