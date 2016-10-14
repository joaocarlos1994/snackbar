/*
 * snackbar 1.0 14 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.hyperclass.snackbar.domain.menu.Menu;
import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.restapi.wrapper.ProductWrapper;

/**
 * 
 * 
 * @author Jo�o Batista
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
	public List<Product> menuItemProducts() {
		return menu.getProducts();
	}
	
	@RequestMapping(value = "/menu/register", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void registerProduct(@RequestBody final ProductWrapper productWrapper){
		final Product product = new Product(productWrapper.getName(), productWrapper.getPrice());
		menu.addProductMenu(product);
	}
	
	@RequestMapping(value = "/menu/delete", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void deleteProduct(@RequestBody final ProductWrapper productWrapper){
		final Product product = new Product(productWrapper.getName(), productWrapper.getPrice());
		menu.removeProductMenu(product);
	}
	
	@RequestMapping(value = "/menu/add-order", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void addProductOrder(@RequestBody final ProductWrapper productWrapper){
		
		final Product product = new Product(productWrapper.getName(), productWrapper.getPrice());
		menu.addProductCart(product);
	}

}