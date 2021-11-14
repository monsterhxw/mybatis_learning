package github.hxw.mybatis.application.product;

import static org.junit.Assert.assertNotNull;

import github.hxw.mybatis.domain.product.ProductEntity;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Before;
import org.junit.Test;

/**
 * @author huangxuewei
 * @date 2021/11/14
 */
public class ProductApplicationServiceTest {

    private static ProductApplicationService productApplicationService;

    @Before
    public void setUp() throws Exception {
        productApplicationService = new ProductApplicationService();
    }

    @Test
    public void createProduct() {
        // 入库一些商品
        ProductEntity product = new ProductEntity();
        product.setName("MyBatis课程");
        product.setDescription("深入MyBatis源码的视频教程");
        product.setPrice(new BigDecimal(99));
        long productId = productApplicationService.createProduct(product);
        assertNotNull(productId);
    }

    @Test
    public void find() {
        ProductEntity product = new ProductEntity();
        product.setName("MyBatis课程");
        product.setDescription("深入MyBatis源码的视频教程");
        product.setPrice(new BigDecimal(99));
        long productId = productApplicationService.createProduct(product);
        assertNotNull(productId);

        ProductEntity productEntity = productApplicationService.find(product.getId());
        assertNotNull(productEntity);
    }

    @Test
    public void findByProductName() {
        ProductEntity product = new ProductEntity();
        product.setName("MyBatis课程");
        product.setDescription("深入MyBatis源码的视频教程");
        product.setPrice(new BigDecimal(99));
        long productId = productApplicationService.createProduct(product);
        assertNotNull(productId);

        String name = product.getName();
        List<ProductEntity> entities = productApplicationService.find(name);
        assertNotNull(entities);
    }
}