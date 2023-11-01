insert into "user" ("id", "enabled", "password", "username", "created_by", "created_date") values (1, true, '$2a$10$ucU0Td/bQ5xWfFK5KRfdw.8D1YHxX3rx1HrCL6Nk16Tfw4UK/bfHO', 'admin', 'admin', now()) on conflict ("id") do nothing;
insert into "user" ("id", "enabled", "password", "username", "created_by", "created_date") values (2, true, '$2a$10$ucU0Td/bQ5xWfFK5KRfdw.8D1YHxX3rx1HrCL6Nk16Tfw4UK/bfHO', 'jroigdo', 'admin', now()) on conflict ("id") do nothing;

insert into "role" ("id", "enabled", "name", "created_by", "created_date") values (1, true, 'admin', 'admin', now()) on conflict ("id") do nothing;
insert into "role" ("id", "enabled", "name", "created_by", "created_date") values (2, true, 'user', 'admin', now()) on conflict ("id") do nothing;

insert into "user_role" ("id", "enabled", "created_by", "created_date", "user_id", "role_id") values (1, true, 'admin', now(), 1, 1) on conflict ("id") do nothing;
insert into "user_role" ("id", "enabled", "created_by", "created_date", "user_id", "role_id") values (2, true, 'admin', now(), 2, 2) on conflict ("id") do nothing;
