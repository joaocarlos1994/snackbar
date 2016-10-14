/*
 * snackbar 1.0 11 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.order;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.hyperclass.snackbar.domain.product.Product;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 11 de out de 2016
 */
public class Order {

	private final List<Product> productsOrder;

	public Order() {
		super();
		this.productsOrder = new ArrayList<Product>();
	}

	public void addItemCart(final Product product) {
		this.productsOrder.add(product);
	}

	public void removeItemCart(final Product product) {
		if (productsOrder.contains(product)) {
			productsOrder.remove(product);
		} else {
			new IllegalArgumentException("Error remove Cart item");
		}
	}

	public List<Product> getProductsCart() {
		return Collections.unmodifiableList(productsOrder);
	}

	public double priceTotalCart() {

		double total = 0;

		for (final Product productItem : productsOrder) {
			total += productItem.getPrice();
		}

		return total;
	}

}
