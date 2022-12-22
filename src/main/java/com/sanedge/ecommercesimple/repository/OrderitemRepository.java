package com.sanedge.ecommercesimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanedge.ecommercesimple.models.OrderItem;

@Repository
public interface OrderitemRepository extends JpaRepository<OrderItem, Long> {

}
