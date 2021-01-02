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

## URLs

Application                                  | URL
---------------------------------------------|-----
Limits Service                               | http://localhost:8080/limits http://localhost:8080/actuator/refresh (POST) 
Spring Cloud Config Server                   | http://localhost:8888/limits-service/default http://localhost:8888/limits-service/dev
Currency Converter Service - Direct Call     | http://localhost:8100/currency-converter/from/USD/to/INR/quantity/10
Currency Converter Service - Feign           | http://localhost:8100/currency-converter-feign/from/EUR/to/INR/quantity/10000
Currency Exchange Service                    | http://localhost:8000/currency-exchange/from/EUR/to/INR http://localhost:8001/currency-exchange/from/USD/to/INR
Eureka                                       | http://localhost:8761/
Zuul - Currency Exchange & Exchange Services | http://localhost:8765/currency-exchange-service/currency-exchange/from/EUR/to/INR http://localhost:8765/currency-conversion-service/currency-converter-feign/from/USD/to/INR/quantity/10
Zipkin                                       | http://localhost:9411/zipkin/
Spring Cloud Bus Refresh                     | http://localhost:8080/actuator/bus-refresh (POST)

## Centralized Config Server

![](https://github.com/shamy1st/spring-microservices/blob/main/images/microservices-environments.png)

![](https://github.com/shamy1st/spring-microservices/blob/main/images/microservice-environments-example.png)

![](https://github.com/shamy1st/spring-microservices/blob/main/images/spring-cloud-config-server.png)

### 1. Git Repository

    //create new directory in your workspace 
    mkdir git-repository
    
    git init
    
    //create limits-service.properties file (default profile)
        server.port=8080
        
        limits-service.minimum=6
        limits-service.maximum=7000

    //create limits-service-dev.properties file (dev profile)
        limits-service.minimum=1
        limits-service.maximum=111

    //create limits-service-qa.properties file (qa profile)
        limits-service.minimum=2
        limits-service.maximum=222
        
    //create currency-exchange-service.properties file (default profile)
        

### 2. Spring Cloud Config Server

![](https://github.com/shamy1st/spring-microservices/blob/main/images/spring-cloud-config-server-creation.png)

        @EnableConfigServer
        @SpringBootApplication
        public class SpringCloudConfigServerApplication {

            public static void main(String[] args) {
                SpringApplication.run(SpringCloudConfigServerApplication.class, args);
            }

        }

        application.properties
            spring.application.name=spring-cloud-config-server
            server.port=8888

            spring.cloud.config.server.git.uri=file:///Users/elshamy/Documents/courses/microservices/workspace/git-repository

        link folder to local git-repository
        
        url: http://localhost:8888/limits-service/default
        url: http://localhost:8888/limits-service/dev
        url: http://localhost:8888/limits-service/qa
        url: http://localhost:8888/currency-exchange-service/default

### 3. Limits Microservice

![](https://github.com/shamy1st/spring-microservices/blob/main/images/limits-microservice-creation.png)

        pom.xml (Bootstrap, provided by spring-cloud-commons, is no longer enabled by default)
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-bootstrap</artifactId>
            </dependency>

        @RestController
        public class LimitConfigurationController {

            @Autowired
            private Configuration configuration;

            @GetMapping("/limits")
            public LimitConfiguration getLimitConfig() {
                return new LimitConfiguration(configuration.getMinimum(), configuration.getMaximum());
            }
        }

        public class LimitConfiguration {
            private int minimum;
            private int maximum;

            public LimitConfiguration() {

            }

            public LimitConfiguration(int minimum, int maximum) {
                this.minimum = minimum;
                this.maximum = maximum;
            }

            public int getMinimum() {
                return minimum;
            }

            public int getMaximum() {
                return maximum;
            }
        }

        @Component
        @ConfigurationProperties("limits-service")
        public class Configuration {
            private int minimum;
            private int maximum;

            public int getMinimum() {
                return minimum;
            }

            public void setMinimum(int minimum) {
                this.minimum = minimum;
            }

            public int getMaximum() {
                return maximum;
            }

            public void setMaximum(int maximum) {
                this.maximum = maximum;
            }
        }

        application.properties, rename to bootstrap.properties (change profile: default, dev, qa)
            spring.application.name=limits-service
            
            spring.cloud.config.uri=http://localhost:8888
            spring.profiles.active=dev

        url: http://localhost:8080/limits

### 4. Currency Exchange Microservice

![](https://github.com/shamy1st/spring-microservices/blob/main/images/currency-exchange-service-creation.png)

        pom.xml
            <dependency>
                <groupId>org.springframework.cloud</groupId>
                <artifactId>spring-cloud-starter-bootstrap</artifactId>
            </dependency>
            <dependency>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-starter-data-jpa</artifactId>
            </dependency>
            <dependency>
                <groupId>com.h2database</groupId>
                <artifactId>h2</artifactId>
                <scope>runtime</scope>
            </dependency>            
        
        Run/Debug Configurations > Add New Configuration > currency-exchange 8000
            VM options: -Dserver.port=8000
        
        Run/Debug Configurations > Add New Configuration > currency-exchange 8001
            VM options: -Dserver.port=8001

        
        @RestController
        public class CurrencyExchangeController {

            @Autowired
            private Environment environment;

            @GetMapping("/exchange-currency/{from}/{to}")
            public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to) {
                ExchangeValue exchangeValue = new ExchangeValue("EUR", "EGP", 19.22f);
                exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
                return exchangeValue;
            }
        }

        public class ExchangeValue {
            private String from;
            private String to;
            private float value;
            private int port;

            public ExchangeValue() {

            }

            public ExchangeValue(String from, String to, float value) {
                this.from = from;
                this.to = to;
                this.value = value;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getTo() {
                return to;
            }

            public void setTo(String to) {
                this.to = to;
            }

            public float getValue() {
                return value;
            }

            public void setValue(float value) {
                this.value = value;
            }

            public int getPort() {
                return port;
            }

            public void setPort(int port) {
                this.port = port;
            }
        }
        
        url: http://localhost:8000/exchange-currency/x/y
        url: http://localhost:8001/exchange-currency/x/y



### 5.






