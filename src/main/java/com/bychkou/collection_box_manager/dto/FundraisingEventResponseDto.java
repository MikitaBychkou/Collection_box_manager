package com.bychkou.collection_box_manager.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundraisingEventResponseDto {
	private Long eventId;
	private String name;
	private String currency;
	private BigDecimal accountBalance;
}
