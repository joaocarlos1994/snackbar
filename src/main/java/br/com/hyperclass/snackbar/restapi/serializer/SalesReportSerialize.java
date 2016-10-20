/*
 * snackbar 1.0 20 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.hyperclass.snackbar.restapi.wrapper.SalesReportWrapper;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 20 de out de 2016
 */
public class SalesReportSerialize extends JsonSerializer<SalesReportWrapper> {
	
	
	@Override
	public void serialize(SalesReportWrapper salesReportWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		
	}

}
