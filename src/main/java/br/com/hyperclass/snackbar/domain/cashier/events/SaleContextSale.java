/*
 * snackbar 1.0 14 de out de 2016
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
 * A <code>SaleContextSale</code> este classe ContextEventSale, ela recebe por padrao
 * um Orde, retornando assim as propriedades interssante por cada evento.
 * 
 * @author Jo�o Batista
 * @version 1.0 14 de out de 2016
 */
public class SaleContextSale extends ContextEventSale {
	
	private final Order order;
	
	public SaleContextSale(final TypeSale typeSale, final Order order) {
		super(typeSale);
		this.order = order;
	}
	
	public List<Product> productsSales(){
		return Collections.unmodifiableList(order.getProductsOrder());
	}
	
	public double totalOrder(){
		return order.priceTotalOrder();
	}
	
	public int totalItemOrder(){
		return order.quatityItemOrder();
	}
	
}
