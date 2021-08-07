# Spring-boot-jpa-h2
For bank accounts balances checking, and money transfer

### Prerequisite
- Maven
- JDK 1.8+
### Project Structure
```bash
Spring-boot-jpa-h2
├── src
│   ├── main
│   │   ├── java\com\acmebank\accountManager
│   │   └── resources
│   └── test
│       ├── java\com\example\accountManager
│       └── resources
├── LICENSE
├── .gitignore
├── pom.xml
└── README.md


### Packaging
```
mvn package
```
### Test
```
mvn test
```

### Running
```
java -jar spring-boot-jpa-h2-0.0.1-SNAPSHOT.jar
```

### Data
Initial data will be loaded in the H2 database when application start.
> accountRepo.save(new Account("12345678", new BigDecimal("1000000")));
> accountRepo.save(new Account("88888888", new BigDecimal("1000000")));

## Features
It provides APIs for following 2 features

- Retrieve Account Balance
* with accountNumber, e.g. "12345678", or "12345678, 88888888"
* without accountNumber, which will retrieve all accounts under this user
* accountNumber existence checking, redundant accounts filtering

- Create Transaction
* checking if the balances larger than money to be transferred in the account
* transaction management to roll back if any exception happens

### Basic API Information
| Method | Path | Usage |
| --- | --- | --- |
| GET | account-manager/accounts/{@RequestParam(required = false) String accountNum} | retrieve account balance |
| POST | account-manager/transfer | do money transfer |

### Error Code
| Code | Description |
| --- | --- |
| ERR_SYS_001 | used when internal server error happens |
| ERR_SYS_002 | used when gateway timeout happens (e.g. calling external services) |
| ERR_CLIENT_001 | used when error due to client's input or business logic |
| ERR_CLIENT_002 | used when error related to account logic |
 
 
### Library used
| Library | Usage |
| --- | --- |
| spring boot | for spring boot application |
| spring data jpa | for ORM and DB operation purpose |
| H2 | lightweight database for demo purpose |
| httpClient | configurate http request |

### Reference
* https://github.com/sonictee/MoneyTransferAPI#readme
* https://github.com/bezkoder/spring-boot-h2-database-crud
* https://rbkgh.github.io/MoneyTransferRESTApi/





