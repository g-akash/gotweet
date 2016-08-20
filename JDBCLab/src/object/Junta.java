package object;

public class Junta {	
	public String username;
	public String name;
	public String password;
	public String dob;
	public int num_followers;
	public int num_following;
	public String email_id;

	public Junta(String id,String n,String p,String d,int p1,int q,String e){
		
	username=id;
	name=n;
	password=p;
	dob=d;
	num_followers=p1;
	num_following=q;
	email_id=e;
	}

}
