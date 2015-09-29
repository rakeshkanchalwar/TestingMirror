package com.bitwise.app.engine.util;

import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.slf4j.Logger;

import com.bitwise.app.common.util.LogFactory;
import com.bitwise.app.common.util.XMLConfigUtil;
import com.bitwise.app.engine.converter.Converter;
import com.bitwise.app.engine.converter.ConverterFactory;
import com.bitwise.app.graph.model.Component;
import com.bitwise.app.graph.model.Container;
import com.bitwiseglobal.graph.commontypes.TypeBaseComponent;
import com.bitwiseglobal.graph.main.Graph;
import com.bitwiseglobal.graph.main.ObjectFactory;

public class ConverterUtil {
	private static final Logger logger = new LogFactory(ConverterUtil.class.getName()).getLogger();
	public static final ConverterUtil INSTANCE = new ConverterUtil();
	private ConverterUtil(){}
	
	public void convertToXML(Container container, boolean validate) throws Exception{
		try{
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
			marshall(graph, validate);
		}
		catch(Exception exception){
			logger.info("Failed to create the engine xml", exception);
			throw exception;
		}
	}
	
	
	private void marshall(Graph graph, boolean validate) {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(graph.getClass());
			Marshaller marshaller = jaxbContext.createMarshaller();
			//TODO : add validation
			marshaller.marshal(graph, System.out);
		} catch (Exception exception) {
			logger.info("Failed in marshall", exception);
		}
	}

}
