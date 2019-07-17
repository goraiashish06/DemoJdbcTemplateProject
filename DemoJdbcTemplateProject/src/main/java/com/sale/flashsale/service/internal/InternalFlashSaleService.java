package com.sale.flashsale.service.internal;

import com.sale.flashsale.model.FlashSale;
import com.sale.flashsale.model.Product;
import com.sale.flashsale.pojo.FlashSaleResult;

public interface InternalFlashSaleService {
	/**
     * Initiates a flashsale for a given product, and prepares for accepting registration
     *
     * param p, the product to start a sale for
     * @return {@link FlashSale}
     */
	FlashSaleResult createFlashSale(final Product p);

    /**
     * Pre-processes registration for a given flashsale. Caches product information and buyer
     * details for easy access. A purchase order will go through only if this has been done.
     * This also marks the start of the sale.
     *
     * @param f, given flashsale
     * @return a {@link Boolean} indicating if sale was started successfully.
     */
    Boolean startFlashSale(final FlashSale f);

    /**
     * Stops sale, prohibiting any transaction to go in.
     * @param f, input flashsale
     * @return a {@link Boolean} indicating if sale was stopped successfully.
     */
    Boolean stopFlashSale(final FlashSale f);

}
