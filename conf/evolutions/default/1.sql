# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table album (
  id                        bigint not null,
  title                     varchar(255),
  description               varchar(255),
  date_created              timestamp,
  author_id                 bigint,
  constraint pk_album primary key (id))
;

create table comment (
  author                    varchar(255),
  comment_date              timestamp)
;

create table photo (
  id                        bigint not null,
  title                     varchar(255),
  description               varchar(255),
  photo_date                timestamp,
  photo_url                 varchar(255),
  picture                   bytea,
  album_id                  bigint,
  constraint pk_photo primary key (id))
;

create table tag (
  id                        bigint not null,
  title                     varchar(255),
  constraint pk_tag primary key (id))
;

create table user (
  id                        bigint not null,
  firstname                 varchar(255),
  lastname                  varchar(255),
  password                  varchar(255),
  email                     varchar(255),
  date_created              timestamp,
  constraint pk_user primary key (id))
;


create table tag_photo (
  tag_id                         bigint not null,
  photo_id                       bigint not null,
  constraint pk_tag_photo primary key (tag_id, photo_id))
;
create sequence album_seq;

create sequence photo_seq;

create sequence tag_seq;

create sequence user_seq;

alter table album add constraint fk_album_author_1 foreign key (author_id) references user (id);
create index ix_album_author_1 on album (author_id);
alter table photo add constraint fk_photo_album_2 foreign key (album_id) references album (id);
create index ix_photo_album_2 on photo (album_id);



alter table tag_photo add constraint fk_tag_photo_tag_01 foreign key (tag_id) references tag (id);

alter table tag_photo add constraint fk_tag_photo_photo_02 foreign key (photo_id) references photo (id);

# --- !Downs

drop table if exists album cascade;

drop table if exists comment cascade;

drop table if exists photo cascade;

drop table if exists tag cascade;

drop table if exists tag_photo cascade;

drop table if exists user cascade;

drop sequence if exists album_seq;

drop sequence if exists photo_seq;

drop sequence if exists tag_seq;

drop sequence if exists user_seq;

