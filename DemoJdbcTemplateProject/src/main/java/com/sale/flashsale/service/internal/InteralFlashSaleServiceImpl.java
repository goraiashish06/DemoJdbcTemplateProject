package com.sale.flashsale.service.internal;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.sale.flashsale.repository.FlashSaleRepository;
import com.sale.flashsale.repository.ProductRepository;
import com.sale.flashsale.controller.SaleController;
import com.sale.flashsale.model.FlashSale;
import com.sale.flashsale.model.Product;
import com.sale.flashsale.pojo.FlashSaleResult;

public class InteralFlashSaleServiceImpl implements InternalFlashSaleService {

	@Autowired
	FlashSaleRepository flashSaleRepository;

	@Autowired
	ProductRepository productRepo;

	private static final Logger logger = LogManager.getLogger(InteralFlashSaleServiceImpl.class);

	@Override
	public FlashSaleResult createFlashSale(final Product p) {
		FlashSale f = new FlashSale();
		FlashSaleResult fr = new FlashSaleResult();
		boolean flag = false;

		f.setProduct_id(p.getId());
		f.setStatus(Boolean.FALSE);
		f.setRegistrationOpen(Boolean.TRUE);
		fr.setId(0);
		fr.setMessage("invalid parameters");
		logger.info("Flashsale creation starts ");
		// get the product details here
		if (p.getId() != null && p.getPrice() != null && p.getSku() != null) {
			int productResult = productRepo.saveProductDetailsForFlashSale(p);
			if (productResult > 0) {
				flag = Boolean.TRUE;
			}
		} else {
			fr.setId(0);
			fr.setMessage("Product not saved");
			return fr;
		}

		if (flag && flashSaleRepository.createFlashsale(p, f) > 0) {
			fr.setId(1);
			fr.setMessage("Flash Sale Created");
			return fr;
		}
		return fr;
	}

	@Override
	public Boolean startFlashSale(FlashSale f) {
		logger.info("Flashsale starts ");
		if (f != null) {
			FlashSale flashSaleById = flashSaleRepository.getFlashSaleById(f.getId());
			logger.info("Flashsale starts with id:: ", flashSaleById);
			if (flashSaleById != null && !f.getStatus()) {
				f.setStatus(Boolean.TRUE);
				f.setRegistrationOpen(Boolean.FALSE);
				int i = flashSaleRepository.startFlashsale(f);

				if (i > 0) {
					return Boolean.TRUE;
				}
			} else {
				logger.info("Flash Sale not present or activated ");
			}
		}

		return Boolean.FALSE;
	}

	@Override
	public Boolean stopFlashSale(FlashSale f) {
		logger.info("Flashsale stops ");
		if (f != null) {
			f.setStatus(Boolean.FALSE);
			int j = flashSaleRepository.stopFlashsale(f);
			if (j > 0) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
}
