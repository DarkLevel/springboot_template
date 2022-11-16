# springboot_rest_api_template

CÓMO HACER LOGIN:

- URL: localhost:8080/api/oauth/token
- BODY: x-www-form-urlencoded -> username: admin, password: springboot, grant_type: password
- AUTHORIZATION: Type: Basic Auth -> Username: restclient, Password: springboot

CÓMO USAR EL TOKEN:

- AUTHORIZATION: Type: Bearer Token -> Token: ${access_token}
