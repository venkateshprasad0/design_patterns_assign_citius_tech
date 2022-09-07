package com.citius.controller.utility;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.stereotype.Component;

import com.citius.domain.ProductDO;
import com.citius.dto.ProductDTO;

@Component
public class ProductControllerUtility {

	public ProductDO productDTOConv(ProductDTO productDTO) {
		return new ProductDO(productDTO.getId(), productDTO.getDescription(), productDTO.getWeight(),
				productDTO.getPrice(), productDTO.getManufacturingDate(), productDTO.getUserBeforeMonths(),
				calculateExpiryDate(productDTO.getManufacturingDate(), productDTO.getUserBeforeMonths()));
	}

	public Date calculateExpiryDate(Date manufacturingDate, Integer userBeforeMonths) {
		return DateUtils.addMonths(manufacturingDate, userBeforeMonths);
	}

	public ProductDTO productDOConv(ProductDO productDO) {
		return new ProductDTO(productDO.getId(), productDO.getDescription(), productDO.getWeight(),
				productDO.getPrice(), productDO.getManufacturingDate(), productDO.getUserBeforeMonths(),
				calculateExpiryDate(productDO.getManufacturingDate(), productDO.getUserBeforeMonths()));
	}

	public List<ProductDO> sortExpiredProducts(List<ProductDO> products) {
		return products.stream().filter(x -> x.getExpiryDate().before(new Date())).collect(Collectors.toList());
	}

}
