/*
 * snackbar 1.0 14 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.cashier.events;

import java.util.Date;

/**
 * A <code>ContextEventSale</code> e uma classe de evento de contexto onde tem o 
 * dia por evento e tipo de venda.
 * 
 * @author João Batista
 * @version 1.0 14 de out de 2016
 */
public class ContextEventSale {

	private final Date date;
	private final TypeSale typeSale;

	public ContextEventSale(final TypeSale typeSale) {
		super();
		this.date = new Date();
		this.typeSale = typeSale;
	}

	public Date getDate() {
		return date;
	}

	public TypeSale getTypeSale() {
		return typeSale;
	}
}
