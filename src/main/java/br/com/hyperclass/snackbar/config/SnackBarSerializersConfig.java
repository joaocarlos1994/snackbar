/*
 * snackbar 1.0 20 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.hyperclass.snackbar.restapi.serializer.ProductSerializer;
import br.com.hyperclass.snackbar.restapi.serializer.ProductsSerializer;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 20 de out de 2016
 */
@Configuration
public class SnackBarSerializersConfig {
	
	@Bean
	public ProductSerializer getProductSerializer(){
		return new ProductSerializer();
	}
	
	@Bean
	public ProductsSerializer getProductsSerializer(){
		return new ProductsSerializer(getProductSerializer());
	}
	
}
