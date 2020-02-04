Project Structure
=================

To build the project I decided to use the Clean Architecture. Based on my experience, one of the
biggest problems I have faced, is when the business rules are strongly coupled with the frameworks 
and libraries used in the project. Sometimes it is almost impossible to change a business rule or 
update a library.

In new projects I always bring the benefits of the use o Clean Architecture to my team. The idea
of isolate our business rules and treat frameworks, libraries, database or other technologies
as plugins, give us the opportunity of change or test new things without worry about our business
rules that is kept intact. Other important part is that we can test fast and easily our code 
without care about any others external dependencies to deal with. For example, sometimes we need 
to test code that has Spring Framework notations and to perform our tests it is often difficult 
to configure the tests without needing the Spring Context due to the numerous dependency injections.   

Domain Layer
------------

I consider the most important part. The entity represents the domain object, and usecase represents 
the business actions, in other words, what we can do with the application.

```

 domain
 └── src
     └── main
         ├── java
         │    └── com.comparator
         │        ├── entity
         │        │   └── InputData
         │        │   └── OutputData
         │        └── usecase
         │            ├── exception
         │            │   └── DecodeDataException
         │            └── port
         │            │   └── DataRepository
         │            └── CompareData
         │            └── DecodeData
         │            └── SaveData
         test
         └── java
             └── com.comparator
                 ├── unit
                 │   └── CompareDataUnitTests
                 │   └── DecodedDataUnitTests
                 │   └── SaveDataUnitTests
                 └── IntegratedTests
```

| Package                           | Class                                                            |
| --------------------------------- |:----------------------------------------------------------------|
| entity                            |  ``InputData`` : Domain class responsible for representing the |
|                                   |    input data for analysis.|
|                                   |  ``OutputData`` : Domain class responsible for maintaining the |
|                                   |    messages related to analysis and returning the layers above  |
| usecase                           |  ``DecodeDataException`` : Class responsible for handling domain|
|                                   |    layer exceptions.|
|                                   |  ``DataRepository`` : Interface that exposes the methods to be|
|                                   |    implemented by the repository in the adapter layer|
|                                   |  ``CompareData`` : Use case responsible for comparing 2 decoded data|
|                                   |  ``DecodeData`` : Use Case responsible to decode the base64 data to the original mode.|
|                                   |  ``SaveData`` : Use Case responsible to persist the data object, using the DataRepository port.|
|                                   |  ``FindData`` : Use Case responsible for searching for a specific data object, using the DataRepository port.|                                              |
| tests (Unit & Integrated Tests)   |  ``CompareDataUnitTests`` : Used to test unitarialy the CompareData usecase. |
|                                   |  ``DecodedDataUnitTests`` : Used to test unitarialy the DecodedData usecase. |
|                                   |  ``SaveDataUnitTests`` : Used to test unitarialy the SaveData usecase. |
|                                   |  ``FindDataUnitTests`` : Used to test unitarialy the FindData usecase. |
|                                   |  ``IntegratedTests`` : Used to test the integration of all use case. |

Adapter Layer
-----

Is responsible to retrieve and store data from different number of sources (Database, device, file systems). In this
layer I implement the interfaces defined by the usecases and provide data transformation to be used by the application.

```

adapter
├── controller
│   └── src
│       ├── java
│       └── com.comparator.controller
│           ├── model
│           │   └── RequestData
│           │   └── ResponseData
│           └── DataController
└── repository
    └── memory-db
        └── src
            └── java
                └── com.comparator.memory.db
                    ├── model
                    │   └── DataDB
                    └── MemoryDataRepository

```

| Package                           | Class                                                            |
| --------------------------------- |:----------------------------------------------------------------|
| controller                        | ``RequestData`` : Class is responsible to receive the data and|
|                                   | transform in an InputData object to send to an usecases|
|                                   | ``ResponseData`` : Class is responsible to receive the data from|
|                                   | an usecase and create an ResponseData object.                 |
|                                   | ``DataController`` : Controller that will be used by the application|
| repository > memory-db            | ``DataDB`` : A model created based in an InputData, to be used  |
|                                   | in the implementation of a repository.                        |
|                                   | ``MemoryDataRepository`` : Provide a implementation of a simple |
|                                   | in memory database solution to simulate a real database usage.|
|                                   | At this point, we can implement for example the infrastructure 
|                                   | to connect to a MongoDB or Postgres database. It will occur |
|                                   | transparently through the application.|

Application Layer
-----

The APIs will be exposed in this layer. I created two applications, using the Spring and Micronaut frameworks. 
I include Micronaut to show what I've been learning and also to present the flexibility of the architecture. 
Regarding the use of frameworks, I believe that they help us a lot to be productive in our deliveries, but we 
cannot consider them the most important part in our projects.

```

application
├── micro-app
│   └── src
│       ├── java
│       └── com.comparator.micronaut
│           ├── config
│           │   └── Config
│           ├── controller
│           │   └── MicronautController
│           └── Application
└──spring-app
    └── src
        ├── java
        └── com.comparator.spring
│           ├── config
│           │   └── Config
│           ├── controller
│           │   └── SpringController
│           └── Application
 

```


| Appication                        | Class                                                            |
| --------------------------------- |:----------------------------------------------------------------|
| spring-app                        | ``Config`` : Using the notations @Configuration and @Bean, it performs |
|                                   | the injection of all necessary objects and instantiates the DataController |
|                                   | class that belongs to the Adapter layer. This class contains all the |
|                                   | necessary dependencies for the application's controller.       |
|                                   | ``SpringController`` : Exposes the apis using the Spring resources. |
|                                   | In this class, we don't have any single business rule, or data transformation. |
|                                   | We only deal with spring resources to the apis.                |
|                                   | ``Application`` : Contains settings for Spring Boot startup and spring context.|
| micro-app                         | ``Config`` : Manually I inject all necessary objects and I instantiate the Data 
|                                   | Controller class that belongs to the Adapter layer. |
|                                   | ``MicronautController`` : Exposes the apis using the Micronaut resources. |
|                                   | In this class, we don't have any single business rule, or data transformation. |
|                                   | We only deal with Microunaut resources to the apis.                |
|                                   | ``Application`` : Contains settings Micronaut to startup the application.|

Configuration
=============

1. After clone the repository, run the command bellow:

.. code-block:: bash

    $ mvn clean install

2. To run the Spring Application, run the command bellow :

.. code-block:: bash

    $ java -jar application/spring-app/target/spring-app-0.0.1-SNAPSHOT.jar
    
3. To run the Micronaut Application, run the command bellow :
    
.. code-block:: bash
    
    $ java -jar application/micro-app/target/micro-app-0.0.1-SNAPSHOT.jar
    
Usage
=====

To validate the APIs endpoints, I provide a Postman project in the endpoints. See below : 

.. code-block:: bash

```
endpoints
└── Project_Comparator_Postman_.json
```