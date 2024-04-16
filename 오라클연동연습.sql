drop table member;

commit;
select * from MEMBER;


select * from comment_list;

CREATE SEQUENCE article_sq
  START WITH 1
  INCREMENT BY 1
  MINVALUE 1
  NOCACHE
  NOCYCLE;
  
  
  select * from article;
  delete from article;
  delete from comment_list;
  insert into article(id,content,title) values(article_sq.nextval,4444,'라라라라');


drop table article;
drop table comment_list;
drop table coffee;
drop SEQUENCE article_sq;
drop SEQUENCE Member_SQ;
drop SEQUENCE coffee_sq;
