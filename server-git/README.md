# I. Create a github repository for save config.
This is my repository: https://github.com/namdq2/sample-config-cloud
application.properties
```
global=This is global config on Git backend.
```
client1.propertes
```
message=Hello world!
name=Client 1
environment=default-cloud
```
client1-dev.properties
```
message=Hello world!
name=Client 1
environment=dev
```
client2.properties
```
message=Hello world!
name=Client 2
environment=default-cloud
```
client2-prod.properties
```
message=Hello world!
name=Client 2
environment=prod
```
- Test case "global": the global config for all application. 
- Test case "name": the application config for an application.
- Test case "environment": the profile config for an application (production, development, ...).
- Test case "message": the property config on an application.

# II. Starting a RabbitMQ server.
Docker image: https://hub.docker.com/_/rabbitmq/
`docker run -d --name namdq-rabbit -p 15672:15672 -p 5672:5672 rabbitmq:3-management
`

# III. Create a maven project.
## 1. Adding to `pom.xml` file.
```
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.2.6.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.namdq</groupId>
	<artifactId>server-git</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>server-git</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
		<spring-cloud.version>Hoxton.SR3</spring-cloud.version>
	</properties>

	<dependencies>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-server</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-config-monitor</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.cloud</groupId>
			<artifactId>spring-cloud-starter-stream-rabbit</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
	</dependencies>

	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>org.springframework.cloud</groupId>
				<artifactId>spring-cloud-dependencies</artifactId>
				<version>${spring-cloud.version}</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```
## 2. Adding to `application.yml` file.
```
server:
  port: 8888
spring:
  cloud:
    config:
      server:
        git:
          uri: https://github.com/namdq2/sample-config-cloud
    bus:
      enabled: true
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

```
`spring.cloud.config.server.git.uri: https://github.com/namdq2/sample-config-cloud` is repository config.\
`spring.cloud.bus,enabled: true` is a config to enable push message to RabbitMQ server.\
`spring.cloud.rabbitmq.*` It is config to connect RabbitMQ server.

## 3. Enable a config server.
Add `@EnableConfigServer` on Main class.
```
@SpringBootApplication
@EnableConfigServer
public class ServerGitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerGitApplication.class, args);
    }

}
```

# IV. Testing
http://localhost:8888/client1/dev

Response:
```
{
    "name": "client1",
    "profiles": [
        "dev"
    ],
    "label": null,
    "version": null,
    "state": null,
    "propertySources": [
        {
            "name": "redis:client1-dev",
            "source": {
                "message": "Hello world!",
                "name": "Client 1",
                "environment": "dev"
            }
        },
        {
            "name": "redis:client1",
            "source": {
                "message": "Hello world!",
                "name": "Client 1",
                "environment": "default-cloud"
            }
        }
    ]
}
```