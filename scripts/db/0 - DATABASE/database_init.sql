--创建用户
CREATE USER sp LOGIN
  ENCRYPTED PASSWORD 'sp'
  NOSUPERUSER INHERIT CREATEDB NOCREATEROLE NOREPLICATION;
COMMENT ON ROLE sp IS 'spring-payment';


--create tablespace
CREATE TABLESPACE sp_data
  OWNER iot
  LOCATION 'C:\\apps\\PostgreSQL\\data\\sp';


--create database
CREATE DATABASE sp_database
  WITH ENCODING='UTF8'
       OWNER=sp
       CONNECTION LIMIT=-1
       TABLESPACE=sp_data;

create schema front;
create tablespace front_data location 'D:\work\database_data\postgresql\';
grant all privileges on tablespace front_data to sp;