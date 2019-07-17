package com.sale.flashsale.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sale.flashsale.pojo.PurchaseResult;
import com.sale.flashsale.DemoJdbcTemplateProjectApplication;
import com.sale.flashsale.model.Product;
import com.sale.flashsale.model.Registration;
import com.sale.flashsale.pojo.RegistrationResult;
import com.sale.flashsale.repository.RegistrationRepo;
import com.sale.flashsale.service.FlashSaleServiceImpl;

@RestController
public class SaleController {

	private static final Logger logger = LogManager.getLogger(SaleController.class);
	@Autowired
	FlashSaleServiceImpl service;

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public RegistrationResult registerCustomer(@RequestParam Integer buyerId, @RequestParam Integer flashSaleId) {

		logger.info("- Register new cust method is invoked.");
		return service.register(buyerId, flashSaleId);
	}

	@RequestMapping(value = "/sale/", method = RequestMethod.POST)
	public PurchaseResult processSale(@RequestParam Integer buyerId, @RequestParam Integer flashSaleId,
			@RequestParam Integer productId) {

		logger.info("- Inside Sale controller");
		PurchaseResult pr = service.purchase(flashSaleId, buyerId, productId);
		return pr;
	}

	@RequestMapping(value = "/getProducts", method = RequestMethod.GET)
	public List<Product> getProductsForFlashSale() {

		logger.info("FInd Products available for flashsale");
		return service.getProducts();
	}
}
