/*
 * snackbar 1.0 14 de out de 2016
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

import br.com.hyperclass.snackbar.domain.menu.Menu;
import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.restapi.wrapper.ProductWrapper;
import br.com.hyperclass.snackbar.restapi.wrapper.ProductsWrapper;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 14 de out de 2016
 */
@RestController
public class MenuController {

	private final Menu menu;

	@Autowired
	public MenuController(final Menu menu) {
		super();
		this.menu = menu;
	}

	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public ResponseEntity<ProductsWrapper> menuItemProducts() {
		return new ResponseEntity<>(new ProductsWrapper(menu.getProducts()), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/menu", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductWrapper> registerProduct(@RequestBody final ProductWrapper productWrapper){
		final Product product = new Product(productWrapper.getName(), productWrapper.getPrice());
		menu.addProductMenu(product);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/menu/", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<ProductWrapper> deleteProduct(@RequestBody final ProductWrapper productWrapper){
		final Product product = new Product(productWrapper.getName(), productWrapper.getPrice());
		menu.removeProductMenu(product);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
