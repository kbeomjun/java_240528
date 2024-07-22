# DB 삭제(university)
drop database if exists university;
# DB 생성(university)
create database if not exists university;

use university;
# student 테이블 삭제
drop table if exists student;
# student 테이블 추가
create table if not exists student(
	st_num char(10) primary key,
	st_name varchar(30) not null,
    st_major varchar(15) not null,
    st_grade int not null default 1
);

drop table if exists professor;
create table if not exists professor(
	pr_num char(10) primary key,
    pr_name varchar(30) not null,
    pr_major varchar(15) not null,
    pr_room varchar(100)
);

drop table if exists lecture;
create table if not exists lecture(
	le_num int primary key auto_increment,
    le_title varchar(30) not null,
    le_major char(5) not null,
    le_room varchar(100),
    le_schedule varchar(50),
    le_point int,
    le_time int,
    le_pr_num char(10),
    foreign key(le_pr_num) references professor(pr_num)
);

drop table if exists course;
create table if not exists course(
	co_num int primary key auto_increment,
    co_mid int default 0,
    co_final int default 0,
    co_perform int default 0,
    co_homework int default 0,
    co_total varchar(2) default 'F',
    co_attendance int default 0,
    co_st_num char(10),
    co_le_num int,
    foreign key(co_st_num) references student(st_num), 
    foreign key(co_le_num) references lecture(le_num)
);

drop table if exists contact;
create table if not exists contact(
	ct_num int primary key auto_increment,
    ct_addr char(100) not null,
    ct_detail char(100),
    ct_phone varchar(13) not null,
    ct_st_num char(10),
    foreign key(ct_st_num) references student(st_num)
);