package com.sale.flashsale.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//import com.flash.flashsale.FlashSaleRepository;
import com.sale.flashsale.repository.*;
import com.sale.flashsale.controller.SaleController;
import com.sale.flashsale.model.Buyer;
//import com.flash.flashsale.model.Customer;
import com.sale.flashsale.model.FlashSale;
import com.sale.flashsale.model.Order;
import com.sale.flashsale.model.OrderStatus;
import com.sale.flashsale.model.Product;
import com.sale.flashsale.model.Registration;
import com.sale.flashsale.pojo.FlashSaleConstant;
import com.sale.flashsale.pojo.PurchaseResult;
import com.sale.flashsale.pojo.RegistrationResult;

@Service
public class FlashSaleServiceImpl implements FlashSaleService {

	@Autowired
	FlashSaleRepository flashsaledao;

	@Autowired
	BuyerRepository buyerdao;

	@Autowired
	RegistrationRepo regdao;

	@Autowired
	FlashSaleRepository fashsaledao;

	@Autowired
	ProductRepository productdao;

	@Autowired
	OrderRepository orderdao;

	private int skuValue;
	private static final Logger logger = LogManager.getLogger(SaleController.class);

	@Override
	@Transactional
	public RegistrationResult register(final Integer flashsaleId, final Integer buyerId) {
		FlashSale f = flashsaledao.getFlashSaleById(flashsaleId);
		Buyer b = buyerdao.getBuyerById(buyerId);
		RegistrationResult registrationResult = new RegistrationResult();
		registrationResult.setStatus(Boolean.FALSE);

		if (f != null) {
			registrationResult.setMessage(FlashSaleConstant.INVALID_FLASHSALE);
			logger.info("Invalid flash sale id");
		} else if (b != null) {
			registrationResult.setMessage(FlashSaleConstant.INVALID_BUYER);
			logger.info("Invalid flash buyer id");
		} else {
			logger.info("User registration begins for the flash sale");
			Registration rr = regdao.getRegisterUser(flashsaleId, buyerId);

			if (rr != null) {
				registrationResult.setMessage(FlashSaleConstant.BUYER_ALREADY_REGISTERED);
				registrationResult.setRegistrationId(rr.getId());
				logger.info("User already registered with id::", rr.getId());
			} else {

				int reg = regdao.registerBuyer(flashsaleId, buyerId, FlashSaleConstant.BUYER_REGISTERED_FOR_SALE);
				if (reg > 0) {
					registrationResult.setMessage(FlashSaleConstant.SUCCESS);
					registrationResult.setStatus(Boolean.TRUE);
					logger.info("User registered with id::", reg);
				}
			}
		}
		return registrationResult;
	}

	@Override
	public Boolean isEligible(Integer flashsaleId, Integer buyerId) {
		Registration rr = regdao.getRegisterUser(flashsaleId, buyerId);

		if (rr != null) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}

	}

	@Override
	@Transactional
	public PurchaseResult purchase(Integer flashsaleId, Integer buyerId, Integer productId) {
		PurchaseResult pr = new PurchaseResult();

		Boolean isEligible = isEligible(flashsaleId, buyerId);
		Order order = new Order();
		synchronized (this) {
			logger.info("Purchase starts for the buyer with id:", buyerId);
			if (checkStock(productId)) { // Checking the sku value before the purchase
				if (isEligible) {
					this.skuValue = this.skuValue - 1;
					Product pp = new Product();
					pp.setId(productId);
					pp.setSku(this.skuValue);

					int productStatus = productdao.saveProductDetailsForFlashSale(pp);
					order.setBuyerId(buyerId);
					order.setProduct(productId);
					order.setCreatedAt(new Date());

					pr.setBuyerId(buyerId);
					pr.setProductId(productId);
					if (productStatus > 0) {
						// Product is updated with the current sku
						order.setOrderStatus(FlashSaleConstant.APPROVED);
						int orderId = orderdao.saveOrder(order);
						if (orderId > 0) {
							logger.info("Order saved with id:", orderId);
							pr.setStatus(Boolean.TRUE);
							pr.setMessage(FlashSaleConstant.APPROVED);
						}

					} else {
						order.setOrderStatus(FlashSaleConstant.CANCELLED);
						int orderId = orderdao.saveOrder(order);
						if (orderId > 0) {
							logger.info("Order in Cancelled state");
						}

						pr.setStatus(Boolean.FALSE);
						pr.setMessage(FlashSaleConstant.CANCELLED);
					}

				} else {
					// Buyer is not registered to buy the flashsale product
					pr.setStatus(Boolean.FALSE);
					pr.setBuyerId(buyerId);
					pr.setMessage(FlashSaleConstant.BUYER_NOT_REGISTERED_FOR_SALE);
				}
			} else {
				logger.info("No stock available");
				pr.setStatus(Boolean.FALSE);
				pr.setBuyerId(buyerId);
				pr.setMessage(FlashSaleConstant.PRODUCT_OUT_OF_STOCK);

			}

		}

		return pr;

	}

	@Override
	public synchronized Boolean checkStock(Integer productId) {
		Product pr = productdao.getProductById(productId);
		this.skuValue = pr.getSku();

		if (this.skuValue > 0) {
			return Boolean.TRUE;
		} else {
			return Boolean.FALSE;
		}
	}

	// Method to fetch all the products available for flash sale
	public List<Product> getProducts() {

		return productdao.getAllProducts();
	}
}