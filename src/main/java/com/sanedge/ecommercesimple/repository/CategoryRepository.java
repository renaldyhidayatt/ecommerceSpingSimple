package com.sanedge.ecommercesimple.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanedge.ecommercesimple.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
