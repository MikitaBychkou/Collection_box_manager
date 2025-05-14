package com.bychkou.collection_box_manager.mapper;


import com.bychkou.collection_box_manager.dto.FundraisingEventRequestDto;
import com.bychkou.collection_box_manager.dto.FundraisingEventResponseDto;
import com.bychkou.collection_box_manager.model.FundraisingEvent;

public class FundraisingEventMapper {

	public static FundraisingEvent dtoToEntity(FundraisingEventRequestDto dto) {
		return FundraisingEvent.builder()
				.name(dto.getName())
				.currency(dto.getCurrency())
				.accountBalance(java.math.BigDecimal.ZERO)
				.build();
	}

	public static FundraisingEventResponseDto entityToDto(FundraisingEvent entity) {
		return FundraisingEventResponseDto.builder()
				.eventId(entity.getEventId())
				.name(entity.getName())
				.currency(entity.getCurrency())
				.accountBalance(entity.getAccountBalance())
				.build();
	}
}