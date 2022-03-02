create table if not exists role(
    role_id     bigint not null primary key,
    name        text
);

create table if not exists users (
    username    text    not null primary key,
    email       text,
    name        text,
    password    text
);

create table if not exists user_role(
    id          bigint not null primary key,
    username    text not null,
    role_id     bigint not null
);

insert into role values (1,'ROLE_ADMIN');
insert into role values (2,'ROLE_USER');
insert into users values ('admin', 'admin@jorgeacetozi.com.br', 'Jorge Acetozi', '$2a$06$WfXHoFhYT/cXcyNOZQsjMuXRyydgcUTMJcMweF0m8RMub2HS1rCHu');
-- insert into user_role values (1, 'admin', 1);
