package com.elastic.util.elastic;

import com.elastic.constants.ElasticConstants;
import com.google.gson.JsonObject;

public class BaseUtil {
	
	public static JsonObject getMatchAllQuery(){
		JsonObject matchAllQuery = new JsonObject();
		JsonObject matchAll = new JsonObject();
		matchAll.add(ElasticConstants.MATCH_ALL.getName(), new JsonObject());
		matchAllQuery.add(ElasticConstants.QUERY.getName(), matchAll);
		return matchAllQuery;
	}

}
