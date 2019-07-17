package com.sale.flashsale.model;

public class Registration {
	

	public Registration(Integer id, Integer flashSaleId, Integer buyerId, String registrationStatus) {
		super();
		this.id = id;
		this.flashSaleId = flashSaleId;
		this.buyerId = buyerId;
		this.registrationStatus = registrationStatus;
	}
	public Registration() {
		
	}
	private Integer id;

	private Integer flashSaleId;

	private Integer buyerId;

	private String registrationStatus;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRegistrationStatus() {
		return registrationStatus;
	}

	public void setRegistrationStatus(String registrationStatus) {
		this.registrationStatus = registrationStatus;
	}

	public Integer getFlashSaleId() {
		return flashSaleId;
	}

	public void setFlashSaleId(Integer flashSaleId) {
		this.flashSaleId = flashSaleId;
	}

	public Integer getBuyerId() {
		return buyerId;
	}

	public void setBuyerId(Integer buyerId) {
		this.buyerId = buyerId;
	}

}
