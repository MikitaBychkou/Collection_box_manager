package com.bychkou.collection_box_manager.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddMoneyRequestDto {
	private Long boxId;
	private BigDecimal amount;
	private String currency;
}