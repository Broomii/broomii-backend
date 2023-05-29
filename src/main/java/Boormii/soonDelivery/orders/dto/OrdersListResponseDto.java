package Boormii.soonDelivery.orders.dto;

import Boormii.soonDelivery.orders.domain.DeliveryStatus;
import Boormii.soonDelivery.orders.domain.Orders;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Data
public class OrdersListResponseDto {

    @NotNull
    private String title;

    @NotNull
    private String nickName;

    @NotNull
    private Long id;

    @NotNull
    private String deliveryAddress;

    @NotNull
    private Integer deliveryPay;

    @NotNull
    private DeliveryStatus deliveryStatus;

    @NotNull
    private String createTime;

    public static OrdersListResponseDto getOrdersList(Orders orders) {
        OrdersListResponseDto ordersListResponseDto = new OrdersListResponseDto();
        ordersListResponseDto.title = orders.getTitle();
        if (orders.getMembers() == null) {
            ordersListResponseDto.nickName = "알 수 없음";
        } else {
            ordersListResponseDto.nickName = orders.getMembers().getNickName();
        }
        ordersListResponseDto.id = orders.getId();
        ordersListResponseDto.deliveryAddress = orders.getDeliveryAddress();
        ordersListResponseDto.deliveryStatus = orders.getDeliveryStatus();
        ordersListResponseDto.deliveryPay = orders.getDeliveryPay();
        ordersListResponseDto.createTime = orders.getCreateTime().format(DateTimeFormatter.ofPattern("yyyy년, MM월, dd일, HH시, mm분"));

        return ordersListResponseDto;
    }
}
