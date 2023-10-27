# springboot_rest_api_template

## Execute these scripts in the database

INSERT INTO "user" ("enabled", "password", "username", "created_by", "created_date") VALUES (true, ${generated_password}, 'admin', 'admin', now());
INSERT INTO "user" ("enabled", "password", "username", "created_by", "created_date") VALUES (true, ${generated_password}, 'jroigdo', 'admin', now());

INSERT INTO "role" ("enabled", "name", "created_by", "created_date") VALUES (true, 'admin', 'admin', now());
INSERT INTO "role" ("enabled", "name", "created_by", "created_date") VALUES (true, 'admin', 'admin', now());

INSERT INTO user_role ("enabled", "created_by", "created_date", "user_id", "role_id") VALUES (true, 'admin', now(), 1, 1);
INSERT INTO user_role ("enabled", "created_by", "created_date", "user_id", "role_id") VALUES (true, 'admin', now(), 2, 2);

### Generated password

The password is generated in the terminal when the server is deployed
