package at.codefabrik.Springreactivevuejs.product.service.product;

import at.codefabrik.Springreactivevuejs.product.entity.*;
import at.codefabrik.Springreactivevuejs.product.repo.elastic.ProductEsRepository;
import at.codefabrik.Springreactivevuejs.product.service.category.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductEsService {
    private final ProductEsRepository productEsRepository;
    private final CategoryService categoryService;

    /**
     * @Async
     * Wenn wir im ProductService.java product=productRepository.save(product).block(); aufrufen,
     * dann blockieren wir den aktuellen Thread bis die Operation abgeschlossen ist.
     */

    public Mono<ProductEs> saveNewProduct(Product product){
        ProductEs productEs = ProductEs.builder()
                .id(product.getId())
                .name(product.getName())
                .code(product.getCode())
                .description(product.getDescription())
                //.companyId(product.getCompanyId())
                .seller(CompanyEs.builder().id(product.getCompanyId()).name(product.getName() + " Seller Name").build())
                //.categoryId(product.getCategoryId())
                //.category(CategoryEs.builder().id(product.getCategoryId()).name("Category Name").build())
                .category(getProductCategory(product.getCategoryId()))
                .features(product.getFeatures())
                .active(product.isActive())
                .priceMapEs(product.getPriceMap())
                // TODO: Hier muss man noch eine Ordersystem integrieren, um die preis umzuwandeln.
                .images(product.getProductImage().stream().map(img->img.getUrl()).toList())
                /*.images(product.getProductImage().stream()
                        .map(ProductImage::getUrl)
                        .collect(Collectors.toList()))
                 */
                .build();
        return productEsRepository.save(productEs);
    }

    public CategoryEs getProductCategory(String categoryId){
        Category category = categoryService.getById(categoryId);
        return CategoryEs.builder()
                .name(category.getName())
                .id(category.getId())
                .code(category.getCode())
                .build();
    }

    public Flux<ProductEs> findAll(){
        return productEsRepository.findAll();
    }

    public Mono<ProductEs> findById(String id){
        return productEsRepository.findById(id);
    }
}
