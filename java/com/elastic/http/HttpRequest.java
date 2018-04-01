package com.elastic.http;

import java.io.IOException;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.entity.StringEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.elastic.response.handler.ResponseHandler;

public class HttpRequest {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpRequest.class);

	public static CloseableHttpResponse executeGet(final String url){
		LOGGER.info("Executing get request. url={}",url);
		HttpGet getRequest = new HttpGet(url);
		CloseableHttpResponse response =null;
		try {
			response = HttpClient.getHttpClient().execute(getRequest);
		} catch (IOException e) {
			LOGGER.error("Unable to execute http get request. url={}", url);
		}
		return response;		
	}
	
	public static <T> T executeGet(final String url, final ResponseHandler<T> handler){
		CloseableHttpResponse response=null;
		T data =null;
		try{
			response= executeGet(url);
		    data =handler.handleResponse(response);
		}finally{
		closeHttpResponse(response);	
		}
		return data;		
	}
	
	public static CloseableHttpResponse executePost(final String url, final String jsonData){
		LOGGER.info("Executing post request. url={}",url);
		HttpPost httpPost = new HttpPost(url);
		CloseableHttpResponse response =null;
		try {
		    StringEntity entity = new StringEntity(jsonData);
		    httpPost.setEntity(entity);
		    httpPost.setHeader("Accept", "application/json");
		    httpPost.setHeader("Content-type", "application/json");
			response = HttpClient.getHttpClient().execute(httpPost);
		} catch (IOException e) {
			LOGGER.error("Unable to execute http post request. url={}", url);
			LOGGER.error("post data. jsonData={}", jsonData);
		}
		return response;		
	}
	
	public static <T> T executePost(final String url, final String jsonData,final ResponseHandler<T> handler){
		CloseableHttpResponse response=null;
		T data =null;
		try{
			response= executePost(url,jsonData);
		    data =handler.handleResponse(response);
		}finally{
		closeHttpResponse(response);	
		}
		return data;
	}
	
	public static void closeHttpResponse(final CloseableHttpResponse closeableHttpResponse){
		HttpClientUtils.closeQuietly(closeableHttpResponse);
	}
}
