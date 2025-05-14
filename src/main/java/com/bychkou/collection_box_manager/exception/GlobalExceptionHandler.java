package com.bychkou.collection_box_manager.exception;


import com.bychkou.collection_box_manager.dto.ErrorResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ErrorResponseDto> handleIllegalArgumentException(IllegalArgumentException e) {
		ErrorResponseDto dto = ErrorResponseDto.builder()
				.errorMessage(e.getMessage())
				.timestamp(LocalDateTime.now())
				.code(HttpStatus.BAD_REQUEST.value())
				.build();

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(dto);
	}

	@ExceptionHandler(NoSuchElementException.class)
	public ResponseEntity<ErrorResponseDto> handleNoSuchElementException(NoSuchElementException e) {
		ErrorResponseDto dto = ErrorResponseDto.builder()
				.errorMessage(e.getMessage())
				.timestamp(LocalDateTime.now())
				.code(HttpStatus.NOT_FOUND.value())
				.build();

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(dto);
	}
}