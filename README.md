# Spring Microservices

## Definition

Microservices are small autonomous services that work together.
  * REST
  * Small deployable units
  * Cloud enabled (able to instantiate without a lot of configurations)

## Microservices Challenges

1. Bounded Context (how to decide for each microservice, what it should do? and what it shouldn't do?)
2. Configuration Management
3. Dynamic Load Balancing
4. Visibility and Monitoring (which microservice is down? which doesn't have enough disk space?)
5. Fault Tolerance

## [Spring Cloud](https://spring.io/projects/spring-cloud)

### Main Projects

1. [Spring Cloud Netflix](https://spring.io/projects/spring-cloud-netflix)
   * Integration with various Netflix OSS components (Eureka, Hystrix, Zuul, Archaius, etc.).

2. [Spring Cloud Config](https://spring.io/projects/spring-cloud-config)
   * Centralized external configuration management backed by a git repository. 
   * The configuration resources map directly to Spring Environment but could be used by non-Spring applications if desired.

3. [Spring Cloud Bus](https://spring.io/projects/spring-cloud-bus)
   * An event bus for linking services and service instances together with distributed messaging. 
   * Useful for propagating state changes across a cluster (e.g. config change events).

### Challenges Solution

1. Bounded Context
2. Configuration Management
   * Spring Cloud Config Server
   ![](https://github.com/shamy1st/spring-microservices/blob/main/images/spring-cloud-config-server.png)

3. Dynamic Load Balancing
   * Naming Server (Eureka)
   * Ribbon (Client Side Load Balancing)
   ![](https://github.com/shamy1st/spring-microservices/blob/main/images/ribbon-load-balancing.png)
   * Feign (Easier REST Clients)

4. Visibility and Monitoring
   * Zipkin Distributed Tracing
   * Netflix API Gateway

5. Fault Tolerance
   * Hystrix

## Microservices Advantages

1. Adaptive to new technologies & process
2. Dynamic Scaling
3. Faster release cycles

## Ports

Application                       | Port
----------------------------------|------
Limits Service                    | 8080, 8081, ...
Spring Cloud Config Server        | 8888
Currency Exchange Service         | 8000, 8001, 8002, ..
Currency Conversion Service       | 8100, 8101, 8102, ...
Netflix Eureka Naming Server      | 8761
Netflix Zuul API Gateway Server   | 8765
Zipkin Distributed Tracing Server | 9411





