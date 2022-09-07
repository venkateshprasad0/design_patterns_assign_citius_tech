package com.citius.controller;

import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citius.controller.utility.ProductControllerUtility;
import com.citius.domain.ProductDO;
import com.citius.dto.ProductDTO;
import com.citius.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductControllerUtility controllerUtility;

	@PostMapping("/addProduct")
	public ProductDTO addProduct(@RequestBody ProductDTO productDTO) {
		return controllerUtility
				.productDOConv(productService.saveProduct(controllerUtility.productDTOConv(productDTO)));
	}

	@DeleteMapping("/removeExpiredItems")
	public String removeExpiredProducts() {
		List<ProductDO> productDOs =  controllerUtility.sortExpiredProducts(productService.findAll());
		int count = productDOs.size();
		productDOs.forEach(x->productService.removeProduct(x));
		return count+" Expired Items removed";
	}

	@GetMapping("/getSortedProducts")
	public List<ProductDTO> getSortedProducts() {
		List<ProductDO> productDOs = productService.findAll().stream()
				.sorted(Comparator.comparing(ProductDO::getExpiryDate).reversed()).collect(Collectors.toList());
		List<ProductDTO> productDTOs = new LinkedList<>();
		productDOs.forEach(x -> productDTOs.add(controllerUtility.productDOConv(x)));
		return productDTOs;
	}

	@SuppressWarnings("static-access")
	@GetMapping("/discountedProducts")
	public List<ProductDTO> getDiscountedProducts() {
		Date startDate = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.add(calendar.MONTH, 6);
		Date endDate = calendar.getTime();
		List<ProductDO> productDOs = productService.findAll().stream()
				.filter(dates -> dates.getExpiryDate().after(startDate) && dates.getExpiryDate().before(endDate))
				.peek(x -> x.setPrice(x.getPrice() - ((x.getPrice() * 20) / 100))).collect(Collectors.toList());
		List<ProductDTO> productDTOs = new LinkedList<>();
		productDOs.forEach(x -> productDTOs.add(controllerUtility.productDOConv(x)));
		return productDTOs;
	}

}
