package com.bychkou.collection_box_manager.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionBoxResponseDto {
	private Long boxId;
	private boolean assigned;
	private boolean empty;
}