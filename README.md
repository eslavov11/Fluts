# Fluts

Spring boot application using Java 21.

Startup:
```
mvn clean spring-boot:run
```

Testing locally:

Use swagger-ui (or Postman):
```
http://localhost:8080/swagger-ui/index.html#/flut-trading-controller/processTradingData
```
-> click Try it out


example request body (Content-Type: text/plain):

```
1
6 12 3 10 7 16 5
2
5 7 3 11 9 10
9 1 2 3 4 10 16 10 4 16
0
```

example curl:
```
curl -X 'POST' \
  'http://localhost:8080/api/v1/fluts/process' \
  -H 'accept: text/plain' \
  -H 'Content-Type: text/plain' \
  -d '1
6 12 3 10 7 16 5
2
5 7 3 11 9 10
9 1 2 3 4 10 16 10 4 16
0'
```