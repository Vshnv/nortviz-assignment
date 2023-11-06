# 

Provides API that manages image uploads and serving to Imgur


## Endpoints:


### POST /api/v1/register
Registers given user
Request:
```json
{
    "username": String,
    "password": String,
    "email": String,
    "name": String
}
```
Response:
200 OK
```
User created!
```
<hr>

### POST /api/v1/login
Provides Access Token required for API calls
Request:
```json
{
    "username": String
    "password": String
}
```
Response:
200 OK
```json
{
    "token": String
    "expirationSeconds": Long
}
```
<hr>
All below Endpoints require Bearer Authentication

i.e, include a header with key value "Authentication" and value "Bearer <Access_Token>" where <Access_Token> is token from login API
<hr>

### POST /api/v1/image/upload
Allows users to upload images

Image to be upload should be part send as multi-part file with param name "file"

Response:
200 OK
```json
{
    "id": String
}
```
Above id is the identifier for the Image
<hr>

