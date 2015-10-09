package com.bitwise.app.engine.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.eclipse.core.resources.IFile;
import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.engine.converter.Converter;
import com.bitwise.app.engine.converter.ConverterFactory;
import com.bitwise.app.engine.exceptions.EngineException;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Container;
import com.bitwiseglobal.graph.commontypes.TypeBaseComponent;
import com.bitwiseglobal.graph.main.Graph;
import com.bitwiseglobal.graph.main.ObjectFactory;

public class ConverterUtil {
	private static final Logger logger = LogFactory.INSTANCE.getLogger(ConverterUtil.class);
	public static final ConverterUtil INSTANCE = new ConverterUtil();
	
	private ConverterUtil(){}
	
	public void convertToXML(Container container, boolean validate, IFile outPutFile) throws EngineException,Exception{
	String METHOD_NAME="convertToXML - ";
		logger.debug(METHOD_NAME+" creating converter based on component");
		
			Graph graph = new ObjectFactory().createGraph();
			List<Component> children = container.getChildren();
			if(children != null && !children.isEmpty()){
				for (Component component : children) {
					Converter converter = ConverterFactory.INSTANCE.getConverter(component); 
					converter.prepareForXML();
					TypeBaseComponent typeBaseComponent = converter.getComponent();
					graph.getInputOrOutputOrStraightPull().add(typeBaseComponent);
				}
			}
			marshall(graph, validate,outPutFile);
		//throw new IOException();
		
	}
	
	
	private void marshall(Graph graph, boolean validate,IFile outPutFile) {
		String METHOD_NAME="convertToXML - ";
		logger.debug(METHOD_NAME+" marshling genrated object into target XML");
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(graph.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			marshaller.marshal(graph, out);
			if (outPutFile.exists())
				outPutFile.setContents(new ByteArrayInputStream(out.toByteArray()), true,false, null);
			else
				outPutFile.create(new ByteArrayInputStream(out.toByteArray()),true, null);
			out.close();
			
		} catch (Exception exception) {
			logger.error(METHOD_NAME+"Failed in marshall", exception);
		}
	}

}
