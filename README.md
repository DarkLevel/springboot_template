# springboot_rest_api_template

## Execute these scripts in the database

INSERT INTO "user" ("disabled", "password", "username", "created_by", "created_date") VALUES (false, ${generated_password}, 'admin', 'admin', now());
INSERT INTO "user" ("disabled", "password", "username", "created_by", "created_date") VALUES (false, ${generated_password}, 'jroigdo', 'admin', now());

INSERT INTO "role" ("disabled", "name", "created_by", "created_date") VALUES (false, 'admin', 'admin', now());
INSERT INTO "role" ("disabled", "name", "created_by", "created_date") VALUES (false, 'user', 'admin', now());

INSERT INTO user_role ("user_id", "role_id") VALUES (1, 1);
INSERT INTO user_role ("user_id", "role_id") VALUES (2, 2);

### Generated password

The password is generated in the terminal when the server is deployed
