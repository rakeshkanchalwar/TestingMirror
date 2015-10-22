package com.bitwise.app.graph.model;


// TODO: Auto-generated Javadoc
/**
 * The Class Port.
 * 
 * @author Bitwise
 */
public class Port extends Model{
	
	private static final long serialVersionUID = 302760655288792415L;
	private String terminal;
	private int numberOfPortsOfThisType;
	private String portType;
	private int sequence;
	private Component parent;
	
	/**
	 * Instantiates a new port.
	 * 
	 * @param terminal
	 *            the terminal
	 * @param component
	 *            the component
	 * @param noPortsOfThisType
	 *            the no ports of this type
	 * @param type
	 *            the type
	 * @param seq
	 *            the seq
	 */
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
