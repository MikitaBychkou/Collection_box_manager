package com.bychkou.collection_box_manager.repository;

import com.bychkou.collection_box_manager.model.CollectionBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CollectionBoxRepository extends JpaRepository<CollectionBox, Long> {
}
