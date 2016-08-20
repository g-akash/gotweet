package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import object.*;

//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.text.ParseException;
//import java.util.Date;

public class Functions{


	public static List<Junta> getjunta(String uname)
	{
		List<Junta> jlist = new ArrayList<Junta>();
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from junta where user_id = ?");
			pstmt.setString(1,uname);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				Junta jun = new Junta(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7));
				jlist.add(jun);
			}
		} catch(SQLException sqle){
			System.out.println("SQL exception when getting tweets List");
		} finally{
			closeConnection(connection);
		}
		return jlist;
	}
	
	
	public static List<Tweets> getweets(String uname)
	{
		List<Tweets> tlist = new ArrayList<Tweets>();
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt = connection.prepareStatement("with a as ((select tweet_id from tweets where user_id=?) union (select tweet_id from comments where user_id=?) union (select tweet_id from retweets where user_id=?) union (select tweet_id from likes where user_id=?)) select * from tweets natural join a order by tweet_time desc");
			pstmt.setString(1,uname);
			pstmt.setString(2,uname);
			pstmt.setString(3,uname);
			pstmt.setString(4,uname);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				Tweets t = new Tweets(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
				tlist.add(t);
			}
		} catch(SQLException sqle){
			System.out.println("SQL exception when getting tweets List");
		} finally{
			closeConnection(connection);
		}
		return tlist;
	}
	public static Tweets getatweet(int tid)
	{
		Tweets tw = new Tweets();
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt = connection.prepareStatement("select * from tweets where tweet_id = ?");
			pstmt.setInt(1,tid);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				tw.tweet_id = rs.getInt(1);
				tw.user_id = rs.getString(2);
				tw.body = rs.getString(3);
				tw.time = (String)rs.getString(4);
				tw.num_likes = rs.getInt(5);
				tw.num_retweets = rs.getInt(6);
			}
		} catch(SQLException sqle){
			System.out.println("SQL exception when getting a single tweet");
		} finally{
			closeConnection(connection);
		}
		return tw;
	}
	
	public static List<Messages> getmessages(String uname)
	{
		List<Messages> mlist=new ArrayList<Messages>();
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select * from messages where (sender=? or receiver=?) order by message_time desc");
			pstmt.setString(1,uname);
			pstmt.setString(2,uname);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				Messages m = new Messages(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5));
				mlist.add(m);
			}
			
		} catch(SQLException sqle){
			System.out.println("SQL exception when getting messages");
		} finally{
			closeConnection(connection);
		}
		return mlist;
		
	}

	public static List<Comments> getcomments(int tid)
	{
		List<Comments> clist= new ArrayList<Comments>();
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select * from comments where tweet_id=? order by comment_time");
			pstmt.setInt(1,tid);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				Comments com = new Comments(rs.getInt(1),rs.getInt(2),rs.getString(3),rs.getString(4),rs.getString(5));
				clist.add(com);
			}
		} catch(SQLException sqle){
			System.out.println("SQL exception when getting comments");
		} finally{
			closeConnection(connection);
		}		
		return clist;
	}

	public static List<Tweets> getpublictweets(String uname)
	{
		List<Tweets> tlist=new ArrayList<Tweets>();
		
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("with gr as (with a as ((select tweet_id from tweets where user_id=?) union (select tweet_id from comments where user_id=?) union (select tweet_id from retweets where user_id=?) union (select tweet_id from likes where user_id=?)), b as (select * from tweets natural join a), c as (select distinct * from tweets where user_id in (select followed from followers where follower=?)) (select * from b) union (select * from c)) select * from gr order by tweet_time desc");
			pstmt.setString(1,uname);
			pstmt.setString(2,uname);
			pstmt.setString(3,uname);
			pstmt.setString(4,uname);
			pstmt.setString(5,uname);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				Tweets t = new Tweets(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6));
				tlist.add(t);
			}			
		} catch(SQLException sqle){
			System.out.println("SQL exception getpublictweets");
		} finally{
			closeConnection(connection);
		}
		return tlist;
	}
	

	public static int getmyfollowers(String uname)
	{
		int out=0;
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select num_followers from junta where user_id=?");
			pstmt.setString(1,uname);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				out = rs.getInt(1);
			}			
		} catch(SQLException sqle){
			System.out.println("SQL exception getpublictweets");
		} finally{
			closeConnection(connection);
		}
		return out;
	}

	public static List<Junta> getmyfollowersnames(String uname)
	{
		List<Junta> lj = new ArrayList<Junta>();
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select user_id, name, password, dob, num_followers, num_following, email_id from followers, junta where follower = user_id and followed=?");
			pstmt.setString(1,uname);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				Junta jun = new Junta(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7));
				lj.add(jun);				
			}			
		} catch(SQLException sqle){
			System.out.println("SQL exception getpublictweets");
		} finally{
			closeConnection(connection);
		}
		return lj;
	}


	public static List<Junta> getmyfollowednames(String uname)
	{
		List<Junta> lj = new ArrayList<Junta>();
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select user_id, name, password, dob, num_followers, num_following, email_id from followers, junta where followed = user_id and follower=?");
			pstmt.setString(1,uname);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				Junta jun = new Junta(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7));
				lj.add(jun);				
			}			
		} catch(SQLException sqle){
			System.out.println("SQL exception getpublictweets");
		} finally{
			closeConnection(connection);
		}
		return lj;
	}
	
	
	public static String get_tweeter(int tid)   // returns the tweeter of the tweet
	{
		String name ="";
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select user_id from tweets where tweet_id=?");
			pstmt.setInt(1,tid);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				name = rs.getString(1);
			}			
		} catch(SQLException sqle){
			System.out.println("SQL exception get tweeter");
		} finally{
			closeConnection(connection);
		}
		return name;
	}

	
	
	public static String getname(String uname)
	{
		String name ="";
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select name from junta where user_id=?");
			pstmt.setString(1,uname);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				name = rs.getString(1);
			}			
		} catch(SQLException sqle){
			System.out.println("SQL exception getpublictweets");
		} finally{
			closeConnection(connection);
		}
		return name;
	}
	
	public static int getiamfollowing(String uname)
	{
		int out=0;
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select num_following from junta where user_id=?");
			pstmt.setString(1,uname);
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				out = rs.getInt(1);
			}			
		} catch(SQLException sqle){
			System.out.println("SQL exception getpublictweets");
		} finally{
			closeConnection(connection);
		}
		return out;
	}
	
	
	public static List<Junta> searchpeople(String uname)
	{
		List<Junta> jlist=new ArrayList<Junta>();
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt= connection.prepareStatement("select * from junta where user_id like ? or name like ?");
			pstmt.setString(1,"%"+uname+"%");
			pstmt.setString(2,"%"+uname+"%");
			ResultSet rs= pstmt.executeQuery();
			while (rs.next()){
				Junta jun = new Junta(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getString(7));
				jlist.add(jun);
			}
		} catch(SQLException sqle){
			System.out.println("SQL exception when getting search people");
		} finally{
			closeConnection(connection);
		}
		return jlist;		
	}
	
	public static void addtweet(String uname,String message)
	{
	Connection connection=null;
	try{
		connection = getConnection();
		PreparedStatement pstmt= connection.prepareStatement("insert into tweets values(default,?,?,default,0,0)");
		pstmt.setString(1,uname);
		pstmt.setString(2,message);
		pstmt.execute();
	}catch(SQLException sqle)
	{
		System.out.println("SQL exception in adding tweet");
	}
	finally{
		closeConnection(connection);
	}
	return;	
	}

	public static void sendmessage(String uname, String uname1, String message)
	{
	Connection connection=null;
	try{
		connection = getConnection();
		PreparedStatement pstmt= connection.prepareStatement("insert into messages values(default,?,?,?,default)");
		pstmt.setString(1,uname);
		pstmt.setString(2,uname1);
		pstmt.setString(3,message);
		pstmt.executeUpdate();
	}catch(SQLException sqle)
	{
		System.out.println("SQL exception while ending message");
	}
	finally{
		closeConnection(connection);
	}
	return;	
	}

	public static void follow(String uname,String uname1)
	{
	Connection connection=null;
	try{
		connection = getConnection();
		PreparedStatement pstmt= connection.prepareStatement("insert into followers values(?,?)");
		pstmt.setString(1,uname);
		pstmt.setString(2,uname1);
		pstmt.execute();

		PreparedStatement pstmt1= connection.prepareStatement("update junta set num_followers=num_followers+1 where user_id=?");
		pstmt1.setString(1,uname1);
		pstmt1.executeUpdate();

		PreparedStatement pstmt2= connection.prepareStatement("update junta set num_following=num_following+1 where user_id=?");
		pstmt2.setString(1,uname);
		pstmt2.executeUpdate();

	}catch(SQLException sqle)
	{
		System.out.println("SQL exception in follow function");
	}
	finally{
		closeConnection(connection);
	}
	return;	
	}


	public static void updateuser(String user_id, String name, String dob, String email)
	{
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt=connection.prepareStatement("update junta set name=? , dob=? , email_id=? where user_id=?");
			pstmt.setString(1,name);
			pstmt.setDate(2,java.sql.Date.valueOf(dob));
			pstmt.setString(3,email);
			pstmt.setString(4, user_id);
			pstmt.executeUpdate();
		}catch(SQLException sqle){
			System.out.println("error in updateuser");
		}finally{
			closeConnection(connection);
		}
		return;
	}
	
	
	
	public static void unfollow(String uname,String uname1)
	{
	Connection connection=null;
	try{
		connection = getConnection();
		PreparedStatement pstmt= connection.prepareStatement("delete from followers where follower=? and followed=?");
		pstmt.setString(1,uname);
		pstmt.setString(2,uname1);
		pstmt.execute();

		PreparedStatement pstmt1= connection.prepareStatement("update junta set num_followers=num_followers-1 where user_id=?");
		pstmt1.setString(1,uname1);
		pstmt1.executeUpdate();

		PreparedStatement pstmt2= connection.prepareStatement("update junta set num_following=num_following-1 where user_id=?");
		pstmt2.setString(1,uname);
		pstmt2.executeUpdate();

	}catch(SQLException sqle)
	{
		System.out.println("SQL exception in UNfollow function");
	}
	finally{
		closeConnection(connection);
	}
	return;	
	}

		
	public static boolean isfollow(String uname,String uname1)
	{
	int count=0;
	Connection connection=null;
	try{
		connection = getConnection();
		PreparedStatement pstmt= connection.prepareStatement("select * from followers where follower=? and followed=?");
		pstmt.setString(1,uname);
		pstmt.setString(2,uname1);
		ResultSet rset=pstmt.executeQuery();
		while(rset.next())
		{
			count++;
		}
	}catch(SQLException sqle)
	{
		System.out.println("SQL exception in follow function");
	}
	finally{
		closeConnection(connection);
	}
	if(count==0)
		return false;
	return true;	
	}
	
	
	public static void addcomment(String uname,int tid, String comment)
	{
	Connection connection=null;
	try{
		connection = getConnection();
		PreparedStatement pstmt= connection.prepareStatement("insert into comments values(default,?,?,?,default)");
		pstmt.setInt(1, tid);
		pstmt.setString(2, uname);
		pstmt.setString(3, comment);
		pstmt.execute();
	}catch(SQLException sqle)
	{
		System.out.println("SQL exception in add comment");
	}
	finally{
		closeConnection(connection);
	}
	return;	
	}

	public static void like(int tweet_id,String uname)
	{
		Connection connection=null;
		try{
			connection = getConnection();
			PreparedStatement pstmt=connection.prepareStatement("insert into likes values(?,?)");
			pstmt.setInt(1,tweet_id);
			pstmt.setString(2,uname);
			pstmt.execute();
			
			PreparedStatement pstmt1=connection.prepareStatement("update tweets set num_likes = num_likes + 1 where tweet_id=?");
			pstmt1.setInt(1,tweet_id);
			pstmt1.executeUpdate();
		
		}catch(SQLException sqle){
			System.out.println("There is an exception in like");
		}
		finally{
			closeConnection(connection);
		}
		return;
	}

	public static boolean isliked(int tweet_id,String uname)
	{
		int count=0;
		Connection connection=null;
		try{
			connection = getConnection();
			PreparedStatement pstmt=connection.prepareStatement("select * from likes where tweet_id=? and user_id=?");
			pstmt.setInt(1,tweet_id);
			pstmt.setString(2,uname);
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
				count++;
			}
			
		
		}catch(SQLException sqle){
			System.out.println("There is an exception in checking like");
		}
		finally{
			closeConnection(connection);
		}
		if(count==0)
			return false;
		return true;
	}

	public static boolean isretweeted(int tweet_id,String uname)
	{
		int count=0;
		Connection connection=null;
		try{
			connection = getConnection();
			PreparedStatement pstmt=connection.prepareStatement("select * from retweets where tweet_id=? and user_id=?");
			pstmt.setInt(1,tweet_id);
			pstmt.setString(2,uname);
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
				count++;
			}
					
		}catch(SQLException sqle){
			System.out.println("There is an exception in checking retweet");
		}
		finally{
			closeConnection(connection);
		}
		if(count==0)
			return false;
		return true;
	}
	
	
	public static boolean matchpass(String uname,String pwd)
	{
		String pwd1="";
		Connection connection=null;
		try{
			connection = getConnection();
			PreparedStatement pstmt=connection.prepareStatement("select password from junta where user_id=?");
			pstmt.setString(1,uname);
			ResultSet rset=pstmt.executeQuery();
			//if(rset.empty()){System.out.println("Ya its empty");}
			while(rset.next())
			{
				pwd1 = rset.getString(1);
			}
					
		}catch(SQLException sqle){
			System.out.println("There is an exception in checking retweet");
		}
		finally{
			closeConnection(connection);
		}
		if(pwd1.equals(pwd))
			return true;
		return false;
	}
	


	public static void changepass(String uname,String pwd)
	{
		Connection connection=null;
		try{
			connection = getConnection();

			PreparedStatement pstmt1=connection.prepareStatement("update junta set password = ? where user_id=?");
			pstmt1.setString(1,pwd);
			pstmt1.setString(1,uname);
			pstmt1.executeUpdate();

		}catch(SQLException sqle){
			System.out.println("You have an exception");
		}finally{
			closeConnection(connection);
		}
		return;
	}
	
	
	
	
	public static void retweet(int tweet_id, String uname)
	{
		Connection connection=null;
		try{
			connection = getConnection();

			PreparedStatement pstmt=connection.prepareStatement("insert into retweets values(?,?)");
			pstmt.setInt(1,tweet_id);
			pstmt.setString(2,uname);
			pstmt.execute();

			PreparedStatement pstmt1=connection.prepareStatement("update tweets set num_retweets = num_retweets + 1 where tweet_id=?");
			pstmt1.setInt(1,tweet_id);
			pstmt1.executeUpdate();

		}catch(SQLException sqle){
			System.out.println("You have an exception");
		}finally{
			closeConnection(connection);
		}
		return;
	}

	public static boolean checkUser(String uname, String pwd)
	{
		Connection connection=null;
		int count=0;
		try{
			connection=getConnection();
			System.out.println(uname);
			PreparedStatement pstmt=connection.prepareStatement("select user_id from junta where user_id=? and password=?");
			pstmt.setString(1,uname);
			pstmt.setString(2,pwd);
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
				System.out.println(count);
				count++;
			}
			
		}
		catch(SQLException sqle){
			System.out.println("Account does not exist(check user)");
		}finally{
			closeConnection(connection);
		}
		if(count>0) return true;
		return false;
	}

	public static boolean userid_present(String uname)
	{
		Connection connection=null;
		int count=0;
		try{
			connection=getConnection();
			PreparedStatement pstmt=connection.prepareStatement("select user_id from junta where user_id=?");
			pstmt.setString(1,uname);
			ResultSet rset=pstmt.executeQuery();
			while(rset.next())
			{
				count++;
			}
		}
		catch(SQLException sqle){
			System.out.println("Account does not exist userid present");
		}finally{
			closeConnection(connection);
		}
		if(count>0) return true;
		return false;
	}


	public static void adduser(String user_id, String name, String pwd, String dob, String email)
	{
		Connection connection=null;
		try{
			connection=getConnection();
			PreparedStatement pstmt=connection.prepareStatement("insert into junta values(?,?,?,?,0,0,?)");
			pstmt.setString(1,user_id);
			pstmt.setString(2,name);
			pstmt.setString(3,pwd);
			pstmt.setDate(4, java.sql.Date.valueOf(dob));
			pstmt.setString(5,email);
			pstmt.execute();
			System.out.println("BNM");
		}catch(SQLException sqle){
			System.out.println("error in adduser");
		}finally{
			closeConnection(connection);
		}
		return;
	}
	
	static Connection getConnection() {
		String dbURL = "jdbc:postgresql://10.105.1.12/cs387";
        String dbUser = "db130050011";
        String dbPass = "hello";
        Connection connection=null;
        try {
			Class.forName("org.postgresql.Driver");
			connection = DriverManager.getConnection(dbURL, dbUser, dbPass);
        } catch(ClassNotFoundException cnfe){
        	System.out.println("JDBC Driver not found");
        } catch(SQLException sqle){
        	System.out.println("Error in getting connetcion from the database");
        }
        
        return connection;
	}
	static void closeConnection(Connection connection) {
		try{
			connection.close();
		} catch(SQLException sqle) {
			System.out.println("Error in close database connetcion");
		}
	}
}
