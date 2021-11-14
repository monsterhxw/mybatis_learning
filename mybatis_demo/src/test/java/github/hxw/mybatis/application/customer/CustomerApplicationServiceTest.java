package github.hxw.mybatis.application.customer;

import static org.junit.Assert.assertNotNull;

import github.hxw.mybatis.domain.customer.AddressEntity;
import github.hxw.mybatis.domain.customer.CustomerEntity;
import java.util.List;
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

    @Test
    public void addAddress() {
        String name = "黄学维";
        String phone = "12345678910";
        Long customerId = customerApplicationService.register(name, phone);
        assertNotNull(customerId);

        long addressId = customerApplicationService.addAddress(customerId, "牛栏村", "牛栏市", "矮人国");

        assertNotNull(addressId);
    }

    @Test
    public void findAllAddress() {
        String name = "黄学维";
        String phone = "12345678910";
        Long customerId = customerApplicationService.register(name, phone);
        assertNotNull(customerId);

        long addressId = customerApplicationService.addAddress(customerId, "牛栏村", "牛栏市", "矮人国");
        assertNotNull(addressId);

        long addressId2 = customerApplicationService.addAddress(customerId, "牛栏村2", "牛栏市2", "矮人国2");
        assertNotNull(addressId2);

        CustomerEntity withAddress = customerApplicationService.findWithAddress(customerId);
        assertNotNull(withAddress);
        assertNotNull(withAddress.getAddresses());

        List<AddressEntity> allAddress = customerApplicationService.findAllAddress(customerId);
        assertNotNull(allAddress);
    }

    @Test
    public void find() {
        String name = "黄学维";
        String phone = "12345678910";
        Long customerId = customerApplicationService.register(name, phone);
        assertNotNull(customerId);

        CustomerEntity customer = customerApplicationService.find(customerId);
        assertNotNull(customer);
    }

    @Test
    public void findWithAddress() {
        String name = "黄学维";
        String phone = "12345678910";
        Long customerId = customerApplicationService.register(name, phone);
        assertNotNull(customerId);

        long addressId = customerApplicationService.addAddress(customerId, "牛栏村", "牛栏市", "矮人国");
        assertNotNull(addressId);

        long addressId2 = customerApplicationService.addAddress(customerId, "牛栏村2", "牛栏市2", "矮人国2");
        assertNotNull(addressId2);

        CustomerEntity withAddress = customerApplicationService.findWithAddress(customerId);
        assertNotNull(withAddress);
        assertNotNull(withAddress.getAddresses());
    }
}