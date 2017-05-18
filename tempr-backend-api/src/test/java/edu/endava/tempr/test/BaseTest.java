package edu.endava.tempr.test;

import edu.endava.tempr.Application;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public abstract class BaseTest {
    protected Logger LOG = LoggerFactory.getLogger(Application.class);
}