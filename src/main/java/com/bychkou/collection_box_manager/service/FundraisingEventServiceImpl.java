package com.bychkou.collection_box_manager.service;

import com.bychkou.collection_box_manager.dto.FinancialReportItemDto;
import com.bychkou.collection_box_manager.dto.FundraisingEventRequestDto;
import com.bychkou.collection_box_manager.dto.FundraisingEventResponseDto;
import com.bychkou.collection_box_manager.mapper.FundraisingEventMapper;
import com.bychkou.collection_box_manager.model.FundraisingEvent;
import com.bychkou.collection_box_manager.repository.FundraisingEventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FundraisingEventServiceImpl implements FundraisingEventService {

	private final FundraisingEventRepository eventRepository;

	@Override
	public FundraisingEventResponseDto createFundraisingEvent(FundraisingEventRequestDto dto) {
		FundraisingEvent event = FundraisingEventMapper.toEntity(dto);
		return FundraisingEventMapper.toDto(eventRepository.save(event));
	}

	@Override
	public List<FinancialReportItemDto> getFinancialReport() {
		return eventRepository.findAll().stream()
				.map(event -> FinancialReportItemDto.builder()
						.name(event.getName())
						.amount(event.getAccountBalance())
						.currency(event.getCurrency())
						.build())
				.collect(Collectors.toList());
	}
}