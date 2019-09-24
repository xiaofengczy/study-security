create table user
(
   id       int auto_increment
       primary key,
   username varchar(32)  null,
   password varchar(255) null,
   enabled  tinyint(1)   null,
   locked   tinyint(1)   null
);

INSERT INTO study.user (id, username, password, enabled, locked) VALUES (1, 'root', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', 1, 0);
INSERT INTO study.user (id, username, password, enabled, locked) VALUES (2, 'admin', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', 1, 0);
INSERT INTO study.user (id, username, password, enabled, locked) VALUES (3, 'sang', '$2a$10$RMuFXGQ5AtH4wOvkUqyvuecpqUSeoxZYqilXzbz50dceRsga.WYiq', 1, 0);


create table role
(
   id     int auto_increment
       primary key,
   name   varchar(32) null,
   nameZh varchar(32) null
);

INSERT INTO study.role (id, name, nameZh) VALUES (1, 'ROLE_dba', '数据库管理员');
INSERT INTO study.role (id, name, nameZh) VALUES (2, 'ROLE_admin', '系统管理员');
INSERT INTO study.role (id, name, nameZh) VALUES (3, 'ROLE_user', '用户');


create table user_role
(
   id  int auto_increment
       primary key,
   uid int null,
   rid int null
);

INSERT INTO study.user_role (id, uid, rid) VALUES (1, 1, 1);
INSERT INTO study.user_role (id, uid, rid) VALUES (2, 1, 2);
INSERT INTO study.user_role (id, uid, rid) VALUES (3, 2, 2);
INSERT INTO study.user_role (id, uid, rid) VALUES (4, 3, 3);


create table menu
(
    id       int auto_increment
        primary key,
    parttern varchar(64) null
);

INSERT INTO study.menu (id, parttern) VALUES (1, '/db/**');
INSERT INTO study.menu (id, parttern) VALUES (2, '/admin/**');
INSERT INTO study.menu (id, parttern) VALUES (3, '/user/**');


create table menu_role
(
    id  int auto_increment
        primary key,
    mid int null,
    rid int null
);

INSERT INTO study.menu_role (id, mid, rid) VALUES (1, 1, 1);
INSERT INTO study.menu_role (id, mid, rid) VALUES (2, 2, 2);
INSERT INTO study.menu_role (id, mid, rid) VALUES (3, 3, 3);

