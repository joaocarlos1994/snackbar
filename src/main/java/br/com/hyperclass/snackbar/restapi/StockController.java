/*
 * snackbar 1.0 27 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.domain.stock.Stock;
import br.com.hyperclass.snackbar.domain.stock.StockException;
import br.com.hyperclass.snackbar.restapi.wrapper.ProductWrapper;
import br.com.hyperclass.snackbar.restapi.wrapper.ProductsWrapper;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 27 de out de 2016
 */
@RestController
public class StockController {
	
	private final Stock stock;

	@Autowired
	public StockController(Stock stock) {
		super();
		this.stock = stock;
	}
	
	@RequestMapping(value = "/stock/add-item", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductWrapper> addItem(@RequestBody final ProductWrapper productWrapper){
		final Product product = new Product(productWrapper.getName(), productWrapper.getPrice());
		stock.addItemStock(product);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/stock/add-item", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductWrapper> removeItem(@RequestBody final ProductWrapper productWrapper) throws StockException{
		final Product product = new Product(productWrapper.getName(), productWrapper.getPrice());
		stock.removeItemStock(product);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stock", method = RequestMethod.GET)
	public ResponseEntity<ProductsWrapper> menuItemProducts() {
		return new ResponseEntity<ProductsWrapper>(new ProductsWrapper(stock.getProducts()), HttpStatus.OK);
	}
	
}
