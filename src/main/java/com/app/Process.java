package com.app;

import java.util.Map;

public abstract class Process {
	protected String name;
	protected String logic;
	protected int type;
	private Map<String,Object> input;
	
	public Process(String name) {
		this.name = name;
	}
	
	public void setInput(Map<String,Object> input) {
		this.input = input;
	}

	
	public void setLogic(String logic) {
		this.logic = logic;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	boolean isStart() {
		return this.type == 1;
	}
	boolean isEvent() {
		return this.type == 3;
	}
	boolean isEnd() {
		return this.type == 4;
	}
	
	 public Boolean execute() {
		return MvelParser.parseMvelExpression(logic,input);
	}
}
