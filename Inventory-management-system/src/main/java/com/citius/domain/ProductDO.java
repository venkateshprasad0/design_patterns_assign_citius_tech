package com.citius.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
@Table(name = "Product")
public class ProductDO {

	public ProductDO() {
	}

	public ProductDO(Integer id, String description, Double weight, Double price, Date manufacturingDate,
			Integer userBeforeMonths, Date expiryDate) {
		super();
		this.id = id;
		this.description = description;
		this.weight = weight;
		this.price = price;
		this.manufacturingDate = manufacturingDate;
		this.userBeforeMonths = userBeforeMonths;
		this.expiryDate = expiryDate;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String description;
	private Double weight;
	private Double price;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date manufacturingDate;
	private Integer userBeforeMonths;
	@Temporal(TemporalType.DATE)
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	private Date expiryDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getManufacturingDate() {
		return manufacturingDate;
	}

	public void setManufacturingDate(Date manufacturingDate) {
		this.manufacturingDate = manufacturingDate;
	}

	public Integer getUserBeforeMonths() {
		return userBeforeMonths;
	}

	public void setUserBeforeMonths(Integer userBeforeMonths) {
		this.userBeforeMonths = userBeforeMonths;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	@Override
	public String toString() {
		return "ProductDO [id=" + id + ", description=" + description + ", weight=" + weight + ", price=" + price
				+ ", manufacturingDate=" + manufacturingDate + ", userBeforeMonths=" + userBeforeMonths
				+ ", expiryDate=" + expiryDate + "]";
	}

}
