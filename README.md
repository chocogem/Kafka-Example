## How to run

### 1. build client app image to docker

  - cd client

  - mvn package

  - docker build -t exam-client .

### 2. build server app image to docker

  - cd ..

  - cd server

  - mvn package

  - docker build -t exam-server .

### 3. build worker app image to docker

  - cd ..

  - cd worker

  - mvn package

  - docker build -t exam-worker .

### 4. start all app on docker

  - cd ..

  - docker-compose up -d

## How to test

1. open url on browser http://localhost:8080/index

2. fill json request on input text example : {"Msg_id":1,"sender":"Tom","msg":"Hello"}

3. press submit button

## Built With

* Intellij
* Spring Boot
* Kafka
* Zookeeper
* MongoDB
* Thymeleaf

