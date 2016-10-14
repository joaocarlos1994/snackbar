/*
 * snackbar 1.0 14 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.cashier.events;

import java.util.Date;
import java.util.EventObject;

import br.com.hyperclass.snackbar.domain.cart.Cart;

/**
 * 
 * 
 * @author Jo�o Batista
 * @version 1.0 14 de out de 2016
 */
public abstract class EventSale extends EventObject implements Comparable<EventSale> {


	public EventSale(final ContextEventSale context) {
		super(context);
	}
	
	public TypeSale getSale(){
		return getSource().getTypeSale();
	}
	
	public Date getDate(){
		return getSource().getDate();
	}
	
	public Cart getCart(){
		return getSource().getCart();
	}

	@Override
	public int compareTo(EventSale eventSale) {
		return getDate().compareTo(eventSale.getDate());
	}
	
	@Override
	public ContextEventSale getSource() {
		return (ContextEventSale) super.getSource();
	}

}
