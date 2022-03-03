create sequence if not exists roles_id_seq start 1 increment by 1;
create sequence if not exists users_id_seq start 1 increment by 1;

create table if not exists roles(
    id          bigserial not null primary key,
    name        text unique
);

create table if not exists users(
    id          bigserial not null primary key,
    username    text unique,
    email       text unique,
    name        text,
    password    text
);

create table if not exists user_role(
    user_id     bigserial not null,
    role_id     bigserial not null,
    primary key (user_id, role_id),
    constraint  fk_user_role_role_id foreign key (role_id) references roles(id),
    constraint  fk_user_role_username foreign key (user_id) references users(id)
);
