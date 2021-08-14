# Kafka Demo Project
![img](/images/Diagram.png)
The application simulates an ecommerce scenario cosisting of two
APIs:
- The **`ORDER-API`**: receives the requests to buy a certain quantity of a product and then send a message via Kafka to the CATALOG-API.
- The **`CATALOG-API`**: allows access and CRUD operations on the products in the Database and receives and process messages from the ORDER-API. 

## Technologies
- Spring Kafka
- Docker Compose
- Docker (to run the SQLServer, Kafka and Zookeeper)

