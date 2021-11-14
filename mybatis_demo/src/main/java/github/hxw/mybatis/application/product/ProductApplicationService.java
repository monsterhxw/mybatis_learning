package github.hxw.mybatis.application.product;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import github.hxw.mybatis.domain.product.ProductEntity;
import github.hxw.mybatis.domain.product.ProductRepository;
import github.hxw.mybatis.util.DaoUtils;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author huangxuewei
 * @date 2021/11/14
 */
public class ProductApplicationService {

    // 创建商品
    public long createProduct(ProductEntity product) {
        // 检查product中的各个字段是否合法
        Preconditions.checkArgument(product != null, "product is null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(product.getName()), "product name is empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(product.getDescription()), "description name is empty");
        Preconditions.checkArgument(product.getPrice().compareTo(new BigDecimal(0)) > 0,
            "price<=0 error");
        return DaoUtils.execute(sqlSession -> {
            // 通过ProductMapper中的save()方法完成持久化
            ProductRepository productMapper = sqlSession.getMapper(ProductRepository.class);
            return productMapper.save(product);
        });
    }

    public ProductEntity find(long productId) {
        // 检查productId参数是否合法
        Preconditions.checkArgument(productId > 0, "product id error");
        return DaoUtils.execute(sqlSession -> {
            // 通过ProductMapper中的find()方法精确查询Product
            ProductRepository productMapper = sqlSession.getMapper(ProductRepository.class);
            return productMapper.find(productId);
        });
    }

    public List<ProductEntity> find(String productName) {
        // 检查productName参数是否合法
        Preconditions.checkArgument(!Strings.isNullOrEmpty(productName), "product id error");
        return DaoUtils.execute(sqlSession -> {
            // 根据productName模糊查询Product
            ProductRepository productMapper = sqlSession.getMapper(ProductRepository.class);
            return productMapper.findByName(productName);
        });
    }
}
