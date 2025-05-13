package com.bychkou.collection_box_manager.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinancialReportItemDto {
	private String name;
	private BigDecimal amount;
	private String currency;
}