package demo.kafka.ecommerce.catalog.repositories;

import demo.kafka.ecommerce.catalog.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, String>
{
    Product findByProductName(String productName);
}
