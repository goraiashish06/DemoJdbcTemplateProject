FlashSale Project

Repository created for the falsh sale design Flash Sales
with tables :
Product
FalshSale
Order
Buyer
Registration


Java properties file in src/main/resources/config/flashsale.properties.

h2 url => jdbc:h2:~/test, and this has to be created before running tests.
h2 username => sa
h2 password => 

API

This library contains two public facing APIs:

Internal API is at com.sale.flashsale.controller.FlashSaleInternalController. This is not to be exposed outside.

/internal/createflashsale --> This creates the flash sale and takes the Product details as parameters for which the sale will be conducted.

/internal/startflashsale --> This starts the flashsale and takes flashsale detais from the request body.

/internal/stopflashsale  --> This stops the particular flashsale

External API is at com.sale.flashsale.controller.FlashSaleController.

/register  --> This api registers the user if the flashsale is started and the he is not registered before for the flashsale.

/sale      --> This api is used to sale the product during the flashsale.

/getProducts --> This fetches all the products available for the flashsale.


We have made some assumptions:

While creating the flashsale the admin has to enter the products which he wants for the flashsale. The product details 
includes the name, price and stock informations.
A flashsale holds only 1 product
Buyer can only register if the flashsale is not running.
flashsale is started/stopped by the admin team with the internal apis exposed.
Purchase limit per buyer per flashsale is 1. 
Payment is not implemented.
