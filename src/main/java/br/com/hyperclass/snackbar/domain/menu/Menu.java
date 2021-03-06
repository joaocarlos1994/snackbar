/*
 * snackbar 1.0 13 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.menu;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.domain.stock.Stock;

/**
 * A <code>Menu</code> tem todos produtos disponivel e indisponivel,
 * nela e possivel adcionar novos produtos ao menu, assim como excluir.
 * Ela tamb�m possu� um metodo que verifica se existe produtos disponivel
 * no Stock.
 * 
 * @author Jo�o Batista
 * @version 1.0 13 de out de 2016
 */
public class Menu implements Serializable {

	private static final long serialVersionUID = 1L;
	private final List<Product> productsMenu;
	private final Stock stock;

	public Menu(final List<Product> products, final Stock stock) {
		super();
		this.productsMenu = products;
		this.stock = stock;
	}

	public void addProductMenu(final Product product) {

		if (!productsMenu.contains(product)) {
			this.productsMenu.add(product);
		} else {
			new IllegalArgumentException("Object exist in list");
		}
	}

	public void removeProductMenu(final Product product) {

		if (productsMenu.contains(product)) {
			this.productsMenu.remove(product);
		} else {
			new IllegalArgumentException("Object not exist in list");
		}
	}

	public List<Product> getProducts() {
		return Collections.unmodifiableList(productsMenu);
	}
	
	public boolean productsAvaible(final Product product){
		if (stock.quantityStockItem(product) > 0) {
			return true;
		} else {
			throw new IllegalArgumentException("Products not Avaible");
		}
	}
}
