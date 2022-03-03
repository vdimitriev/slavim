insert into roles(id, name) values (nextval('roles_id_seq'), 'ROLE_ADMIN');
insert into roles(id, name) values (nextval('roles_id_seq'), 'ROLE_USER');
insert into users(id, username, email, name, password) values (nextval('users_id_seq'), 'admin', 'admin@jorgeacetozi.com.br', 'Jorge Acetozi', '$2a$06$WfXHoFhYT/cXcyNOZQsjMuXRyydgcUTMJcMweF0m8RMub2HS1rCHu');
insert into user_role(user_id, role_id) values (1, 1);
