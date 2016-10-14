/*
 * snackbar 1.0 14 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.cashier.events;

import br.com.hyperclass.snackbar.domain.order.Order;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 14 de out de 2016
 */
public class SaleCompletedEvent extends EventSale {

	private static final long serialVersionUID = 1L;

	public SaleCompletedEvent(final TypeSale typeSale, final Order cart) {
		super(new ContextEventSale(typeSale, cart));
	}

}
