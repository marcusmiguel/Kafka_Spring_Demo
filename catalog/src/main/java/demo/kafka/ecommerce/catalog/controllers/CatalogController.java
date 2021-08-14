package demo.kafka.ecommerce.catalog.controllers;

import demo.kafka.ecommerce.catalog.models.Product;
import demo.kafka.ecommerce.catalog.models.ProductDTO;
import demo.kafka.ecommerce.catalog.services.CatalogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/catalog")
@RequiredArgsConstructor (onConstructor = @__(@Autowired))
public class CatalogController {

    private final CatalogService catalogService;

    @GetMapping
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> response = catalogService.getProducts();
        if (response!= null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @RequestMapping(value = "/{productName}", method = RequestMethod.GET)
    public ResponseEntity<Product> getByProductName(@PathVariable("productName") String productName){
        Product response = catalogService.findByProductName(productName);
        if (response!= null) {
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO){
        Product product = Product.builder().price(productDTO.getPrice()).quantity(productDTO.getQuantity()).productName(productDTO.getProductName()).build();
        catalogService.insertProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(productDTO);
    }

    @PutMapping
    public ResponseEntity<ProductDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        Product oldProduct = catalogService.findByProductName(productDTO.getProductName());
        Product newProduct = Product.builder()
                .id(oldProduct.getId())
                .price(productDTO.getPrice())
                .quantity(productDTO.getQuantity())
                .productName(productDTO.getProductName())
                .build();
        catalogService.updateProduct(newProduct);
        return ResponseEntity.status(HttpStatus.OK).body(productDTO);
    }

    @DeleteMapping
    public ResponseEntity<ProductDTO> deleteProduct(@RequestBody String productName) {
        catalogService.deleteProduct(productName);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
