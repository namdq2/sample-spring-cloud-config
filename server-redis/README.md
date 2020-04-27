# I. Starting vault server:

Docker image: https://hub.docker.com/_/redis/

## Step 1: Run Vault container:
```
docker run --name namdq-redis -p 6379:6379 -d redis:6.0-rc4-alpine
```

## Step 2: Attach the container:
```
docker exec -it namdq-redis sh
```
```
redis-cli
```
Response:
```
OK
127.0.0.1:6379>
```
## Step 3: Adding config:

**application:**

Use `application` for a global config. Key `global` with value: `This is global config on Vault backend.`

```
HMSET application global "This is global config on Redis backend."
```

**client1:**
```
HMSET client1 message "Hello world!" name "Client 1" environment "default-cloud"
```

**client1-dev:**
```
HMSET client1-dev message "Hello world!" name "Client 1" environment "dev"
```

**client2:**
```
HMSET client2 message "Hello world!" name "Client 2" environment "default-cloud"
```

**client2-prod:**
```
HMSET client2-prod message "Hello world!" name "Client 2" environment "prod"
```

# II. Spring boot project
## Step 1: Starting spring boot project:
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
	<artifactId>server-redis</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>server-redis</name>
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
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-data-redis</artifactId>
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
## Step 2: Add `@EnableConfigServer`
```
@SpringBootApplication
@EnableConfigServer
public class ServerVaultApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerVaultApplication.class, args);
    }

}
```
## Step 3: Create `application.yml` in resources folder:
```
server:
  port: 8888
spring:
  profiles:
    active: redis
  redis:
    host: localhost
    port: 6379
```

## Step 4: Verify
Running Vault Server: http://localhost:8888/client1/dev
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

Running Client1 Server: http://localhost:8081/message
```
{
"message": "Hello world!",
"name": "Client 1",
"environment": "dev",
"global": "This is global config on Vault backend."
}
```