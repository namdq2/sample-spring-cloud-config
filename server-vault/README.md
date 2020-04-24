# I. Starting vault server:

Docs: https://learn.hashicorp.com/vault/?track=getting-started#getting-started

Docker image: https://hub.docker.com/_/vault

## Step 1: Run Vault container:
```
docker run --cap-add=IPC_LOCK -p 8200:8200 --name namdq-vault vault
```
Response:
```
==> Vault server configuration:

             Api Address: http://0.0.0.0:8200
                     Cgo: disabled
         Cluster Address: https://0.0.0.0:8201
              Listener 1: tcp (addr: "0.0.0.0:8200", cluster address: "0.0.0.0:8201", max_request_duration: "1m30s", max_request_size: "33554432", tls: "disabled")
               Log Level: info
                   Mlock: supported: true, enabled: false
           Recovery Mode: false
                 Storage: inmem
                 Version: Vault v1.4.0

WARNING! dev mode is enabled! In this mode, Vault runs entirely in-memory
and starts unsealed with a single unseal key. The root token is already
authenticated to the CLI, so you can immediately begin using Vault.

You may need to set the following environment variable:

    $ export VAULT_ADDR='http://0.0.0.0:8200'

The unseal key and root token are displayed below in case you want to
seal/unseal the Vault or re-authenticate.

Unseal Key: 7H/IOzRr+s9hYuZHUP27ciYg/oI1lytLLbmHLBfJYu4=
Root Token: s.3qFWxQGRIYfRYPSidNwhLZMI

Development mode should NOT be used in production installations!

==> Vault server started! Log data will stream in below:

2020-04-24T02:38:17.802Z [INFO]  proxy environment: http_proxy= https_proxy= no_proxy=
2020-04-24T02:38:17.802Z [WARN]  no `api_addr` value specified in config or in VAULT_API_ADDR; falling back to detection if possible, but this value should be manually set
2020-04-24T02:38:17.803Z [ERROR] core: no seal config found, can't determine if legacy or new-style shamir
2020-04-24T02:38:17.803Z [ERROR] core: no seal config found, can't determine if legacy or new-style shamir
2020-04-24T02:38:17.803Z [INFO]  core: security barrier not initialized
2020-04-24T02:38:17.803Z [INFO]  core: security barrier initialized: stored=1 shares=1 threshold=1
2020-04-24T02:38:17.804Z [INFO]  core: post-unseal setup starting
2020-04-24T02:38:17.818Z [INFO]  core: loaded wrapping token key
2020-04-24T02:38:17.818Z [INFO]  core: successfully setup plugin catalog: plugin-directory=
2020-04-24T02:38:17.818Z [INFO]  core: no mounts; adding default mount table
2020-04-24T02:38:17.819Z [INFO]  core: successfully mounted backend: type=cubbyhole path=cubbyhole/
2020-04-24T02:38:17.819Z [INFO]  core: successfully mounted backend: type=system path=sys/
2020-04-24T02:38:17.819Z [INFO]  core: successfully mounted backend: type=identity path=identity/
2020-04-24T02:38:17.823Z [INFO]  core: successfully enabled credential backend: type=token path=token/
2020-04-24T02:38:17.823Z [INFO]  core: restoring leases
2020-04-24T02:38:17.825Z [INFO]  rollback: starting rollback manager
2020-04-24T02:38:17.825Z [INFO]  expiration: lease restore complete
2020-04-24T02:38:17.825Z [INFO]  identity: entities restored
2020-04-24T02:38:17.825Z [INFO]  identity: groups restored
2020-04-24T02:38:17.825Z [INFO]  core: post-unseal setup complete
2020-04-24T02:38:17.826Z [INFO]  core: root token generated
2020-04-24T02:38:17.826Z [INFO]  core: pre-seal teardown starting
2020-04-24T02:38:17.826Z [INFO]  rollback: stopping rollback manager
2020-04-24T02:38:17.826Z [INFO]  core: pre-seal teardown complete
2020-04-24T02:38:17.826Z [ERROR] core: no seal config found, can't determine if legacy or new-style shamir
2020-04-24T02:38:17.826Z [INFO]  core.cluster-listener.tcp: starting listener: listener_address=0.0.0.0:8201
2020-04-24T02:38:17.826Z [INFO]  core.cluster-listener: serving cluster requests: cluster_listen_address=[::]:8201
2020-04-24T02:38:17.826Z [INFO]  core: post-unseal setup starting
2020-04-24T02:38:17.826Z [INFO]  core: loaded wrapping token key
2020-04-24T02:38:17.826Z [INFO]  core: successfully setup plugin catalog: plugin-directory=
2020-04-24T02:38:17.827Z [INFO]  core: successfully mounted backend: type=system path=sys/
2020-04-24T02:38:17.827Z [INFO]  core: successfully mounted backend: type=identity path=identity/
2020-04-24T02:38:17.827Z [INFO]  core: successfully mounted backend: type=cubbyhole path=cubbyhole/
2020-04-24T02:38:17.828Z [INFO]  core: successfully enabled credential backend: type=token path=token/
2020-04-24T02:38:17.828Z [INFO]  core: restoring leases
2020-04-24T02:38:17.828Z [INFO]  rollback: starting rollback manager
2020-04-24T02:38:17.828Z [INFO]  identity: entities restored
2020-04-24T02:38:17.828Z [INFO]  identity: groups restored
2020-04-24T02:38:17.828Z [INFO]  expiration: lease restore complete
2020-04-24T02:38:17.828Z [INFO]  core: post-unseal setup complete
2020-04-24T02:38:17.828Z [INFO]  core: vault is unsealed
2020-04-24T02:38:17.830Z [INFO]  core: successful mount: namespace= path=secret/ type=kv
2020-04-24T02:38:17.843Z [INFO]  secrets.kv.kv_97536fa1: collecting keys to upgrade
2020-04-24T02:38:17.843Z [INFO]  secrets.kv.kv_97536fa1: done collecting keys: num_keys=1
2020-04-24T02:38:17.843Z [INFO]  secrets.kv.kv_97536fa1: upgrading keys finished
```

## Step 2: Attach the container:
```
docker exec -it namdq-vault sh
```
```
export VAULT_ADDR='http://0.0.0.0:8200'
```
```
vault login s.3qFWxQGRIYfRYPSidNwhLZMI
```
Login response:
```
Success! You are now authenticated. The token information displayed below
is already stored in the token helper. You do NOT need to run "vault login"
again. Future Vault requests will automatically use this token.

Key                  Value
---                  -----
token                s.3qFWxQGRIYfRYPSidNwhLZMI
token_accessor       XKcu6oUGCuqdv9NUvmdVIJw8
token_duration       ∞
token_renewable      false
token_policies       ["root"]
identity_policies    []
policies             ["root"]
```
## Step 3: Adding config:

**application:**

Use `application` for a global config. Key `global` with value: `This is global config on Vault backend.`

```
vault kv put secret/application global="This is global config on Vault backend."
```

Put response:
```
Key              Value
---              -----
created_time     2020-04-23T09:23:46.405855261Z
deletion_time    n/a
destroyed        false
version          1
```

**client1:**
```
vault kv put secret/client1 message="Hello world!" name="Client 1" environment="default-cloud"
```

**client1-dev:**
```
vault kv put secret/client1,dev message="Hello world!" name="Client 1" environment="dev"
```

**client2:**
```
vault kv put secret/client2 message="Hello world!" name="Client 2" environment="default-cloud"
```

**client2-prod:**
```
vault kv put secret/client1,prod message="Hello world!" name="Client 2" environment="prod"
```

# Spring boot project
Step 1: Starting spring boot project:
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
    active: vault
  cloud:
    config:
      server:
        vault :
          kvVersion: 2
          authentication: TOKEN
          token: s.3qFWxQGRIYfRYPSidNwhLZMI
```
Token is `Root token` on *Part I* > *Step 1*:
```
...
Unseal Key: 7H/IOzRr+s9hYuZHUP27ciYg/oI1lytLLbmHLBfJYu4=
Root Token: s.3qFWxQGRIYfRYPSidNwhLZMI
...
```
## Step 4: Verify
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