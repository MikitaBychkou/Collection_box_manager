package com.bychkou.collection_box_manager.service;

import com.bychkou.collection_box_manager.dto.AddMoneyRequestDto;
import com.bychkou.collection_box_manager.dto.CollectionBoxResponseDto;

import java.util.List;

public interface CollectionBoxService {
	CollectionBoxResponseDto registerNewBox();
	List<CollectionBoxResponseDto> getAllBoxes();
	void unregisterBox(Long boxId);
	void assignBox(Long boxId, Long eventId);
	void addMoney(Long boxId, AddMoneyRequestDto dto);
	void emptyBox(Long boxId);
}
