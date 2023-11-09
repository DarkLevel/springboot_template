insert into "user" ("enabled", "password", "username", "created_by", "created_date") values (true, '$2a$10$ucU0Td/bQ5xWfFK5KRfdw.8D1YHxX3rx1HrCL6Nk16Tfw4UK/bfHO', 'admin', 'admin', now()) on conflict ("username") do nothing;
insert into "user" ("enabled", "password", "username", "created_by", "created_date") values (true, '$2a$10$ucU0Td/bQ5xWfFK5KRfdw.8D1YHxX3rx1HrCL6Nk16Tfw4UK/bfHO', 'jroigdo', 'admin', now()) on conflict ("username") do nothing;

insert into "role" ("enabled", "name", "created_by", "created_date") values (true, 'admin', 'admin', now()) on conflict ("name") do nothing;
insert into "role" ("enabled", "name", "created_by", "created_date") values (true, 'user', 'admin', now()) on conflict ("name") do nothing;

insert into "user_role" ("enabled", "created_by", "created_date", "user_id", "role_id") values (true, 'admin', now(), 1, 1) on conflict ("user_id", "role_id") do nothing;
insert into "user_role" ("enabled", "created_by", "created_date", "user_id", "role_id") values (true, 'admin', now(), 2, 2) on conflict ("user_id", "role_id") do nothing;
