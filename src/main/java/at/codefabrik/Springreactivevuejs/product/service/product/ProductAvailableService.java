package at.codefabrik.Springreactivevuejs.product.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductAvailableService {
    public int getByProductId(String productId){
        return 10;
    }
}
