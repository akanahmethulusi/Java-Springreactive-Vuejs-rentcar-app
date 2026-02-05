package at.codefabrik.Springreactivevuejs.product.controller;

import at.codefabrik.Springreactivevuejs.product.model.product.ProductDetailsResponse;
import at.codefabrik.Springreactivevuejs.product.model.product.ProductResponse;
import at.codefabrik.Springreactivevuejs.product.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Flux<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDetailsResponse> getProductDetails(@PathVariable("id") String id) {
        return productService.getProductDetails(id);
    }
}
