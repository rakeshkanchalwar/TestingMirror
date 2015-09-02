package test.java;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;

import org.eclipse.core.runtime.Platform;
import org.junit.Assert;
import org.junit.Test;

import com.bitwise.app.common.Messages;
import com.bitwise.app.common.component.config.CategoryType;
import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.component.config.Policy;
import com.bitwise.app.common.component.policyconfig.MasterPolicies;
import com.bitwise.app.common.component.policyconfig.ObjectFactory;
import com.bitwise.app.common.component.policyconfig.PolicyConfig;
import com.bitwise.app.common.util.XMLConfigUtil;

public class TestGeneralPolices {  
 
	

	@Test
	public void testPolicyConfig()throws Exception{
		String filePath = Platform.getInstallLocation().getURL().getPath() + Messages.XMLConfigUtil_CONFIG_FOLDER+"/policy";
		
		ObjectFactory factory = new ObjectFactory();
		JAXBContext jaxbContext = JAXBContext.newInstance(PolicyConfig.class); 
		Marshaller marshaller = jaxbContext.createMarshaller();
		
		File directory = new File(filePath);
		if (!directory.exists()) { 
			directory.mkdir();
		}
		
		PolicyConfig policyConfig = null;
		File file1 = null;
		file1 = File.createTempFile("file", ".xml", directory);
		policyConfig = factory.createPolicyConfig();
		List<Policy> policies = new ArrayList<>(); 
		Policy policy = new Policy();
		policy.setName("Test");
		policy.setValue("test");
		policies.add(policy);
		MasterPolicies masterPolicies= new MasterPolicies();
		masterPolicies.setPolicy(policies);
		policyConfig.setMasterpolicies(masterPolicies);
		marshaller.marshal(policyConfig, file1); 
		PolicyConfig policyConfig2=XMLConfigUtil.INSTANCE.getPolicyConfig();
		Component component = new Component();
		component.setCategory(CategoryType.IO);
		component.setName("Input");
		List<Policy> policiesFinal= XMLConfigUtil.INSTANCE.getPoliciesForComponent(component,"Input");
		Assert.assertEquals(1, policiesFinal.size());
		file1.deleteOnExit();
		directory.deleteOnExit();
	}
}
