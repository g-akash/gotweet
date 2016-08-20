package object;


public class Messages {
	public int message_id;
	public String sender;
	public String receiver;
	public String body;
	public String message_time;

	public Messages(int mid,String s,String r,String b,String t){
		message_id=mid;
		sender=s;
		receiver=r;
		body=b;
		message_time=t;
	}
}
