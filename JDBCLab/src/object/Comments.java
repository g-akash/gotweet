package object;

public class Comments {
	public int tweet_id;
	public int comment_id;
	public String user_id;
	public String body;
	public String comment_time;

	public Comments(int tid,int cid,String id,String b,String t){
	tweet_id=tid;
	comment_id=cid;
	user_id=id;
	body=b;
	comment_time=t;
	}
}
