# shutdown-springboot-starter

springboot shutdown starter supports springboot 2.x, working with tomcat and jetty, **not working with undertow**.

## why ?

Instead of springboot-actuator, we do not provide rest endpoints. And support task and scheduler shutdown.


## How to use?


> import shutdown lib
```xml
   <dependency>
        <groupId>com.github.imloama</groupId>
        <artifactId>shutdown-spring-boot-starter</artifactId>
        <version>0.0.1</version>
    </dependency>
```

> start project

`java -jar yourproject.jar`

> get PID of your project

`ps -ef|grep yourproject`

> stop your project

`kill -15 <PID>`

