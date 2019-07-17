package com.sale.flashsale.controller.internal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sale.flashsale.model.FlashSale;
import com.sale.flashsale.model.Product;
import com.sale.flashsale.pojo.FlashSaleResult;
import com.sale.flashsale.service.internal.InternalFlashSaleService;

@RestController
@RequestMapping(value = "/internal")
public class FlashSaleInternalController {

	@Autowired
	InternalFlashSaleService service;

	@RequestMapping(value = "/createflashsale", method = RequestMethod.POST)
	public FlashSaleResult createFlashSale(@RequestBody Product p) {

		return service.createFlashSale(p);
	}

	@RequestMapping(value = "/stopflashsale/", method = RequestMethod.POST)
	public Boolean stopFlashSale(@RequestBody FlashSale f) {
		return service.stopFlashSale(f);
	}

	@RequestMapping(value = "/startflashsale/", method = RequestMethod.POST)
	public Boolean startFlashSale(@RequestBody FlashSale f) {

		return service.startFlashSale(f);
	}

}
