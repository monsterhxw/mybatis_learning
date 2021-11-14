package github.hxw.mybatis.application.order;

import static org.junit.Assert.assertNotNull;

import com.google.common.collect.Lists;
import github.hxw.mybatis.application.customer.CustomerApplicationService;
import github.hxw.mybatis.application.product.ProductApplicationService;
import github.hxw.mybatis.domain.customer.CustomerEntity;
import github.hxw.mybatis.domain.order.OrderEntity;
import github.hxw.mybatis.domain.order.OrderItemEntity;
import github.hxw.mybatis.domain.product.ProductEntity;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;

/**
 * @author huangxuewei
 * @date 2021/11/14
 */
public class OrderApplicationServiceTest {

    private static OrderApplicationService orderApplicationService;
    private static CustomerApplicationService customerApplicationService;
    private static ProductApplicationService productApplicationService;

    @Before
    public void setUp() throws Exception {
        orderApplicationService = new OrderApplicationService();
        customerApplicationService = new CustomerApplicationService();
        productApplicationService = new ProductApplicationService();
    }

    @Test
    public void createOrder() {
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

        // 创建一个订单
        OrderEntity order = new OrderEntity();
        order.setCustomer(withAddress); // 买家
        order.setDeliveryAddress(withAddress.getAddresses().get(0)); // 配送地址
        order.setCreateTime(System.currentTimeMillis());

        ProductEntity product = new ProductEntity();
        product.setName("MyBatis课程");
        product.setDescription("深入MyBatis源码的视频教程");
        product.setPrice(new BigDecimal(99));
        long productId = productApplicationService.createProduct(product);
        assertNotNull(productId);

        ProductEntity productEntity = productApplicationService.find(product.getId());
        assertNotNull(productEntity);

        // 生成购买条目
        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setAmount(20);
        orderItem.setProduct(product);
        orderItem.setPrice(new BigDecimal(20 * 99));
        order.setOrderItems(Lists.newArrayList(orderItem));

        order.setTotalPrice(new BigDecimal(99 * 20));

        long orderId = orderApplicationService.createOrder(order);
        assertNotNull(orderId);
    }

    @Test
    public void find() {
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

        // 创建一个订单
        OrderEntity order = new OrderEntity();
        order.setCustomer(withAddress); // 买家
        order.setDeliveryAddress(withAddress.getAddresses().get(0)); // 配送地址
        order.setCreateTime(System.currentTimeMillis());

        ProductEntity product = new ProductEntity();
        product.setName("MyBatis课程");
        product.setDescription("深入MyBatis源码的视频教程");
        product.setPrice(new BigDecimal(99));
        long productId = productApplicationService.createProduct(product);
        assertNotNull(productId);

        ProductEntity productEntity = productApplicationService.find(product.getId());
        assertNotNull(productEntity);

        // 生成购买条目
        OrderItemEntity orderItem = new OrderItemEntity();
        orderItem.setAmount(20);
        orderItem.setProduct(product);
        orderItem.setPrice(new BigDecimal(20 * 99));
        order.setOrderItems(Lists.newArrayList(orderItem));

        order.setTotalPrice(new BigDecimal(99 * 20));

        long orderId = orderApplicationService.createOrder(order);
        assertNotNull(orderId);

        OrderEntity orderEntity = orderApplicationService.find(order.getId());
        assertNotNull(orderEntity);
    }
}