//Attendance Tracker
import java.sql.*; 
import java.util.Scanner;
import java.io.Console;

class LoginAdmin
{
	void process()
	{

		try
		{
			int flag=0;
			Class.forName("com.mysql.jdbc.Driver");
			//step2 create connection
			String url="jdbc:mysql://localhost:3306/new";
			String user="root";
			String passw="root";
			Connection con=DriverManager.getConnection(url,user,passw);
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Unique ID: ");
			String u=sc.nextLine();
			System.out.println("Username: ");
			String user_name=sc.nextLine();
			Console ob=System.console();
			System.out.println("Password: ");
			char a[]=ob.readPassword();
			String pass=String.valueOf(a);
			
			
			Statement st=con.createStatement();  
			ResultSet set=st.executeQuery("select name,password,uniqID from admin;");
			while(set.next())
			{
				String uniID=set.getString("uniqID");
				String name=set.getString("name");
				String password =set.getString("password");
				if(u.equals(uniID))
				{
					if(user_name.equals(name))
					{
						if(password.equals(pass))
						{
							System.out.println("Login Successfully...");
							break;
						}
						else
						{
							flag=1;
							System.out.println("Wrong password");
							System.exit(0);
						}
					}
				}
			}
			if(flag==1)
			{
				System.out.println("Login Failed, User not found.");
				System.exit(0);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
}
class LoginStudent
{
	void process()
	{

		try
		{
			int flag=0;
			Class.forName("com.mysql.jdbc.Driver");
			//step2 create connection
			String url="jdbc:mysql://localhost:3306/new";
			String user="root";
			String passw="root";
			Connection con=DriverManager.getConnection(url,user,passw);
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Unique ID: ");
			String uniID=sc.nextLine();
			System.out.println("Username: ");
			String user_name=sc.nextLine();
			Console ob=System.console();
			System.out.println("Password: ");
			char a[]=ob.readPassword();
			String pass=String.valueOf(a);
			
			Statement st=con.createStatement();  
			ResultSet set=st.executeQuery("select uniqID,name,password from student;");
			while(set.next())
			{
				String u=set.getString("uniqID");
				String name=set.getString("name");
				String password =set.getString("password");
				if(uniID.equals(u))
				{
					if(user_name.equals(name))
					{
						if(password.equals(pass))
						{
							System.out.println("Login Successfully...");
							break;
						}
						else
						{
							flag=1;
							System.out.println("Wrong password");
							System.exit(0);
						}
					}
					else
					{
						System.out.println("Wrong Username");
						System.exit(0);
					}
				}	
			}
			if(flag==1)
			{
				System.out.println("Login Failed, User not found.");
				System.exit(0);	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
}


class RegisterStudent
{
	void process()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/new";
			String user="root";
			String passw="root";
			Connection con=DriverManager.getConnection(url,user,passw);
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter UniqueID:");
			String uniID=sc.nextLine();
			System.out.println("Enter Name:");
			String user_name=sc.nextLine();
			Console ob=System.console();
			System.out.println("Password: ");
			char a[]=ob.readPassword();
			String pass=String.valueOf(a);
			
			Statement st=con.createStatement();  
			String sql=String.format("insert into student(uniqID,name,password) values('%s','%s','%s');",uniID,user_name,pass);
			int rs=st.executeUpdate(sql);
			
			System.out.println("Registered Successfully...");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
}


class RegisterAdmin
{
	void process()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/new";
			String user="root";
			String passw="root";
			Connection con=DriverManager.getConnection(url,user,passw);
			
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter your Name:");
			String user_name=sc.nextLine();
			System.out.println("Enter Unique ID:");
			String uniID=sc.nextLine();
			Console ob=System.console();
			System.out.println("Password: ");
			char a[]=ob.readPassword();
			String pass=String.valueOf(a);
			Statement st=con.createStatement();  
			String sql = String.format("insert into admin values('%s','%s','%s');",uniID,user_name,pass);
			int rs=st.executeUpdate(sql);
			
			System.out.println("Registered Successfully...");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
}

class Admin extends Student
{
	private String name,att;
	
	Admin(String name)
	{
		this.name=name;
	}
	void insertDate()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter date:");
			String date=sc.nextLine();
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/new";
			String user="root";
			String passw="root";
			Connection con=DriverManager.getConnection(url,user,passw);
			Statement st=con.createStatement();  
			String sql = String.format(" alter table student add %s varchar(10);",date);
			int rs=st.executeUpdate(sql);
			
			System.out.println("New date Inserted successfully...");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void insert()
	{
		try
		{
			RegisterStudent rs=new RegisterStudent();
			rs.process();
			
			System.out.println("Data Inserted Successfully...");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void update()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter UniqueID:");
			String uniID=sc.nextLine();
			System.out.println("Enter date(format:DDmonthYYYY):");
			String date=sc.nextLine();
			System.out.println("Enter Attendance(P/A):");
			String att=sc.nextLine();
			
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/new";
			String user="root";
			String passw="root";
			Connection con=DriverManager.getConnection(url,user,passw);
			Statement st=con.createStatement();  
			String sql = String.format("UPDATE student SET %s = '%s' WHERE uniqID='%s';",date,att,uniID);
			int rs=st.executeUpdate(sql);
			
			System.out.println("Data updated Successfully...");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	void delete()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter UniqueID:");
			String uniID=sc.nextLine();
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/new";
			String user="root";
			String passw="root";
			Connection con=DriverManager.getConnection(url,user,passw);
			Statement st=con.createStatement();  
			String sql = String.format("Delete from student where uniqID='%s';",uniID);
			int rs=st.executeUpdate(sql);
			
			System.out.println("Data Deleted Successfully...");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}

class Student
{
	void view()
	{
		try
		{
			Scanner sc=new Scanner(System.in);
			System.out.println("Enter Date(format:DDmonthYYYY):");
			String date=sc.nextLine();
			if(date.equals("password"))
			{
				System.out.println("Illegal Date,Session terminated.");
				System.exit(0);
			}				
			Class.forName("com.mysql.jdbc.Driver");
			String url="jdbc:mysql://localhost:3306/new";
			String user="root";
			String passw="root";
			Connection con=DriverManager.getConnection(url,user,passw);
			Statement st=con.createStatement();  
			String sql = String.format("select * from student order by name asc");
			ResultSet rs=st.executeQuery(sql);
			
			while(rs.next())
			{
				String name=rs.getString("name");
				String s = String.format("%s",date);
				String att=rs.getString(s);
				System.out.print("Name: "+name);
				System.out.println(" Attendace: "+att);
			}
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
}


class Project
{
	public static void main(String args[])
	{
		String ch,i,j;
		int count=0,count2=0;
		Scanner sc=new Scanner(System.in);
		System.out.println("1.Login as Admin");
		System.out.println("2.Login as Student");
		System.out.println("Press 1 or 2");
		ch=sc.nextLine();
		switch(ch)
		{
			case "1":
			{
				LoginAdmin l=new LoginAdmin();
				l.process();
				while(count==0)
				{
					System.out.println("1.Insert");
					System.out.println("2.Insert new date");
					System.out.println("3.Update");
					System.out.println("4.View");
					System.out.println("5.Delete");
					System.out.println("Press 1/2/3/4/5");
					j=sc.nextLine();
					switch(j)
					{
						case "1":
						{
							
							Admin A=new Admin("abc");
							A.insert();
							break;
						}
						case "2":
						{
							System.out.println("Enter name:");
							String name=sc.nextLine();
							Admin A=new Admin(name);
							A.insertDate();
							break;
						}
						case "3":
						{
							System.out.println("Enter name:");
							String name=sc.nextLine();
							Admin A=new Admin(name);
							A.update();
							break;
						}
						case "4":
						{
							
							Admin A=new Admin("abc");
							A.view();
							break;
						}
						case "5":
						{
							Admin A=new Admin("abc");
							A.delete();
							break;
						}
						default:
						{
							System.out.println("Invalid Input.");
							break;
						}
					}
					System.out.println("Do you want to continue(Y/N):");
					String s=sc.nextLine();
					if(s.equals("Y") || s.equals("y"))
						count=0;
					else if(s.equals("N") || s.equals("n"))
					{
						count=1;
						System.out.println("You are Logged Out Successfully...");
					}
					else
					{
						System.out.println("Invalid Input,session terminated!!!");
						System.exit(0);
					}
				
				}
				
				break;
			}
			case "2":
			{
					
				System.out.println("1.Login ");
				System.out.println("2.Register");
				System.out.println("Press 1 or 2");
				i=sc.nextLine();
				switch(i)
				{
					case "1":
					{
						LoginStudent l=new LoginStudent();
						l.process();
						while(count2==0)
						{
							Student s=new Student();
							s.view();
							System.out.println("Do you want to view attendance for another date(Y/N):");
							String q=sc.nextLine();
							if(q.equals("Y") || q.equals("y"))
								count=0;
							else if(q.equals("N") || q.equals("n"))
							{
								count2=1;
								System.out.println("You are Logged Out Successfully...");
							}
							else
							{
								System.out.println("Invalid Input,session terminated!!!");
								System.exit(0);
							}
						}
						break;
					}
					case "2":
					{
						RegisterStudent r=new RegisterStudent();
						r.process();
						break;
					}
					default:
					{
						System.out.println("Invalid Input.");
						break;
					}
				}
					
				break;
			}
			default:
			{
				System.out.println("Invalid Input.");
				break;
			}
		}
		
		
	}
}



