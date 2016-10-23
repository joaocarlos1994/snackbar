/*
 * snackbar 1.0 23 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.util;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 23 de out de 2016
 */
public interface Observer {
	
	void notify(final Object object) throws Exception;
	
}
