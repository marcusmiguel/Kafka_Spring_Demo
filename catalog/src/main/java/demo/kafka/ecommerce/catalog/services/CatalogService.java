package demo.kafka.ecommerce.catalog.services;

import demo.kafka.ecommerce.catalog.models.OrderDTO;
import demo.kafka.ecommerce.catalog.models.Product;
import demo.kafka.ecommerce.catalog.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor =  @__(@Autowired))
@Slf4j
public class CatalogService {
    
    private final ProductRepository productRepository;

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product findByProductName(String productName){
        return productRepository.findByProductName(productName);
    }

    public void insertProduct(Product product){
        Product exists = productRepository.findByProductName(product.getProductName());
        if (exists == null) {productRepository.save(product);}
        else{
            log.error("Product already exists: " + product.getProductName());
        }
    }

    public void updateProduct(Product product){
        productRepository.save(product);
    }

    public void deleteProduct(String productName){
        Product productToDelete = productRepository.findByProductName(productName);
        productRepository.delete(productToDelete);
    }

    public void dispatchOrder(OrderDTO orderDTO){
        Product product = productRepository.findByProductName(orderDTO.getProductName());
        if (product == null){
            log.error("Catalog Service - ProductNotFound: " + orderDTO.getProductName());
        }
        int newQuantity = product.getQuantity()-orderDTO.getQuantity();
        if (newQuantity < 0){
            log.error("Catalog Service - Not enough products in the store.");
        }
        else if ( newQuantity == 0){
            deleteProduct(product.getProductName());
        }
        product.setQuantity(newQuantity);
        updateProduct(product);
        log.info("Product updated: " + product.getProductName()
                +  " Quantity: " + product.getQuantity());
    }
}
