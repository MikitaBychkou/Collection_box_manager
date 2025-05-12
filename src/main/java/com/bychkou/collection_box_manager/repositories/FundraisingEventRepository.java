package com.bychkou.collection_box_manager.repositories;

import com.bychkou.collection_box_manager.models.FundraisingEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundraisingEventRepository extends JpaRepository<FundraisingEvent, Long> {
}