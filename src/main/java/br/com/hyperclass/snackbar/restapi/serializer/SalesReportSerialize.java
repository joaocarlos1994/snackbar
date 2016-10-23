/*
 * snackbar 1.0 20 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi.serializer;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.hyperclass.snackbar.domain.cashier.events.EventSale;
import br.com.hyperclass.snackbar.domain.cashier.events.SaleCompletedEvent;
import br.com.hyperclass.snackbar.restapi.wrapper.ProductsWrapper;
import br.com.hyperclass.snackbar.restapi.wrapper.SalesReportWrapper;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 20 de out de 2016
 */
public class SalesReportSerialize extends JsonSerializer<SalesReportWrapper> {
	
	private final ProductsSerializer productsSerializer;
	
	@Autowired
	public SalesReportSerialize(ProductsSerializer productsSerializer) {
		super();
		this.productsSerializer = productsSerializer;
	}



	@Override
	public void serialize(SalesReportWrapper salesReportWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		
		final Map<Date, List<EventSale>> events = salesReportWrapper.getEventSales();
		
		jsonGenerator.writeStartObject();
		

		for (List<EventSale> event: events.values()) {
			eventSale(event, jsonGenerator, serializerProvider);
		}
		
		jsonGenerator.writeEndObject();
		
	}
	
	private void eventSale(final List<EventSale> eventSales, final JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException{
		
		for (final EventSale eventSale : eventSales) {
			
			final SaleCompletedEvent saleCompletedEvent = (SaleCompletedEvent) eventSale;
			
			jsonGenerator.writeStringField("typeSale", saleCompletedEvent.getSale().name());
			jsonGenerator.writeNumberField("date", saleCompletedEvent.getDate().getTime());
			jsonGenerator.writeNumberField("total", saleCompletedEvent.totalOrder());
			jsonGenerator.writeNumberField("quantity", saleCompletedEvent.totalItemOrder());
			
			jsonGenerator.writeFieldName("products");
			productsSerializer.serialize(new ProductsWrapper(saleCompletedEvent.productsSale()), jsonGenerator, serializerProvider);
			
		}
	}

}
