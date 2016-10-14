/*
 * snackbar 1.0 11 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.cashier;

import java.util.ArrayList;
import java.util.List;

import br.com.hyperclass.snackbar.domain.cashier.events.EventSale;
import br.com.hyperclass.snackbar.domain.cashier.events.SaleCompletedEvent;
import br.com.hyperclass.snackbar.domain.cashier.events.TypeSale;
import br.com.hyperclass.snackbar.domain.order.Order;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 11 de out de 2016
 */
public class Cashier {
	
	private final Order cart;
	private final List<EventSale> salesEvent = new ArrayList<>();

	public Cashier(final Order cart) {
		super();
		this.cart = cart;
	}
	
	public void checkout(final double money, final TypeSale typeSale){
		if(money > cart.priceTotalCart()){
			this.salesEvent.add(new SaleCompletedEvent(typeSale, cart));
		}
	}
}
