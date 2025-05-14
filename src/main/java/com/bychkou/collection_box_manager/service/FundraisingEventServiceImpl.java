package com.bychkou.collection_box_manager.service;

import com.bychkou.collection_box_manager.dto.FinancialReportItemDto;
import com.bychkou.collection_box_manager.dto.FundraisingEventRequestDto;
import com.bychkou.collection_box_manager.dto.FundraisingEventResponseDto;
import com.bychkou.collection_box_manager.mapper.FundraisingEventMapper;
import com.bychkou.collection_box_manager.model.FundraisingEvent;
import com.bychkou.collection_box_manager.repository.FundraisingEventRepository;
import com.bychkou.collection_box_manager.validator.FundraisingEventValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FundraisingEventServiceImpl implements FundraisingEventService {

	private final FundraisingEventRepository eventRepository;
	private final FundraisingEventValidator validator;

	@Override
	@Transactional
	public FundraisingEventResponseDto createFundraisingEvent(FundraisingEventRequestDto fundraisingEventRequestDto) {
		validator.validate(fundraisingEventRequestDto);
		FundraisingEvent event = FundraisingEventMapper.dtoToEntity(fundraisingEventRequestDto);
		return FundraisingEventMapper.entityToDto(eventRepository.save(event));
	}

	@Override
	@Transactional(readOnly = true)
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