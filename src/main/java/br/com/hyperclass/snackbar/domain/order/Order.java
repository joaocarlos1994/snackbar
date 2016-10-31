/*
 * snackbar 1.0 11 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.hyperclass.snackbar.domain.menu.Menu;
import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.domain.user.UserSnack;

/**
 * A <code>Order</code> tem uma lista produtos, onde e possivel adicionar
 * novos produtos e remover produtos, conforme a vontade do cliente, ela
 * possui funcoes para retornar o valor total dos produtos, como a quantidade
 * de itens que ele tem.
 * 
 * @author João Batista
 * @version 1.0 11 de out de 2016
 */
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private final List<Product> productsOrder;
	private UserSnack user;
	private final Menu menu;

	public Order(final Menu menu) {
		super();
		this.productsOrder = new ArrayList<Product>();
		this.menu = menu;
	}

	public void addItemCart(final Product product) {
		if (menu.productsAvaible(product)) {
			this.productsOrder.add(product);
		}
	}

	public void removeItemCart(final Product product) {
		if (productsOrder.contains(product)) {
			productsOrder.remove(product);
		} else {
			new IllegalArgumentException("Error remove Cart item");
		}
	}

	public List<Product> getProductsOrder() {
		return Collections.unmodifiableList(productsOrder);
	}

	public double priceTotalOrder() {

		double total = 0;

		for (final Product productItem : productsOrder) {
			total += productItem.getPrice();
		}

		return total;
	}

	public int quatityItemOrder(){
		return productsOrder.size();
	}
	
	public List<Product> productsMenu(){
		return menu.getProducts();
	}
	
	public void setUser(UserSnack user) {
		this.user = user;
	}

}
