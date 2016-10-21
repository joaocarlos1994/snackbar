/*
 * snackbar 1.0 20 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi.serializer;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.restapi.wrapper.ProductsWrapper;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 20 de out de 2016
 */
@Component
public class ProductsSerializer extends JsonSerializer<ProductsWrapper> {
	
	private final Serializer productSerializer;

	@Autowired
	public ProductsSerializer(final Serializer productSerializer) {
		super();
		this.productSerializer = productSerializer;
	}


	@Override
	public void serialize(ProductsWrapper productsWrapper, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
			throws IOException, JsonProcessingException {
		
		jsonGenerator.writeStartArray();
		
		for (final Product product : productsWrapper.getProducts()) {
			productSerializer.serialize(product, jsonGenerator);	
		}
		
		jsonGenerator.writeEndArray();
		
	}


}
