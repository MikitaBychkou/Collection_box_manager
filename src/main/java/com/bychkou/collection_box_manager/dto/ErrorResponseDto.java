package com.bychkou.collection_box_manager.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponseDto {
	private String errorMessage;
	private LocalDateTime timestamp;
	private int code;
}
