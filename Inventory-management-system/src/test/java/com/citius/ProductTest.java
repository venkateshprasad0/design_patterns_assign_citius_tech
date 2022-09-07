package com.citius;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.citius.domain.ProductDO;
import com.citius.repo.ProductRepo;
import com.citius.service.ProductService;

@SpringBootTest
class ProductTest {

	@Autowired
	private ProductService productService;

	@MockBean
	private ProductRepo productRepo;

	@Test
	void testAddProduct() {
		ProductDO productDO = new ProductDO(1, "CT_PDT1", 20.0, 2000.0, new Date(), 6, null);
		when(productRepo.save(productDO)).thenReturn(productDO);
		assertThat(productService.saveProduct(productDO), is(equalTo(productDO)));
	}

	@Test
	void testFINDAllProducts() {
		ProductDO productDO = new ProductDO(1, "CT_PDT1", 20.0, 2000.0, new Date(), 6, null);
		ProductDO productDO2 = new ProductDO(1, "CT_PDT2", 40.0, 4000.0, new Date(), 6, null);

		List<ProductDO> detailsDOs = new LinkedList<>();
		detailsDOs.add(productDO);
		detailsDOs.add(productDO2);

		when(productRepo.findAll()).thenReturn(detailsDOs);
		assertThat(productService.findAll(), is(equalTo(detailsDOs)));
	}

	@Test
	void testDeleteProduct() {
		Optional<ProductDO> productDO = Optional.of(new ProductDO(1, "CT_PDT1", 20.0, 2000.0, new Date(), 6, null));
		when(productRepo.findById(1)).thenReturn(productDO);
		when(productRepo.existsById(productDO.get().getId())).thenReturn(false);
		assertFalse(productRepo.existsById(productDO.get().getId()));

	}
}
