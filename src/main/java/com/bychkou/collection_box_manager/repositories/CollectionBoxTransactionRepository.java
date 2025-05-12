package com.bychkou.collection_box_manager.repositories;

import com.bychkou.collection_box_manager.models.CollectionBoxTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionBoxTransactionRepository extends JpaRepository<CollectionBoxTransaction, Long> {
}