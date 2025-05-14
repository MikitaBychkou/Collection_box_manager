package com.bychkou.collection_box_manager.controller;

import com.bychkou.collection_box_manager.dto.*;
import com.bychkou.collection_box_manager.service.CollectionBoxService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/collection-boxes")
public class CollectionBoxController {

	private final CollectionBoxService collectionBoxService;

	@PostMapping
	public ResponseEntity<CollectionBoxResponseDto> registerBox() {
		return ResponseEntity.ok(collectionBoxService.registerNewBox());
	}

	@GetMapping
	public ResponseEntity<List<CollectionBoxResponseDto>> getAllBoxes() {
		return ResponseEntity.ok(collectionBoxService.getAllBoxes());
	}

	@DeleteMapping("/{boxId}")
	public ResponseEntity<Void> unregisterBox(@PathVariable Long boxId) {
		collectionBoxService.unregisterBox(boxId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{boxId}/assign")
	public ResponseEntity<Void> assignBox(@PathVariable Long boxId, @RequestBody CollectionBoxRequestDto collectionBoxRequestDto) {
		collectionBoxService.assignBox(boxId, collectionBoxRequestDto.getFundraisingEventId());
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{boxId}/add-money")
	public ResponseEntity<Void> addMoney(@PathVariable Long boxId, @RequestBody AddMoneyRequestDto addMoneyRequestDto) {
		collectionBoxService.addMoney(boxId, addMoneyRequestDto);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{boxId}/empty")
	public ResponseEntity<Void> emptyBox(@PathVariable Long boxId) {
		collectionBoxService.emptyBox(boxId);
		return ResponseEntity.ok().build();
	}
}
