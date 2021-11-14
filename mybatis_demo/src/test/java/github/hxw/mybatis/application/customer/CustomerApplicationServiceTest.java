package github.hxw.mybatis.application.customer;

import static org.junit.Assert.assertNotNull;

import org.junit.Before;
import org.junit.Test;

/**
 * @author huangxuewei
 * @date 2021/11/14
 */
public class CustomerApplicationServiceTest {

    private static CustomerApplicationService customerApplicationService;

    @Before
    public void setUp() throws Exception {
        customerApplicationService = new CustomerApplicationService();
    }

    @Test
    public void register() {
        String name = "黄学维";
        String phone = "12345678910";
        Long customerId = customerApplicationService.register(name, phone);
        assertNotNull(customerId);
    }
}