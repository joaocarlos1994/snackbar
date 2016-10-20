/*
 * snackbar 1.0 19 de out de 2016
 *
 * Copyright (c) 2016, hyperCLASS. All rights reserved. hyperCLASS
 * proprietary/confidential. Use is subject to license terms.
 */
package br.com.hyperclass.snackbar.restapi.deserializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

import br.com.hyperclass.snackbar.restapi.wrapper.SalesDateWrapper;

/**
 * 
 * 
 * @author João Batista
 * @version 1.0 19 de out de 2016
 */
public class SalesDateDeserialize extends JsonDeserializer<SalesDateWrapper> {

	@Override
	public SalesDateWrapper deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		
		ObjectCodec oc = jsonParser.getCodec();
		JsonNode node = oc.readTree(jsonParser);
		
		return new SalesDateWrapper(node.get("dateInitial").asLong(), node.get("dateFinal").asLong());
	}

}
