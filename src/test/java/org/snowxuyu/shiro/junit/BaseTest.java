package org.snowxuyu.shiro.junit;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/spring/applicationContext.xml","classpath:/spring/spring-mvc.xml"})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)

public abstract class BaseTest extends AbstractTransactionalJUnit4SpringContextTests {

    protected final static Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private MockHttpServletRequest request;
    private MockHttpServletResponse response;
    
}
