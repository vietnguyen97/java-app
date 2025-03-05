
# Learn Java and Spring boot famework

## Authors

- [@devlight](https://github.com/vietnguyen97)

## Features

- CRUD
- Database (Postgres)
- Exception
- Using lombok and mapstruct
- Bcrypt password
- JWT and Authentication with JWT
- Authorization
- Testing

## Tech Stack

**Server:** Java, Spring Boot

## Run Locally

Clone the project

```bash
  git clone https://github.com/vietnguyen97/java-app
```

Go to the project directory

```bash
  cd my-project
```

Install dependencies

```bash
  mvn clean install
```
Install Database
```bash
  docker pull postgres
  docker run --name my-postgres -e POSTGRES_PASSWORD= -p 5432:5432 -d postgres
```

Start the server

```bash
  mvn spring-boot:run
```


