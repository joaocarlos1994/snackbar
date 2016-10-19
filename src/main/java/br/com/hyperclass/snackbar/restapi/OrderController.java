/*
 * snackbar 1.0 18 de out de 2016
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

import br.com.hyperclass.snackbar.domain.order.Order;
import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.restapi.wrapper.ProductWrapper;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 18 de out de 2016
 */
@RestController
public class OrderController {
	
	private final Order order;
	
	@Autowired
	public OrderController(final Order order) {
		super();
		this.order = order;
	}

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> menuItemProducts() {
		return order.productsMenu();
	}
	
	@RequestMapping(value = "/order/all", method = RequestMethod.GET)
	public List<Product> orderItemProducts() {
		return order.getProductsOrder();
	}
	
	@RequestMapping(value = "/order/add-item", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public void addItemCart(@RequestBody final ProductWrapper productWrapper){
		final Product product = new Product(productWrapper.getName(), productWrapper.getPrice());
		order.addItemCart(product);
	}
	
}
