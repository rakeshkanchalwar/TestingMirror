package test.java;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import junit.framework.Assert;

import org.eclipse.core.runtime.Platform;
import org.junit.Test;
import org.xml.sax.SAXException;

import com.bitwise.app.common.Messages;
import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.component.config.Config;
import com.bitwise.app.common.component.config.ObjectFactory;
import com.bitwise.app.common.util.XMLConfigUtil;


public class TestJaxb {

	@Test
	public void testReadFile() throws Exception{
//		String filePath = Platform.getInstallLocation().getURL().getPath() + Messages.XMLConfigUtil_CONFIG_FOLDER;
//		
//		ObjectFactory factory = new ObjectFactory();
//		JAXBContext jaxbContext = JAXBContext.newInstance(Config.class);
//		Marshaller marshaller = jaxbContext.createMarshaller();
//		
//		File directory = new File(filePath);
//		if (!directory.exists()) {
//			directory.mkdir();
//		}
//		
//		List<Component> components = null;
//		Config config = null;
//		
//		File file1 = null;
//		File file2 = null;
//		try{
//			file1 = File.createTempFile("file1", ".xml", directory);
//			config = factory.createConfig();
//			marshaller.marshal(config, file1);
//			components = XMLConfigUtil.INSTANCE.getComponentConfig();
//			Assert.assertEquals(config.getComponent().size(), components.size());
//	
//			Component component = factory.createComponent();
//			config.getComponent().add(component);
//			
//			file2 = File.createTempFile("file2", ".xml", directory);
//			marshaller.marshal(config, file2);
//			components = XMLConfigUtil.INSTANCE.getComponentConfig();
//			Assert.assertEquals(config.getComponent().size(), components.size());
//		}
//		finally{
//			file2.deleteOnExit();
//			file1.deleteOnExit();
//			directory.deleteOnExit();
//		}
	}
	
	@Test
	public void testFilteredFiles(){
		FilenameFilter fileNameFilter = XMLConfigUtil.INSTANCE.getFileNameFilter(Messages.XMLConfigUtil_FILE_EXTENTION);
		String filePath = Platform.getInstallLocation().getURL().getPath() + Messages.XMLConfigUtil_CONFIG_FOLDER;
		String[] filteredFiles = XMLConfigUtil.INSTANCE.getFilteredFiles(filePath, fileNameFilter);
		
		
		File file = new File(filePath);
		String[] fileList = file.list();
		int count = 0;
		if(fileList != null && fileList.length != 0){
			for (int i = 0; i < fileList.length; i++) {
				if(fileList[i].endsWith(Messages.XMLConfigUtil_FILE_EXTENTION))
					count ++;
			}
		}
		else{
			Assert.assertEquals(0, filteredFiles.length);
			return;
		}
		
		Assert.assertEquals(count, filteredFiles.length);
	}
	@Test
	public void itShouldValidateXmlWithXsd() throws SAXException, IOException
	{
		String xsdPath="../com.bitwise.app.product/resources/config/xsds/ComponentConfig.xsd";
		String xmlPath="../com.bitwise.app.product/resources/config/input.xml";
		Assert.assertTrue(XMLConfigUtil.INSTANCE.validateXMLSchema(xsdPath,xmlPath));
	}
}
