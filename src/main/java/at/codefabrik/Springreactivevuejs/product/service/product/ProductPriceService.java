package at.codefabrik.Springreactivevuejs.product.service.product;

import at.codefabrik.Springreactivevuejs.product.entity.MoneySymbol;
import at.codefabrik.Springreactivevuejs.product.repo.mongo.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ProductPriceService {
    private final ProductRepository productRepository;

    public BigDecimal getByMoneyType(String id, MoneySymbol moneySymbol) {
        return BigDecimal.TEN;
    }
}
