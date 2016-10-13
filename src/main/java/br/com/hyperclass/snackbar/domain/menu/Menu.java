/*
 * snackbar 1.0 13 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.menu;

import java.util.Collections;
import java.util.List;

import br.com.hyperclass.snackbar.domain.cart.Cart;
import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.domain.stock.Stock;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 13 de out de 2016
 */
public class Menu {

	private final List<Product> products;
	private Cart cart;
	private final Stock stock;

	public Menu(final List<Product> products, final Stock stock) {
		super();
		this.products = products;
		this.stock = stock;
		
	}

	public void addProductMenu(final Product product) {

		if (products.contains(product)) {
			this.products.add(product);
		} else {
			new IllegalArgumentException("Object exist in list");
		}
	}

	public void removeProductMenu(final Product product) {

		if (products.contains(product)) {
			this.products.remove(product);
		} else {
			new IllegalArgumentException("Object not exist in list");
		}
	}

	public void addProductCart(final Product product){
		if(products.contains(product)){
			this.cart.addItemCart(product);
		}
	}

	public List<Product> getProducts() {
		return Collections.unmodifiableList(products);
	}
}
