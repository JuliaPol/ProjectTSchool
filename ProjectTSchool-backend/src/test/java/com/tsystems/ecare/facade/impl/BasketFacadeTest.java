package java.com.tsystems.ecare.facade.impl;

import com.tsystems.ecare.facade.OptionFacade;
import com.tsystems.ecare.facade.RateFacade;
import com.tsystems.ecare.facade.UserFacade;
import com.tsystems.ecare.facade.impl.OptionFacadeImpl;
import com.tsystems.ecare.facade.impl.RateFacadeImpl;
import com.tsystems.ecare.facade.impl.UserFacadeImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class BasketFacadeTest {
    @Autowired
    private UserFacade userFacade;

    @Autowired
    private RateFacade rateFacade;

    @Autowired
    private OptionFacade optionFacade;

    @Test
    public void getBasketTest() throws Exception {
        String name = "";
    }

    @Configuration
    static class ContextConfiguration {

        @Bean
        public UserFacade userFacade() {
            return new UserFacadeImpl();
        }

        @Bean
        public RateFacade rateFacade() {
            return new RateFacadeImpl();
        }

        @Bean
        public OptionFacade optionFacade() {
            return new OptionFacadeImpl();
        }

    }
}
