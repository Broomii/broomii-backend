package Boormii.soonDelivery.orders.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class DeliveryStatusEditRequestDto {

    @NotNull
    Long id;

    @NotNull
    String deliveryStatus;
}
