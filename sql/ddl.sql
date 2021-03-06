drop table if exists post CASCADE;

create table post
(
    id bigint generated by default as identity,
    title varchar(255) not null,
    content text not null,
    primary key(id)
)

create table user
(
    id bigint generated by default as identity,
    loginId varchar(32) not null,
    loginPw varchar(64) not null,
    primary key(id)
)