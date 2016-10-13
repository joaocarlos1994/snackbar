/*
 * snackbar 1.0 11 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.cart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.domain.stock.Stock;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 11 de out de 2016
 */
public class Cart {

	private final List<Product> productsCart;

	public Cart() {
		super();
		this.productsCart = new ArrayList<Product>();
	}

	public void addItemCart(final Product product) {
		this.productsCart.add(product);
	}

	public void removeItemCart(final Product product) {
		if (productsCart.contains(product)) {
			productsCart.remove(product);
		} else {
			new IllegalArgumentException("Error remove Cart item");
		}
	}

	public List<Product> getProductsCart() {
		return Collections.unmodifiableList(productsCart);
	}

	public double priceTotalCart() {

		double total = 0;

		for (final Product productItem : productsCart) {
			total += productItem.getPrice();
		}

		return total;
	}

}
