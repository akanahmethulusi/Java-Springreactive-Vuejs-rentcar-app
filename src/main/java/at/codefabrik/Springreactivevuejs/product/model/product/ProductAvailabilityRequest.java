package at.codefabrik.Springreactivevuejs.product.model.product;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ProductAvailabilityRequest {
    public String productId;
    public LocalDateTime pickupDate;
    public LocalDateTime returnDate;
}
