package com.elastic.constants;

public enum ElasticConstants {
	//cluster status
	RED("red"),
	GREEN("green"),
	YELLOW("yellow"),
	
	//special characters
	OPEN_BRACE("{"),
	CLOSE_BRACE("}"),
	DOUBLE_QUOTES("\""),
	COMMA(","),
	COLON(":"),
	DOT("."),
	
	//query filters
	HITS("hits"),
	_ID("_id"),
	_SOURCE("_source"),
	_SCROLL_ID("_scroll_id"),
	SCROLL_ID("scroll_id"),
	SCROLL("scroll"),
	SIZE("size"),
	QUERY("query"),
	MATCH_ALL("match_all");
	
	private String name;

	private ElasticConstants(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}


}
