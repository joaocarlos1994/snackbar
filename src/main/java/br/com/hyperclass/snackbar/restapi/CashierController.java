/*
 * snackbar 1.0 19 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hyperclass.snackbar.domain.cashier.Cashier;
import br.com.hyperclass.snackbar.restapi.wrapper.PaySaleWrapper;
import br.com.hyperclass.snackbar.restapi.wrapper.ProductsWrapper;
import br.com.hyperclass.snackbar.restapi.wrapper.SalesDateWrapper;
import br.com.hyperclass.snackbar.restapi.wrapper.SalesReportWrapper;

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
	public ResponseEntity<SalesReportWrapper> saleForDate(@RequestBody final SalesDateWrapper salesDateWrapper) {
		return new ResponseEntity<>(new SalesReportWrapper(cashier.saleByPeriod(salesDateWrapper.getDateInitial(), salesDateWrapper.getDateInitial())), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cashier/order", method = RequestMethod.GET)
	public ResponseEntity<ProductsWrapper> orderItemProducts() {
		return new ResponseEntity<ProductsWrapper>(new ProductsWrapper(cashier.productsInCashier()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/cashier/checkout", method = RequestMethod.POST)
	public ResponseEntity<PaySaleWrapper> checkout(@RequestBody final PaySaleWrapper paySaleWrapper) throws Exception {
		cashier.checkout(paySaleWrapper.getMoney(), paySaleWrapper.getTypeSale());
		return new ResponseEntity<PaySaleWrapper>(HttpStatus.OK);	
	}
}
