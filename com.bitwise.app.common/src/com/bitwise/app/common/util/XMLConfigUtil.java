package com.bitwise.app.common.util;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.eclipse.core.runtime.Platform;

import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.component.config.Config;

public class XMLConfigUtil {
	private static Logger logger = Logger.getLogger(XMLConfigUtil.class.getName());
	
	public final static String CONFIG_FILES_PATH = Platform.getInstallLocation().getURL().getPath() + Messages.XMLConfigUtil_CONFIG_FOLDER;
	
	public static XMLConfigUtil INSTANCE = new XMLConfigUtil();
	
	/** Reads the xml configuration files stored under the platform installation.
	 * 	These files contain the configuration required to create the component on UI. 
	 * @return see {@link Component}
	 * @throws RuntimeException 
	 */
	public List<Component> getComponentConfig() throws RuntimeException {
		try{
			List<Component> componentList = new ArrayList<>();
			JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			String[] configFileList = getFilteredFiles(CONFIG_FILES_PATH, getFileNameFilter(Messages.XMLConfigUtil_FILE_EXTENTION));
			for (int i = 0; i < configFileList.length; i++) {
				Config config = (Config) unmarshaller.unmarshal(new File(CONFIG_FILES_PATH + "/" + configFileList[i]));
				componentList.addAll(config.getComponent());
			}
			return componentList;
		}catch(JAXBException jaxbException){
			logger.log(Level.SEVERE, "Failed to load the config files"); //$NON-NLS-1$
			throw new RuntimeException("Faild in reading XML Config files", jaxbException); //$NON-NLS-1$
		}
	}

	
	/** Filters out the files as per the applied filter and 
	 *  returns the file names array
	 * @param filePath directory location of the files
	 * @param filter criteria on which files are to be filtered
	 * @return
	 */
	public String[] getFilteredFiles(String filePath, FilenameFilter filter){
		File file = new File(CONFIG_FILES_PATH);
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
}
