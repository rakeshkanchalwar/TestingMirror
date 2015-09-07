package test.java;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.bitwise.app.common.component.config.CategoryType;
import com.bitwise.app.common.component.config.Component;
import com.bitwise.app.common.component.config.Policy;
import com.bitwise.app.common.component.policyconfig.PolicyConfig;
import com.bitwise.app.common.util.XMLConfigUtil;

public class TestGeneralPolices {  
 
	/*@Test
	public void testPolicyConfig(){
		System.out.println("Test");
		PolicyConfig policyConfig =XMLConfigUtil.INSTANCE.getPolicyConfig();
		Assert.assertNotNull(policyConfig);
	}
	
	@Test
	public void testGeneralPolicies(){
		Component component = new Component();
		component.setCategory(CategoryType.INPUT);
		component.setName("Input");
		List<Policy> policies= XMLConfigUtil.INSTANCE.getPoliciesForComponent(component);
		Assert.assertEquals(2, policies.size());
		
	}*/
}
