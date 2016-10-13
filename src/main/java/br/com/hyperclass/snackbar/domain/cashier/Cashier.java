/*
 * snackbar 1.0 11 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.cashier;

import java.util.ArrayList;
import java.util.List;

import br.com.hyperclass.snackbar.domain.cart.Cart;
import br.com.hyperclass.snackbar.domain.sale.SaleEvent;
import br.com.hyperclass.snackbar.domain.sale.TypeSale;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 11 de out de 2016
 */
public class Cashier {
	
	private final Cart cart;
	private final List<SaleEvent> sales;

	public Cashier(final Cart cart) {
		super();
		this.cart = cart;
		this.sales = new ArrayList<>();
	}
	
	public void checkout(final double money){
		if(money > cart.priceTotalCart()){
			this.sales.add(new SaleEvent(TypeSale.MONEY, cart));
		}
	}
	
}
