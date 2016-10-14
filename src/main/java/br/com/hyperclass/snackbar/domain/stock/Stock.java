/*
 * snackbar 1.0 13 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.hyperclass.snackbar.domain.product.Product;

/**
 * 
 * 
 * @author Jo�o Batista
 * @version 1.0 13 de out de 2016
 */
public class Stock {
	
	private final List<Product> products;
	
	public Stock() {
		super();
		this.products = new ArrayList<>();
	}
	
	public void addItemStock(final Product product){
		this.products.add(product);
	}

	public void removeItemStock(final Product product){
		if (contains(product)) {
			products.remove(product);
		}
	}
	
	public boolean contains(final Product product) {
		if (products.contains(product)) {
			return true;
		}
		return false;
	}
	
	public double quantityStockItem(final Product product){
		int quantity = 0;
		for (final Product productItem : products){
			if(productItem.equals(product)){
				quantity += 1;
			}
		}
		return quantity;
	}
	
	public List<Product> getProducts() {
		return Collections.unmodifiableList(products);
	}
}
