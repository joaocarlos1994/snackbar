/*
 * snackbar 1.0 13 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.sale;

import java.util.Date;
import java.util.List;

import br.com.hyperclass.snackbar.domain.cart.Cart;
import br.com.hyperclass.snackbar.domain.product.Product;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 13 de out de 2016
 */
public class SaleEvent implements Comparable<SaleEvent> {
	
	private final Date date;
	private final TypeSale typeSale;
	private final Cart cart;
	
	public SaleEvent(final TypeSale typeSale, final  Cart cart) {
		super();
		this.date = new Date();
		this.typeSale = typeSale;
		this.cart = cart;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public int compareTo(final SaleEvent sale) {
		return getDate().compareTo(sale.getDate());
	}
	
	private void removeItemStock(final List<Product> products){
		
	}
	
}
