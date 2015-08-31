package com.bitwise.app.common.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eclipse.core.runtime.Platform;

import com.bitwise.app.common.Messages;
import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.component.config.Config;
import com.bitwise.app.common.component.config.Policy;
import com.bitwise.app.common.component.policyconfig.CategoryPolicies;
import com.bitwise.app.common.component.policyconfig.PolicyConfig;


public class XMLConfigUtil {
	private static Logger logger = Logger.getLogger(XMLConfigUtil.class.getName());
	private static HashMap<String, Component> map = new HashMap<>();
	
	public final static String CONFIG_FILES_PATH = Platform.getInstallLocation().getURL().getPath() + Messages.XMLConfigUtil_CONFIG_FOLDER;
	public final static List<Component> componentList = new ArrayList<>();
	public static PolicyConfig policyConfig ;
	
	public static XMLConfigUtil INSTANCE = new XMLConfigUtil();
	/** Reads the xml configuration files stored under the platform installation.
	 * 	These files contain the configuration required to create the component on UI. 
	 * @return see {@link Component}
	 * @throws RuntimeException 
	 */
	public List<Component> getComponentConfig() throws RuntimeException {
		if(componentList !=null && !componentList.isEmpty()){
			fillMap(componentList);
			return componentList;
		}
		else{
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			String[] configFileList = getFilteredFiles(CONFIG_FILES_PATH, getFileNameFilter(Messages.XMLConfigUtil_FILE_EXTENTION));
			for (int i = 0; i < configFileList.length; i++) {
				Config config = (Config) unmarshaller.unmarshal(new File(CONFIG_FILES_PATH + "/" + configFileList[i]));
				componentList.addAll(config.getComponent());
			}
			fillMap(componentList);
			return componentList;
		}catch(JAXBException jaxbException){
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
	
	private void fillMap(List<Component> componentList) {
		for (Component component : componentList) {
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

	public PolicyConfig getPolicyConfig() throws RuntimeException {
		if(policyConfig !=null){
			return policyConfig;
		}
		else{
		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(PolicyConfig.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			String[] configFileList = getFilteredFiles(CONFIG_FILES_PATH+"/policy", getFileNameFilter(Messages.XMLConfigUtil_FILE_EXTENTION));
			for (int i = 0; i < configFileList.length; i++) {
				 policyConfig = (PolicyConfig) unmarshaller.unmarshal(new File(CONFIG_FILES_PATH + "/policy/" + configFileList[i]));
				
			}

			return policyConfig;
		}catch(JAXBException jaxbException){
			logger.log(Level.SEVERE, "Failed to load the config files"); //$NON-NLS-1$
			throw new RuntimeException("Faild in reading XML Config files", jaxbException); //$NON-NLS-1$
		}
		}
	}
	
	public List<Policy> getPoliciesForComponent(
			com.bitwise.app.common.component.config.Component component,String componentName) {
		List<Policy> policies = new ArrayList<>();
		PolicyConfig policyConfig = XMLConfigUtil.INSTANCE.getPolicyConfig();
		policies.addAll(policyConfig.getMasterpolicies().getPolicy());
		for (CategoryPolicies categoryPolicies : policyConfig
				.getCategorypolicies()) {

			if (categoryPolicies.getCategory().toString()
					.equalsIgnoreCase(component.getCategory().toString())
					&& componentName.equalsIgnoreCase(component.getName())) {
				policies.addAll(categoryPolicies.getPolicy());
				policies.addAll(component.getPolicy());
			}

		}
		return policies;
	}
	
}
