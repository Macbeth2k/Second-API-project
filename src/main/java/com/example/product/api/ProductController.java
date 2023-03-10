package com.example.product.api;

import com.example.product.dto.ProductDto;
import com.example.product.dto.ProductResponse;
import com.example.product.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("api/product")
@RestController
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Product", description = "The Product API. Contains all the operations that can be performed on a product.")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public void createProduct(@RequestBody ProductResponse productResponse){
        productService.addProduct(productResponse);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ProductDto> readProductById(@PathVariable Long id){
        return new ResponseEntity<>(productService.getProductDtoById(id), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> readAllProducts(){
        return new ResponseEntity<>(productService.getAllProducts(),HttpStatus.OK);
    }

    @PutMapping(path = "/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody ProductResponse productResponse){
        productService.updateProduct(id,productResponse);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteProduct(@PathVariable Long id){
        productService.deleteProductById(id);
    }
}
