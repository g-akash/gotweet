package object;
//import java.util.*;

public class Tweets {
	public int tweet_id;
	public String user_id;
	public String body;
	public String time;
	public int num_likes;
	public int num_retweets;

	public Tweets(int tid,String id,String bdy,String tme,int a,int b ){
		tweet_id=tid;
		user_id=id;
		body=bdy;
		time=tme;
		num_likes=a;
		num_retweets=b;
	}
	public Tweets(){
		tweet_id=0;
		user_id="";
		body="";
		time="";
		num_likes=0;
		num_retweets=0;
	}
	
}
