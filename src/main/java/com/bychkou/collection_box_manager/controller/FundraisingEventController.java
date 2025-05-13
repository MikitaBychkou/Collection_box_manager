package com.bychkou.collection_box_manager.controller;

import com.bychkou.collection_box_manager.dto.FinancialReportItemDto;
import com.bychkou.collection_box_manager.dto.FundraisingEventRequestDto;
import com.bychkou.collection_box_manager.dto.FundraisingEventResponseDto;
import com.bychkou.collection_box_manager.service.FundraisingEventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fundraising-events")
public class FundraisingEventController {

	private final FundraisingEventService fundraisingEventService;

	@PostMapping
	public ResponseEntity<FundraisingEventResponseDto> create(@RequestBody FundraisingEventRequestDto dto) {
		return ResponseEntity.ok(fundraisingEventService.createFundraisingEvent(dto));
	}

	@GetMapping("/financial-report")
	public ResponseEntity<List<FinancialReportItemDto>> getFinancialReport() {
		return ResponseEntity.ok(fundraisingEventService.getFinancialReport());
	}
}
