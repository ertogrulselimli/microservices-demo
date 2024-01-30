# Application with micorservice architecture using Spring boot and Spring cloud libraries

## Build docker images using below command and run with docker compose 


Before running below commands  make sure you have docker installed on host machine.
``` 
  mvn compile jib:dockerBuild
  cd docker-compose/default 
  docker compose up
```




