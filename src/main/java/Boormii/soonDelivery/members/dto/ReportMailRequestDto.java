package Boormii.soonDelivery.members.dto;

import lombok.Data;

@Data
public class ReportMailRequestDto {
    private String target;
    private Long orderId;
}
