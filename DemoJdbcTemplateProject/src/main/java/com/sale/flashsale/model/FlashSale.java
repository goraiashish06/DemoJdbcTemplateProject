package com.sale.flashsale.model;

import java.util.Date;

public class FlashSale {

	private Integer id;

	private Integer product_id;

	private Boolean status;

	private Date flashSlaeDate;
	
	private Boolean registrationOpen;

	public FlashSale() {

	}

	public FlashSale(Integer id, Integer product_id, Boolean status, Date flashSlaeDate, Boolean registrationOpen) {
		super();
		this.id = id;
		this.product_id = product_id;
		this.status = status;
		this.flashSlaeDate = flashSlaeDate;
		this.registrationOpen = registrationOpen;
	}

	public Date getFlashSlaeDate() {
		return flashSlaeDate;
	}

	public void setFlashSlaeDate(Date flashSlaeDate) {
		this.flashSlaeDate = flashSlaeDate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Integer product_id) {
		this.product_id = product_id;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getRegistrationOpen() {
		return registrationOpen;
	}

	public void setRegistrationOpen(Boolean registrationOpen) {
		this.registrationOpen = registrationOpen;
	}

}
