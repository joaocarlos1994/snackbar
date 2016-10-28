/*
 * snackbar 1.0 13 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.cashier.events;

/**
 * A <code>TypeSale</code> representa o tipo de vendas disponivel na aplicacao.
 * 
 * @author João Batista
 * @version 1.0 13 de out de 2016
 */
public enum TypeSale {
	
	CREDIT, DEBIT, MONEY, CHECK;
	
	public static TypeSale getTypeSale(final String type){
		
		if (type.equals(CREDIT)) return TypeSale.CREDIT;
		if (type.equals(DEBIT)) return TypeSale.DEBIT;
		if (type.equals(MONEY.name())) return TypeSale.MONEY;
		if (type.equals(CHECK)) return TypeSale.CHECK;
		
		throw new IllegalArgumentException("Type not exist");
	}
	
}
