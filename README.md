# spring-boot-ms-docker
Spring Boot Applications | Docker | Eureka | Spring Security | H2 Memory Database | 

1. This demo project contains three Spring Boot Applications :-> 
    
    a. product-service : 
    b. product-review
    c. discover-service
    
2. All these applications can run inside the docker container. 

4. discover-service application is the Eureka Server and both the other services (product-service, product-review) are their clients. 

5. product-service application has only one endpoint which calls 'product-review' service discovering it via eureka.

6. product-review application has four CRUD endpoints. it also has a in memory H2 database and some seed data are inserted into it when the applicatin starts.

7. product-review application endpoints (POST, PUT & DELETE) are secured by Spring Security Basic In Memory Authentication mechanism.

8. The product_id (samples were given in the requirement) which is used in this application is : BB5476, since I was able to find the response of it on the external adidas service @ https://www.adidas.co.uk/api/products/


**Discover-Service Application: **

1. This application runs on port 8672. To run it below are the commands : 

    a. docker build --tag=ds:latest . (To create image. It will take some time only the first time to dowload dependencies from Maven)

    b. docker run -p 8762:8762 --name discovery-service ds:latest (To verify that app is running, please visit : http://localhost:8762/ on your host machine.
 
