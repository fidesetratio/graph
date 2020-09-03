package com.app;

import java.util.Map;

import org.jgrapht.graph.DefaultEdge;

public abstract class Line extends DefaultEdge {
	private int priority;
	private String logic;
	private Map<String,Object> input;
	
	public void setInput(Map<String,Object> input) {
		this.input = input;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}
	public Integer getPriority() {
		return this.priority;
	}
	
	public Boolean isAllowedContinue() {
		return MvelParser.parseMvelExpression(logic,input);
	}
}
