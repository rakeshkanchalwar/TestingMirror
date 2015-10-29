package com.bitwise.app.graph.model;


// TODO: Auto-generated Javadoc
/**
 * The Class Port.
 * 
 * @author Bitwise
 */
public class Port extends Model{
	
	private static final long serialVersionUID = 302760655288792415L;
	private final String terminal;
	private final int numberOfPortsOfThisType;
	private final String portType;
	private final int sequence;
	private final Component parent;
	private final String nameOfPort;
	/**
	 * Instantiates a new port.
	 * * @param nameOfPort
	 *            the nameOfPort
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
	public Port(String nameOfPort,String terminal, Component component, int noPortsOfThisType, String type, int seq){
		this.terminal = terminal;
		this.numberOfPortsOfThisType = noPortsOfThisType;
		this.portType = type;
		this.sequence = seq;
		this.parent =component;
		this.nameOfPort=nameOfPort;
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
	
	public String getNameOfPort() {
		return nameOfPort;
	}
	
}
