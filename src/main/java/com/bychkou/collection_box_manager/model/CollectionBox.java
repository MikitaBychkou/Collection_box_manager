package com.bychkou.collection_box_manager.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "collection_box")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionBox {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "box_id")
	private Long boxId;

	@Column(nullable = false)
	private boolean assigned;

	@Column(nullable = false)
	private boolean empty;

	@ManyToOne
	@JoinColumn(name = "fundraising_event_id")
	private FundraisingEvent fundraisingEvent;

	@OneToMany(mappedBy = "collectionBox", cascade = CascadeType.ALL)
	private List<CollectionBoxTransaction> transactions;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CollectionBox)) return false;
		CollectionBox collectionBox = (CollectionBox) o;
		return Objects.equals(boxId, collectionBox.boxId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(boxId);
	}
}
