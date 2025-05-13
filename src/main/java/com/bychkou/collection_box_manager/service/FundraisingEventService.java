package com.bychkou.collection_box_manager.service;

import com.bychkou.collection_box_manager.dto.FinancialReportItemDto;
import com.bychkou.collection_box_manager.dto.FundraisingEventRequestDto;
import com.bychkou.collection_box_manager.dto.FundraisingEventResponseDto;

import java.util.List;

public interface FundraisingEventService {
	FundraisingEventResponseDto createFundraisingEvent(FundraisingEventRequestDto dto);
	List<FinancialReportItemDto> getFinancialReport();
}