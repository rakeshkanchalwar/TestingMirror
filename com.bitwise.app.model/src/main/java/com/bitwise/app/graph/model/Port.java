package com.bitwise.app.graph.model;


public class Port extends Model{
	
	private static final long serialVersionUID = 302760655288792415L;
	private String terminal;
	private Component parent;
	
	public Port(String terminal, Component component){
		this.terminal = terminal;
		this.parent =component;
	}

	public Component getParent() {
		return parent;
	}
	
	public String getTerminal() {
		return terminal;
	}
}
