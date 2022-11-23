# springboot_rest_api_template

## Execute these scripts in the database

INSERT INTO "role" (disabled, name) VALUES (false, 'ADMIN_ROLE');
INSERT INTO "user" (disabled, "password", username) VALUES (false, ${generated_password}, 'admin');
INSERT INTO user_role (user_id, role_id) VALUES (1, 1);

### Generated password

The password is generated in the console when the server is deployed
