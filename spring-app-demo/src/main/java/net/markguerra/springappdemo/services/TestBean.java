package net.markguerra.springappdemo.services;

import org.springframework.stereotype.Component;

@Component
public class TestBean {
	public String testMessage() {
		return "Beans are good for you.";
	}

	public int testNumber() {
		return 7;
	}
}
