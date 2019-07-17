package com.sale.flashsale.service;

import java.util.List;


import com.sale.flashsale.pojo.PurchaseResult;
import com.sale.flashsale.pojo.RegistrationResult;

public interface FlashSaleService {

	/**
     * Invoked by individual buyers before the flashsale has started.
    *
    * @param flashsaleId, id of the flashsale to register for
    * @param buyerId, id of the buyer
    * @return {@link RegistrationResult} object. Returns a failure if buyer is already registered or
    * flashsale has started already.
    */
   RegistrationResult register(final Integer flashsaleId, final Integer buyerId);

   /**
    * Eligibility checking for buyer. Hits cache.
    * Returns False if the flashsale has stopped/registration has been closed/buyer is not registered/
    * buyer has already purchased the item/inventory is out of stock.
    *
    * @param flashsaleId, id of the flashsale to check against
    * @param buyerId, id of the buyer
    * @return a {@link Boolean}, indicating if the buyer is eligible to buy the product.
    */
   Boolean isEligible(final Integer flashsaleId, final Integer buyerId);

   /**
    * A purchase request is placed by buyer for a given flashsale. This API checks if the buyer
    * is eligible to buy the product (logic same as above). If yes, places the order. Otherwise,
    * returns a failure result
    *
    * @param flashsaleId, id of the flashsale to check against
    * @param buyerId, id of the buyer
    * @return a {@link PurchaseResult}, holding information about buyer's purchase.
    */
   PurchaseResult purchase(final Integer flashsaleId, final Integer buyerId, final Integer productId);
   
   
   
   /**
    * This method checks for the stock availability
    * @param productId
    * @return a {@link Boolean}, indicating if the stock is available to buy the product.
    */
   public Boolean checkStock(Integer productId);

}
