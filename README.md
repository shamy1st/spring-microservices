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

## Spring Cloud

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
4. Visibility and Monitoring
5. Fault Tolerance

