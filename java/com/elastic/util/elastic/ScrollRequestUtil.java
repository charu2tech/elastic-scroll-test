package com.elastic.util.elastic;

import java.util.Arrays;

import com.elastic.constants.ElasticConstants;
import com.elastic.util.StringUtil;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ScrollRequestUtil {
	
	public static JsonObject getScrollEnableBaseQuery(int batchSize){
		JsonObject scrollEnableBaseQuery = new JsonObject();
		scrollEnableBaseQuery.addProperty(ElasticConstants.SIZE.getName(), batchSize);
		return scrollEnableBaseQuery;
	}
	
	public static JsonObject getScrollEnableQuery(int batchSize, JsonObject query){
		JsonObject scrollEnableQuery=getScrollEnableBaseQuery(batchSize);
		scrollEnableQuery.add(ElasticConstants.QUERY.getName(), query);
		return scrollEnableQuery;
	}
	
	public static JsonObject getScrollEnableMatchAllQuery(int batchSize){
		return getScrollEnableQuery(batchSize,BaseUtil.getMatchAllQuery());
	}
	
	public static JsonObject getPostRequestBody(String timeout, String scrollId){
		JsonObject scrollRequestBody = new JsonObject();
		scrollRequestBody.addProperty(ElasticConstants.SCROLL.getName(),timeout);
		scrollRequestBody.addProperty(ElasticConstants.SCROLL_ID.getName(),scrollId);
		return scrollRequestBody;
	}
	
	public static JsonObject getDeleteRequestBody(JsonArray scrollIds){
		JsonObject scrollRequestBody = new JsonObject();
		scrollRequestBody.add(ElasticConstants.SCROLL_ID.getName(),scrollIds);
		return scrollRequestBody;
	}
	
	public static String getDefaultResponseFilters(){
		String idFilter=StringUtil.joinOnDotDelimiter(Arrays.asList(ElasticConstants.HITS.getName(),
				ElasticConstants.HITS.getName(),ElasticConstants._ID.getName()));
		String sourceFilter=StringUtil.joinOnDotDelimiter(Arrays.asList(ElasticConstants.HITS.getName(),
				ElasticConstants.HITS.getName(),ElasticConstants._SOURCE.getName()));
		return StringUtil.joinOnCommaDelimiter(Arrays.asList(ElasticConstants._SCROLL_ID.getName(),idFilter,sourceFilter));
	}

}
