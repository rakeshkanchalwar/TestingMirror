package com.bitwise.app.graph.model;


public class Port extends Model{
	
	private static final long serialVersionUID = 302760655288792415L;
	private String terminal;
	private int numberOfPortsOfThisType;
	private String portType;
	private int sequence;
	private Component parent;
	
	public Port(String terminal, Component component, int noPortsOfThisType, String type, int seq){
		this.terminal = terminal;
		this.numberOfPortsOfThisType = noPortsOfThisType;
		this.portType = type;
		this.sequence = seq;
		this.parent =component;
	}

	public Component getParent() {
		return parent;
	}
	
	public String getTerminal() {
		return terminal;
	}

	public int getNumberOfPortsOfThisType() {
		return numberOfPortsOfThisType;
	}

	public String getPortType() {
		return portType;
	}

	public int getSequence() {
		return sequence;
	}
}
