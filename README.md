##### Stack
* javaspark
* testng
* guice
* maven
* lombok
* jsondb
* logback
* gson
* commons-io

##### Build
```
mvn clean compile package
```

##### Run
```
docker build -t todo

docker run -p 4567:4567 todo
```

##### Test
```
POST /api/todo HTTP/1.1
Host: localhost:4567
Content-Type: application/json
Authorization: Basic am9objpuaG9q
User-Agent: PostmanRuntime/7.15.2
Accept: */*
Cache-Control: no-cache
Postman-Token: 6ff6de9d-a73f-4af1-8d06-400dc18dfb1f,6b5f12d8-538e-42ba-be9f-631aa3b61974
Host: localhost:4567
Accept-Encoding: gzip, deflate
Content-Length: 17
Connection: keep-alive
cache-control: no-cache

{
	body:"test2"
}

```

##### Documentation

https://documenter.getpostman.com/view/8479502/SVfJVBYN?version=latest

##### Cleanup

```
docker stop $(docker ps -aq)
docker rm $(docker ps -aq)
docker rmi $(docker images -q)
```


