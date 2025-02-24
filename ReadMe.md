# SPRING INTEGRADO COM REDIS VIA CRUDREPOSITORY 
Essa aplicação consiste em utilizar o serviço de cache do Redis para armazenar dados no Redis utilizando a interface CRUD Repository.

Caso você deseje trabalhar com o Redis utilizando Jedis ou o cache do Spring, veja os projetos abaixo:
- [Redis via Jedis](https://github.com/moraesrv/spring-redis-jedis)
- [Redis via cache do Spring](https://github.com/moraesrv/spring-redis-annotations)

## PRÉ-REQUISITOS
Aplicações que devem estar instaladas em sua máquina:
- [JDK](https://www.oracle.com/br/java/technologies/downloads/)
- [Docker](https://www.docker.com/products/docker-desktop/)
- IDE com suporte a Java de sua preferência

## SPRING
Para usar a interface Crud Repository no Spring para salvar os dados no Redis você deve utilizar:

**Dependências**
- **jedis**: biblioteca Java popular que fornece uma interface para interagir com o Redis.
- **spring-boot-starter-data-jpa**: é uma das dependências principais para integrar a aplicação com um banco de dados relacional utilizando JPA (Java Persistence API).
- **spring-boot-starter-data-redis**: utilizada para integrar o Redis à sua aplicação Spring.
- **spring-boot-starter-json**: permite as aplicações Spring trabalharem com JSON.
- **jackson-databind**: biblioteca responsável pela serialização e desserialização de objetos Java para JSON e vice-versa.

**Obs**: Como o Redis não é um banco relacional deve-se desabilitar a configuração automática do Data Source. 
```
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
```


**Configuração**

Especifica os dados de conexão com o REDIS no application.properties:
```
spring.data.redis.host=127.0.0.1
spring.data.redis.port=6379
spring.data.redis.password=root
spring.data.redis.timeout=2000
```

## DOCKER
Para utilizar o serviço de cache iremos instanciar um container Redis no Docker, para isso execute os seguintes comandos no terminal:

Baixar a imagem mais recente do REDIS
```
docker pull redis
```

Criar o container com o serviço do REDIS
```
docker run --name redis-container -p 6379:6379 -d redis redis-server --requirepass root
```

Este comando cria o container com as seguintes características:
- **--name redis-container**: nomeia o container como redis-container.
- **-p**: especifica a porta que será exposta para a conexão.
- **-d**: roda o container em segundo plano (detached mode).
- **redis**: especifica a imagem do Redis que será usada, neste caso será utilizada a mais recente.
- **redis-server --requirepass**: define a senha de acesso ao Redis. **(Parâmetro opcional)**

**Conclusão**: ao executar o comando acima, foi criado um container chamado *redis-container* com o serviço do *Redis* rodando na porta *6379* que para acessá-lo será utilizada a senha *root*.

Caso queira remover posteriormente o container execute os seguintes comandos:
```
docker stop redis-container
docker rm redis-container
```

## REDIS
Redis (*Remote Dictionary Server*) é um sistema de armazenamento de dados em memória, utilizado principalmente para melhorar o desempenho de sistemas ao armazenar e recuperar dados de forma rápida, ao invés de consultar um banco de dados tradicional a cada solicitação. Dentre as características do Redis destacam-se:
- NoSQL (estrutura de chave e valor)
- Armazena os dados em memória
- Permite escalar horizontalmente
- Oferece respostas de baixa latência
- Possui alta disponibilidade
- Compatível com vários tipos de dados, como conjuntos classificados, hashes, conjuntos, listas e strings

Para acessar o Redis é necessário abrir o redis-cli, como ele encontra-se dentro do container, utilizamos o seguinte comando:

```
docker exec -it redis-container redis-cli -a root
```
*Obs: caso o container foi instanciado sem senha, remova o parâmetro **-a root***

### REDIS-CLI
O redis-cli é uma ferramenta de linha de comando (CLI) que permite interagir com o servidor Redis. 

Abaixo encontram-se alguns comandos úteis para manipulação dos dados no Redis:

Para listar todos os registros de uma determinada chave:
```
keys pessoa*
```

Para consultar uma chave:
```
hgetall pessoa:3
```

Para remover uma chave:
```
del produtos::1
```

Para limpar o cache:
```
flushdb
```