package com.bitwise.app.common.util;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.ui.statushandlers.StatusManager;
import org.xml.sax.SAXException;

import com.bitwise.app.common.Messages;
import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.component.config.Config;
import com.bitwise.app.common.component.config.Policy;
import com.bitwise.app.common.component.policyconfig.CategoryPolicies;
import com.bitwise.app.common.component.policyconfig.PolicyConfig;


/**
 * Utility class to read and validate the xml configuration for following :
 * <ul>
 * 	<li>Component</li>
 * 	<li>Policy</li>
 * </ul>
 */
public class XMLConfigUtil {
	private static Logger logger = Logger.getLogger(XMLConfigUtil.class.getName());
	public static XMLConfigUtil INSTANCE = new XMLConfigUtil();
	private XMLConfigUtil() {}
	
	private static HashMap<String, Component> map = new HashMap<>();
	private static final String SEPARATOR = "/";
	
	public final static String CONFIG_FILES_PATH = Platform.getInstallLocation().getURL().getPath() + Messages.XMLConfigUtil_CONFIG_FOLDER;
	public final static String COMPONENT_CONFIG_XSD_PATH = Platform.getInstallLocation().getURL().getPath()+Messages.XMLConfigUtil_COMPONENTCONFIG_XSD_PATH;
	public final static String POLICY_CONFIG_XSD_PATH = Platform.getInstallLocation().getURL().getPath()+Messages.XMLConfigUtil_POLICYCONFIG_XSD_PATH;
	public final static List<Component> componentList = new ArrayList<>();
	public static PolicyConfig policyConfig ;
	
	/** Reads the xml configuration files stored under the platform installation.
	 * 	These files contain the configuration required to create the component on UI. 
	 * @return see {@link Component}
	 * @throws RuntimeException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public List<Component> getComponentConfig() throws RuntimeException, SAXException, IOException {
		if(componentList != null && !componentList.isEmpty()){
			return componentList;
		}
		else{
			try{
				JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				String[] configFileList = getFilteredFiles(CONFIG_FILES_PATH, getFileNameFilter(Messages.XMLConfigUtil_FILE_EXTENTION));
				for (int i = 0; i < configFileList.length; i++){
					if(validateXMLSchema(COMPONENT_CONFIG_XSD_PATH, CONFIG_FILES_PATH + SEPARATOR + configFileList[i])){
						Config config = (Config) unmarshaller.unmarshal(new File(CONFIG_FILES_PATH + SEPARATOR + configFileList[i]));
						componentList.addAll(config.getComponent());
					}
				}
				validateAndFillComponentConfigList(componentList);
				return componentList;
			}catch(JAXBException jaxbException){
				//TODO : show popup window to user
				logger.log(Level.SEVERE, "Failed to load the config files"); //$NON-NLS-1$
				throw new RuntimeException("Faild in reading XML Config files", jaxbException); //$NON-NLS-1$
			}
		}
	}

	/*public List<Property> getUserProperties(Component component){
		return getPropertiesByType(PropertyType.USER);
	}
	
	public List<Property> getSystemProperties(Component component){
		return getPropertiesByType(PropertyType.CONFIG);
	}
	
	private List<Property> getPropertiesByType(PropertyType propertyType){

		return null;
	}*/
	
	public Component getComponent(String componentName){
			return map.get(componentName);
	}
	
	private void validateAndFillComponentConfigList(List<Component> componentList) {
		for (Component component : componentList) {
			if(map.containsKey(component.getName())){
				Status status = new Status(IStatus.ERROR, "com.bitwise.app.common", 
						"One or more configuration files have similar names, reconfigure the files", null);
				StatusManager.getManager().handle(status, StatusManager.BLOCK);
				//remove all component configuration from list
				componentList.clear();
				throw new RuntimeException("One or more Component names are similar");
			}
			map.put(component.getName(), component);	
		}		
	}


	/** Filters out the files as per the applied filter and 
	 *  returns the file names array
	 * @param filePath directory location of the files
	 * @param filter criteria on which files are to be filtered
	 * @return
	 */
	public String[] getFilteredFiles(String filePath, FilenameFilter filter){
		File file = new File(filePath);
		String[] list = file.list(filter);
		return (list == null) ? new String[0] : list;
	}
	
	
	/** Creates a file name filter in order to filter out only the required files.
	 * @param extention the files to be filtered on ex. .xml
	 * @return FilenameFilter 
	 */
	public FilenameFilter getFileNameFilter(final String extention) {
		FilenameFilter filenameFilter = new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
			  if(name.lastIndexOf('.')>0)
               {
                  // get last index for '.' char
                  int lastIndex = name.lastIndexOf('.');
                  
                  // get extension
                  String str = name.substring(lastIndex);
                  
                  // match path name extension
                  if(str.equals(extention))
                  {
                     return true;
                  }
               }
               return false;
			}
		};
		return filenameFilter;
	}

	public PolicyConfig getPolicyConfig() throws RuntimeException, SAXException, IOException {
		if(policyConfig !=null){
			return policyConfig;
		}
		else{
			try{
				JAXBContext jaxbContext = JAXBContext.newInstance(PolicyConfig.class);
				Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
				String[] configFileList = getFilteredFiles(CONFIG_FILES_PATH + SEPARATOR + Messages.XMLConfigUtil_POLICY, getFileNameFilter(Messages.XMLConfigUtil_FILE_EXTENTION));
				for (int i = 0; i < configFileList.length; i++) {
					if(validateXMLSchema(POLICY_CONFIG_XSD_PATH, CONFIG_FILES_PATH + SEPARATOR + Messages.XMLConfigUtil_POLICY + SEPARATOR + configFileList[i]))	{
						policyConfig = (PolicyConfig) unmarshaller.unmarshal(new File(CONFIG_FILES_PATH + SEPARATOR + Messages.XMLConfigUtil_POLICY + SEPARATOR + configFileList[i]));
					}
				}
				return policyConfig;
			}catch(JAXBException jaxbException){
				logger.log(Level.SEVERE, "Failed to load the config files"); //$NON-NLS-1$
				throw new RuntimeException("Faild in reading XML Config files", jaxbException); //$NON-NLS-1$
			}
		}
	}
	
	/**
	 * Returns the collection of policies.This collection contains :<br>
	 * <ol>
	 * <li><b>Master policies :</b> applicable to all components</li>
	 * <li><b>Category policies : </b> applicable to category</li>
	 * <li><b>component policies : </b> applicable to component only</li>
	 * </ol>
	 * @param component
	 * @param componentName
	 * @return
	 * @throws IOException 
	 * @throws SAXException 
	 * @throws RuntimeException 
	 */
	public List<Policy> getPoliciesForComponent(Component component) throws RuntimeException, SAXException, IOException {
		List<Policy> policies = new ArrayList<>();
		PolicyConfig policyConfig = XMLConfigUtil.INSTANCE.getPolicyConfig();
		//put all master policies
		policies.addAll(policyConfig.getMasterpolicies().getPolicy());
		for (CategoryPolicies categoryPolicies : policyConfig.getCategorypolicies()) {
			if (categoryPolicies.getCategory().toString().equalsIgnoreCase(component.getCategory().toString())) {
				//put all category policies
				policies.addAll(categoryPolicies.getPolicy());
				//put all component policies
			}
		}
		policies.addAll(component.getPolicy());
		return policies;
	}
	
	/**
	 * Validates the xmls based on the provided XSD's constraints
	 * @param xsdPath
	 * @param xmlPath
	 * @return
	 * @throws SAXException
	 * @throws IOException
	 */
	public  boolean validateXMLSchema(String xsdPath, String xmlPath) throws SAXException, IOException{
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = factory.newSchema(new File(xsdPath));
        Validator validator = schema.newValidator();
        validator.validate(new StreamSource(new File(xmlPath)));
        return true;
	}
}
