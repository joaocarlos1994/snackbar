/*
 * snackbar 1.0 14 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.com.hyperclass.snackbar.domain.menu.Menu;
import br.com.hyperclass.snackbar.domain.order.Order;
import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.domain.stock.Stock;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 14 de out de 2016
 */
@Configuration
public class SnackBarConfig {
	
	@Bean
	public Menu getMenu(){
		
		List<Product> products = new ArrayList<>();
		
		for (Product productItem: getStock().getProducts()) {
			if (!products.contains(productItem)) {
				products.add(productItem);
			}
		}
		
		final Menu menu = new Menu(products, getStock());
		
		return menu;
	}
	
	@Bean
	public Stock getStock(){
		
		final Stock stock = new Stock();
		
		stock.addItemStock(new Product("Coca", 3.0));
		stock.addItemStock(new Product("Pastel que o Augusto me deve", 14.00));
		
		return stock;
	}
	
	@Bean
	@Value(value="session")
	public Order getOrder(){
		return new Order(getMenu());
	}
	
}
