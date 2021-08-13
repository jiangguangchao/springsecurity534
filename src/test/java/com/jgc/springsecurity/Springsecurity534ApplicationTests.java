package com.jgc.springsecurity;

import com.jgc.springsecurity.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.aop.Advisor;
import org.springframework.aop.framework.Advised;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.interceptor.BeanFactoryTransactionAttributeSourceAdvisor;

@SpringBootTest
class Springsecurity534ApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void f() {
		if (userService == null) {
			System.out.println("-----------------------------------service is null");
		} else {
			System.out.println("---------------------------------------service is not null");
			System.out.println(userService.getClass().getName());
			Advised advised = (Advised) userService;
			Advisor[] advisors = advised.getAdvisors();
			for (Advisor advisor : advisors) {
				System.out.println("advisor.className:" + advisor.getClass().getName());
				BeanFactoryTransactionAttributeSourceAdvisor bAdvisor = (BeanFactoryTransactionAttributeSourceAdvisor) advisor;
				System.out.println(bAdvisor.getAdvice());
			}
		}
	}

}
