**Welcome to RestfulAPIJava24!**

Overview:

RestfulAPIJava24 is a Jakarta EE-based RESTful API designed to manage a collection of music albums. The application provides a set of endpoints for creating, retrieving, updating, and deleting album records. It is built using modern Java EE technologies and is deployed on a WildFly server.

Key Features Album Management: The application offers comprehensive CRUD operations for managing albums, allowing users to create new albums, retrieve existing ones, update album details, and delete albums.

Dependency Injection: Utilizes Contexts and Dependency Injection (CDI) to manage dependencies, ensuring a clean and modular architecture.

Persistence: Leverages Jakarta Persistence API (JPA) for seamless database interactions, supporting various database systems.

Exception Handling: Implements custom exception mappers to provide meaningful error responses to clients.

Deployment
The application is packaged as a WAR file and deployed on a WildFly server. The server is configured to support Jakarta EE technologies, including CDI, JPA, and JAX-RS.


**Deploying the application and database** 

1. - Clone the repository 
   - git clone https://github.com/villeken/RestfulAPIJava24
   - cd RestfulAPIJava24
   

2. - Build the project using Maven
   - Make sure maven is installed on your machine.
   - mvn clean package
   

3. - Ensure docker is installed and running in the background


   
4. - Start the database and server with:
   - docker-compose --profile production up


