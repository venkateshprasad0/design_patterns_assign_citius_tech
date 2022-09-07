package com.citius.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citius.domain.ProductDO;
import com.citius.repo.ProductRepo;

@Service
@Transactional
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	public ProductDO saveProduct(ProductDO productDO) {
		return productRepo.save(productDO);
	}

	public List<ProductDO> findAll() {
		return productRepo.findAll();
	}

	
	public void removeProduct(ProductDO x) {
		productRepo.delete(x);
	}

}
