package com.bitwise.app.perspective.config;

// TODO: Auto-generated Javadoc
/**
 * 
 * @author Bitwise
 * Aug 28, 2015
 *
 */

public enum ELTPerspectives {
	ETLPerspective("com.bitwise.app.perspective.ETLPerspective");
	
	private final String perspectiveID;
	
	private ELTPerspectives(final String perspectiveID){
		this.perspectiveID = perspectiveID;
	}
	
	/**
	 * Contains.
	 * 
	 * @param perspectiveID
	 *            the perspective id
	 * @return true, if successful
	 */
	public static boolean contains(String perspectiveID){
		for(ELTPerspectives eltPerspective : ELTPerspectives.values()){
			if(eltPerspective.toString().equals(perspectiveID)){
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return perspectiveID;
	}
}
