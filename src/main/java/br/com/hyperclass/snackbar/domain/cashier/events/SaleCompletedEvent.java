/*
 * snackbar 1.0 23 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.cashier.events;

import java.util.Collections;
import java.util.List;

import br.com.hyperclass.snackbar.domain.order.Order;
import br.com.hyperclass.snackbar.domain.product.Product;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 23 de out de 2016
 */
public class SaleCompletedEvent extends EventSale {
	
	private static final long serialVersionUID = 1L;
	
	public SaleCompletedEvent(final TypeSale typeSale, final Order order) {
		super(new SaleContextSale(typeSale, order));
	}
	
	public List<Product> productsSale(){
		return Collections.unmodifiableList(getSource().productsSales());
	}
	
	public double totalOrder(){
		return getSource().totalOrder();
	}
	
	public int totalItemOrder(){
		return getSource().totalItemOrder();
	}
	
	@Override
	public SaleContextSale getSource() {
		return (SaleContextSale) super.getSource();
	}
	
}
