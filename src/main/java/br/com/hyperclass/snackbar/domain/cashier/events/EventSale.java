/*
 * snackbar 1.0 14 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.cashier.events;

import java.util.Date;
import java.util.EventObject;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 14 de out de 2016
 */
public abstract class EventSale extends EventObject implements Comparable<EventSale> {

	private static final long serialVersionUID = 1L;

	public EventSale(final ContextEventSale context) {
		super(context);
	}
	
	public TypeSale getSale(){
		return getSource().getTypeSale();
	}
	
	public Date getDate(){
		return getSource().getDate();
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
