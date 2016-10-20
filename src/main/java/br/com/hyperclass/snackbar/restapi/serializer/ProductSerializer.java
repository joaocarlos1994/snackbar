/*
 * snackbar 1.0 20 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;

import br.com.hyperclass.snackbar.domain.product.Product;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 20 de out de 2016
 */
public class ProductSerializer implements Serializer {
	
	protected void serializerProduct(final Product product, final JsonGenerator jsonGenerator) throws IOException{
		jsonGenerator.writeStringField("name", product.getName());
		jsonGenerator.writeNumberField("price", product.getPrice());
	}

	@Override
	public void serialize(final Product product, final JsonGenerator jsonGenerator) throws IOException {
		jsonGenerator.writeStartObject();
		serialize(product, jsonGenerator);
		jsonGenerator.writeEndObject();
	}

}
