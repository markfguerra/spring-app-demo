package net.markguerra.springappdemo.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class TestExampleBean {
	@Autowired
	private ExampleBean exampleBean;

	@Test
	public void testTestNumber() {
		int expected = 7;
		int actual = exampleBean.testNumber();
		Assert.assertEquals(expected, actual);
	}
}
