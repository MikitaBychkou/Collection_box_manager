package com.bychkou.collection_box_manager.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "fundraising_event")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FundraisingEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "event_id")
	private Long eventId;

	@Column(nullable = false)
	private String name;

	@Column(length = 3, nullable = false)
	private String currency;

	@Column(name = "account_balance", nullable = false, precision = 12, scale = 2)
	private BigDecimal accountBalance;

	@OneToMany(mappedBy = "fundraisingEvent", cascade = CascadeType.ALL)
	private List<CollectionBox> collectionBoxes;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof FundraisingEvent)) return false;
		FundraisingEvent fundraisingEvent = (FundraisingEvent) o;
		return Objects.equals(eventId, fundraisingEvent.eventId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(eventId);
	}
}
