package com.bychkou.collection_box_manager.mapper;

import com.bychkou.collection_box_manager.dto.CollectionBoxResponseDto;
import com.bychkou.collection_box_manager.model.CollectionBox;

public class CollectionBoxMapper {

	public static CollectionBoxResponseDto entityToDto(CollectionBox box) {
		return CollectionBoxResponseDto.builder()
				.boxId(box.getBoxId())
				.assigned(box.isAssigned())
				.empty(box.isEmpty())
				.build();
	}
}
