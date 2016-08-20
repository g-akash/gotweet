
insert into junta values('Maverick','Ram','asdf','10-10-1990',0,0,'as@hotmail.com');
insert into junta values('asgard','Leela','lee','12-20-1990',0,0,'lee@hotmail.com');
insert into junta values('shubham','Shubham','shu','12-20-1980',0,0,'shu@gmail.com');
insert into junta values('user1','User1','u1','11-20-1980',0,0,'u1@gmail.com');
insert into junta values('user2','User2','u2','1-20-1980',0,0,'u2@gmail.com');
insert into junta values('user3','User3','u3','9-20-1980',0,0,'u3@gmail.com');
insert into junta values('user4','User4','u4','4-20-1980',0,0,'u4@gmail.com');


insert into tweets values(default,'user1','I am invincible',default,0,0);
insert into tweets values(default,'user2','hi2',default,0,0);
insert into tweets values(default,'user3','hi3',default,0,0);
insert into tweets values(default,'user4','hi4',default,0,0);
insert into tweets values(default,'user4','hi5',default,0,0);


insert into comments values(default, 4, 'user1', 'good', default);
insert into comments values(default, 4, 'user1', 'good', default);
insert into comments values(default, 2, 'user2', 'good', default);
insert into comments values(default, 3, 'user1', 'good', default);
insert into comments values(default, 1, 'user1', 'boring', default);
insert into comments values(default, 1, 'user1', 'really boring', default);


insert into messages values(default,'user1','user2','hi',default);
insert into messages values(default,'user2','user3','hi',default);
insert into messages values(default,'user1','user4','hi',default);


insert into followers values('user1', 'user2');
insert into followers values('user2', 'user3');
insert into followers values('user4', 'user1');

insert into likes values(1,'user1');
insert into likes values(2,'user1');
insert into likes values(3,'user1');
insert into likes values(1,'user2');
insert into likes values(1,'user4');


insert into retweets values(1,'user4');
insert into retweets values(2,'user1');
insert into retweets values(3,'user2');
insert into retweets values(2,'user3');
insert into retweets values(1,'user3');








