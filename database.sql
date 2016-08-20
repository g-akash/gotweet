
create table junta(
user_id varchar(20) not null,
name varchar(100) not null,
password varchar(20) not null,
DOB Date not null,
num_followers integer not null,
num_following integer not null,
email_id varchar(100) not null,

primary key(user_id),
check(num_followers>=0),
check(num_following>=0),
check(email_id like '%@%')
);

create table followers(
follower varchar(20) references junta(user_id) not null,
followed varchar(20) references junta(user_id) not null,

primary key(follower, followed)
);

create table tweets(
tweet_id serial,
user_id varchar(20) references junta not null,
body varchar(140) not null,
tweet_time timestamp default current_timestamp,
num_likes integer not null,
num_retweets integer not null,

primary key(tweet_id),
check(num_likes>=0),
check(num_retweets>=0)
);

create table comments(
comment_id serial,
tweet_id integer not null,
user_id varchar(20) not null ,
body varchar(140) not null,
comment_time timestamp default current_timestamp,

primary key(tweet_id,comment_id),
foreign key(user_id) references junta,
foreign key(tweet_id) references tweets,
check(comment_id>0)
);

create table messages(
message_id serial,
sender varchar(20) not null,
receiver varchar(20) not null,
body varchar(160) not null,
message_time timestamp default current_timestamp,

primary key(message_id),
foreign key(sender) references junta(user_id),
foreign key(receiver) references junta(user_id)
);

create table likes(
tweet_id integer not null,
user_id varchar(20) not null,
primary key(user_id,tweet_id),
foreign key(user_id) references junta,
foreign key(tweet_id) references tweets
);

create table retweets(
tweet_id integer not null,
user_id varchar(20) not null,
primary key(tweet_id,user_id),
foreign key(tweet_id) references tweets,
foreign key(user_id) references junta
);
