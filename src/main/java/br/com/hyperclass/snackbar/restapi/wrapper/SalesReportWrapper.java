/*
 * snackbar 1.0 20 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi.wrapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import br.com.hyperclass.snackbar.domain.cashier.events.EventSale;
import br.com.hyperclass.snackbar.restapi.serializer.SalesReportSerialize;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 20 de out de 2016
 */
@JsonSerialize(using = SalesReportSerialize.class)
public class SalesReportWrapper {
	
	private final Map<Date, List<EventSale>> eventSales;

	public SalesReportWrapper(Map<Date, List<EventSale>> eventSales) {
		super();
		this.eventSales = eventSales;
	}

	public Map<Date, List<EventSale>> getEventSales() {
		return eventSales;
	}
	
	
	
}
