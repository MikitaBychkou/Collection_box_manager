package com.bychkou.collection_box_manager.models;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "collection_box_transaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CollectionBoxTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "transaction_id")
	private Long transactionId;

	@ManyToOne
	@JoinColumn(name = "collection_box_id", nullable = false)
	private CollectionBox collectionBox;

	@Column(length = 3, nullable = false)
	private String currency;

	@Column(nullable = false, precision = 12, scale = 2)
	private BigDecimal amount;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CollectionBoxTransaction)) return false;
		CollectionBoxTransaction collectionBoxTransaction = (CollectionBoxTransaction) o;
		return Objects.equals(transactionId, collectionBoxTransaction.transactionId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(transactionId);
	}
}
