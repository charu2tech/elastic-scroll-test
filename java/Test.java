import org.json.simple.JSONObject;

import com.elastic.http.HttpRequest;
import com.elastic.response.handler.JsonResponseHandler;
import com.elastic.response.handler.StatusResponseHandler;
import com.elastic.util.elastic.ScrollRequestUtil;
import com.google.gson.JsonObject;

public class Test {
	
	public static final int DEFAULT_SCROLL_BATCH_SIZE=70;
	public static final String CLUSTER_HEALTH="_cat/health";
	public static final String LIST_ALL_INDICES="_cat/indices";
	public static final String SEARCH="_search";
	public static final String ENABLE_SCROLL="{indexName}/{typeName}/_search?scroll={timeout}";
	public static final String SCROLL="_search/scroll";

	public static void main(String[] args) {
		String lburl="http://localhost:9200";
		String clusterHealthUrl=lburl+"/"+CLUSTER_HEALTH;
		String listAllIndicesUrl=lburl+"/"+LIST_ALL_INDICES;
		String searchUrl=lburl+"/"+SEARCH;
		String enableScrollUrl=lburl+"/"+"a/b/_search?scroll=1m";
		String scrollUrl=lburl+"/"+SCROLL;
		
		JsonObject scrollEnableQuery = ScrollRequestUtil.getScrollEnableMatchAllQuery(DEFAULT_SCROLL_BATCH_SIZE);
		JsonObject scrollPostRequestBody=ScrollRequestUtil.getPostRequestBody("30m", "12");
		JsonObject scrollDeleteRequestBody=ScrollRequestUtil.getDeleteRequestBody(null);
			
		int status=(int) HttpRequest.executeGet(clusterHealthUrl, new StatusResponseHandler());
		if(status==200){
			JSONObject indicesJsonObject=HttpRequest.executeGet(listAllIndicesUrl, new JsonResponseHandler());
			System.out.println(indicesJsonObject);
			JSONObject scrollJsonObject=HttpRequest.executeGet(enableScrollUrl, new JsonResponseHandler());
		}else{
			System.out.println("cluster is not healthy");
		}
		
	}
}


