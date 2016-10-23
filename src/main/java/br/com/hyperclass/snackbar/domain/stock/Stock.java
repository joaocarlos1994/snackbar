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
import br.com.hyperclass.snackbar.util.Observer;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 13 de out de 2016
 */
public class Stock implements Observer {
	
	private final List<Product> products;
	
	public Stock() {
		super();
		this.products = new ArrayList<>();
	}
	
	public void addItemStock(final Product product){
		this.products.add(product);
	}

	public void removeItemStock(final Product product) throws StockException {
		
		if (products.contains(product)) {
			products.remove(product);
		}
		throw new ProductUnavailableExcpetion();
	}
	
	public int quantityStockItem(final Product product){
		int quantity = 0;
		for (final Product productItem : products) {
			if (productItem.equals(product)) {
				quantity += 1;
			}
		}
		return quantity;
	}
	
	public List<Product> getProducts() {
		return Collections.unmodifiableList(products);
	}

	@Override
	public void notify(final Object products) throws StockException {
		@SuppressWarnings("unchecked")
		final List<Product> productsOrder = (List<Product>) products;
		for (Product product : productsOrder) {
			removeItemStock(product);
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((products == null) ? 0 : products.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Stock other = (Stock) obj;
		if (products == null) {
			if (other.products != null)
				return false;
		} else if (!products.equals(other.products))
			return false;
		return true;
	}
	
	
}
