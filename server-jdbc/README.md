# I. Starting Mysql database:

Docker image: https://hub.docker.com/_/mysql

## Step 1: Run Mysql container:
```
docker run --name namdq-mysql -e MYSQL_ROOT_PASSWORD=password -e MYSQL_DATABASE=environment -d -p 3306:3306 mysql:8
```
Host: localhost\
Port: 3306\
Username: root\
Password: password

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
	<artifactId>server-vault</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>server-vault</name>
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
public class ServerJDBCApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerJDBCApplication.class, args);
    }

}
```
## Step 3: Create 'schema.sql' in resources folder:
```
create TABLE PROPERTIES (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  APPLICATION VARCHAR(250) DEFAULT 'application' NOT NULL,
  PROFILE VARCHAR(250) DEFAULT 'default' NOT NULL,
  LABEL VARCHAR(250) DEFAULT 'master' NOT NULL,
  MY_KEY VARCHAR(250) NOT NULL,
  MY_VALUE VARCHAR(250) NOT NULL
);
```

## Step 4: Create 'data.sql' in resources folder:
```
insert into PROPERTIES (APPLICATION, PROFILE, LABEL, MY_KEY, MY_VALUE) values
  ('application', 'default', 'master', 'global', 'This is global config on JDBC backend.'),
  ('client1', 'default', 'master', 'message', 'Hello world!'),
  ('client1', 'default', 'master', 'name', 'Client 1'),
  ('client1', 'default', 'master', 'environment', 'default-cloud'),
  ('client1', 'dev', 'master', 'message', 'Hello world!'),
  ('client1', 'dev', 'master', 'name', 'Client 1'),
  ('client1', 'dev', 'master', 'environment', 'dev'),
  ('client2', 'default', 'master', 'message', 'Hello world!'),
  ('client2', 'default', 'master', 'name', 'Client 2'),
  ('client2', 'default', 'master', 'environment', 'default-cloud'),
  ('client2', 'prod', 'master', 'message', 'Hello world!'),
  ('client2', 'prod', 'master', 'name', 'Client 2'),
  ('client2', 'prod', 'master', 'environment', 'prod');
```

## Step 5: Create `application.yml` in resources folder:
```
server:
  port: 8888
spring:
  profiles:
    active: jdbc
  cloud:
    config:
      server:
        jdbc:
          sql: SELECT MY_KEY, MY_VALUE from PROPERTIES where APPLICATION=? and PROFILE=? and LABEL=?
          order: 1
  datasource:
    url: jdbc:mysql://localhost:3306/environment
    username: root
    password: password
    initialization-mode: always
  jpa:
    hibernate:
      ddl-auto: none
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL5Dialect
```

## Step 6: Verify
Running Vault Server: http://localhost:8888/application/default
```
{
    "name": "application",
    "profiles": [
        "default"
    ],
    "label": null,
    "version": null,
    "state": null,
    "propertySources": [
        {
            "name": "vault:application",
            "source": {
                "global": "This is global config on Vault backend."
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