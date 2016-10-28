/*
 * snackbar 1.0 11 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.domain.cashier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.WeakHashMap;

import br.com.hyperclass.snackbar.domain.cashier.events.EventSale;
import br.com.hyperclass.snackbar.domain.cashier.events.SaleCompletedEvent;
import br.com.hyperclass.snackbar.domain.cashier.events.TypeSale;
import br.com.hyperclass.snackbar.domain.order.Order;
import br.com.hyperclass.snackbar.domain.product.Product;
import br.com.hyperclass.snackbar.domain.stock.Stock;
import br.com.hyperclass.snackbar.util.Observer;

/**
 * A <code>Cashier</code> representa um caixa onde recebe uma lista de pedidos,
 * ela tem por finalidade exibir os relatorios das vendas, fazer finalizacao das vendas
 * e notificar o estoque sobre as vendas que ocorreram. 
 * 
 * @author João Batista
 * @version 1.0 11 de out de 2016
 */
public class Cashier {
	
	private Order order;
	private static Date DATE_TODAY;
	private final List<Observer> observerCashier;
	private final Map<Date, List<EventSale>> salesEvent = new WeakHashMap<Date, List<EventSale>>();

	public Cashier(final Order order) {
		super();
		this.order = order;
		this.observerCashier = new ArrayList<>();
	}
	
	/**
	 * O <code>checkout</code> tem por finalidade finalizar uma venda que esta no caixa, no
	 * caso de não existir nenhuma lista com as vendas, e criado uma lista e adicionado o um
	 * evento de venda nesta lista, caso exista esta venda ele recupera a lista existente e
	 * adiciona o novo evento na lista existente.
	 * 
	 * */
	@SuppressWarnings("deprecation")
	public void checkout(final double money, final String saleType) throws Exception{
		
		final TypeSale typeSale = TypeSale.getTypeSale(saleType);
		
		if (money >= order.priceTotalOrder() && order.quatityItemOrder() > 0) {
			
			DATE_TODAY = Calendar.getInstance().getTime();
			DATE_TODAY.setHours(0);
			DATE_TODAY.setMinutes(0);
			DATE_TODAY.setSeconds(0);
			
			if (existSaleToday(DATE_TODAY)) {
				
				final List<EventSale> events = salesEvent.get(DATE_TODAY);
				events.add(new SaleCompletedEvent(typeSale, order));
				
				salesEvent.put(DATE_TODAY, events);
				notifyObserver(order);
				
			} else {
				
				final List<EventSale> events = new ArrayList<>();
				events.add(new SaleCompletedEvent(typeSale, order));
				salesEvent.put(DATE_TODAY, events);
				notifyObserver(order);
			}
			
		}
	}
	
	private void notifyObserver(final Order order) throws Exception{
		for (Observer observer : observerCashier) {
			observer.notify(order.getProductsOrder());
		}
	}
	
/*	public List<EventSale> saleByDate(final long time){
		final Date date = new Date(time);
		return Collections.unmodifiableList(salesEvent.get(date));
	}*/
	
	/**
	 * Este metodo tem por finalidade retornar uma lista com as vendas que acontecerem em 
	 * determinado momento.
	 * 
	 * @param time1 dataInicial 
	 * @param time2 dataFinal
	 * */
	public Map<Date, List<EventSale>> saleByPeriod(final long time1, final long time2){
		
		final Map<Date, List<EventSale>> eventsForPerido = new WeakHashMap<>();
		
		final Date date = returnDay(time1);
		final Date date2 = returnDay(time2);
		
		for (final Entry<Date, List<EventSale>> eventSale : salesEvent.entrySet()) {
			
			if (eventSale.getKey().getTime() >= date.getTime() || eventSale.getKey().getTime() <= date2.getTime()) {
				eventsForPerido.put(eventSale.getKey(), eventSale.getValue());
			}
		}
		
		return Collections.unmodifiableMap(eventsForPerido);
		
	}
	
	public void addObserverCashier(final Stock stock){
		this.observerCashier.add(stock);
	}
	
	public List<Product> productsInCashier(){
		return Collections.unmodifiableList(order.getProductsOrder());
	}
	
	private boolean existSaleToday(final Date date){
		if (salesEvent.containsKey(date)) return true;
		return false;
	}
	
	@SuppressWarnings("deprecation")
	private Date returnDay(final long date){
		
		Date day = new Date(date);
		day.setHours(0);
		day.setMinutes(0);
		day.setSeconds(0);
		
		return day;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((salesEvent == null) ? 0 : salesEvent.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cashier other = (Cashier) obj;
		if (salesEvent == null) {
			if (other.salesEvent != null)
				return false;
		} else if (!salesEvent.equals(other.salesEvent))
			return false;
		return true;
	}
	
	
	
}
