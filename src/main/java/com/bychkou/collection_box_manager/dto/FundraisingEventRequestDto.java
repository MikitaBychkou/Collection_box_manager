package com.bychkou.collection_box_manager.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundraisingEventRequestDto {
	private String name;
	private String currency;
}