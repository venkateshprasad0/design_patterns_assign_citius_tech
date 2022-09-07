package com.citius.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.citius.domain.ProductDO;

@Repository
public interface ProductRepo extends JpaRepository<ProductDO, Integer> {

}
