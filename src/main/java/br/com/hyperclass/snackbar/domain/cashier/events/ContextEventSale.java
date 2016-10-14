/*
 * snackbar 1.0 14 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.cashier.events;

import java.util.Date;

import br.com.hyperclass.snackbar.domain.order.Order;

/**
 * 
 * 
 * @author Jo�o Batista
 * @version 1.0 14 de out de 2016
 */
public class ContextEventSale {

	private final Date date;
	private final TypeSale typeSale;
	private final Order cart;

	public ContextEventSale(final TypeSale typeSale, final  Order cart) {
		super();
		this.date = new Date();
		this.typeSale = typeSale;
		this.cart = cart;
	}

	public Date getDate() {
		return date;
	}

	public TypeSale getTypeSale() {
		return typeSale;
	}

	public Order getCart() {
		return cart;
	}
}