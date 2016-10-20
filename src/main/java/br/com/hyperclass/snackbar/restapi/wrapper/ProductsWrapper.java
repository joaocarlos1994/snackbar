/*
 * snackbar 1.0 20 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi.wrapper;

import java.util.Collections;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.restapi.serializer.ProductsSerializer;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 20 de out de 2016
 */
@JsonSerialize(using = ProductsSerializer.class)
public class ProductsWrapper {
	
	private final List<Product> products;

	public ProductsWrapper(List<Product> products) {
		super();
		this.products = products;
	}

	public List<Product> getProducts() {
		return Collections.unmodifiableList(products);
	}
	
	
	
}
