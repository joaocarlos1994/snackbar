/*
 * snackbar 1.0 19 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hyperclass.snackbar.domain.cashier.Cashier;
import br.com.hyperclass.snackbar.domain.cashier.events.EventSale;
import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.restapi.wrapper.PaySaleWrapper;
import br.com.hyperclass.snackbar.restapi.wrapper.SalesDateWrapper;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 19 de out de 2016
 */
@RestController
public class CashierController {
	
	private final Cashier cashier;

	@Autowired
	public CashierController(final Cashier cashier) {
		super();
		this.cashier = cashier;
	}
	
	@RequestMapping(value = "/cashier/sale-by-date", method = RequestMethod.POST)
	public Map<Date, List<EventSale>> saleForDate(@RequestBody final SalesDateWrapper salesDateWrapper) {
		return cashier.saleByPeriod(salesDateWrapper.getDateInitial(), salesDateWrapper.getDateFinal());
	}
	
	@RequestMapping(value = "/cashier/order", method = RequestMethod.GET)
	public List<Product> orderItemProducts() {
		return cashier.productsInCashier();
	}
	
	@RequestMapping(value = "/cashier/checkout", method = RequestMethod.POST)
	public void checkout(@RequestBody final PaySaleWrapper paySaleWrapper) {
		cashier.checkout(paySaleWrapper.getMoney(), paySaleWrapper.getTypeSale());
	}
	
	
	
}
