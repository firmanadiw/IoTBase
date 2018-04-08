# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table sensor_type (
  id                        bigint auto_increment not null,
  value                     integer,
  jumlah                    integer,
  input                     integer,
  output                    integer,
  node                      varchar(255),
  type                      varchar(255),
  a                         integer,
  constraint pk_sensor_type primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table sensor_type;

SET FOREIGN_KEY_CHECKS=1;

