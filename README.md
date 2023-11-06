# Nortviz Assignment

Provides API that manages image uploads (to) and serving (from) Imgur


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
<h3>NOTE</h3>
<h3>All below Endpoints require Bearer Authentication</h3>
<h3>i.e, include a header with key value "Authentication" and value "Bearer <Access_Token>" where <Access_Token> is token from login API</h3>
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

### GET /api/v1/image/list
Lists all images uploaded

Response:
200 OK
```json
[{
    "id": String
}]
```
Above id is the identifier for the Image
<hr>

<hr>

### GET /api/v1/image?id=<id>
Fetches image with given id

Response:
200 OK
IMAGE
<hr>

### DELETE /api/v1/image?id=<id>
Deletes image with given id

Response:
200 OK
<hr>

