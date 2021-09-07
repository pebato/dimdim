create sequence hibernate_sequence start with 1 increment by  1
create table tb_transacao (id number(19,0) not null, description varchar2(255), points number(10,0) not null check (points<=500 AND points>=10), title varchar2(255), primary key (id))
create table tb_user (id number(19,0) not null, email varchar2(255), name varchar2(255), password varchar2(255), primary key (id))
