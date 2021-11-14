package github.hxw.mybatis.domain.product;

import java.util.List;

/**
 * @author huangxuewei
 * @date 2021/11/14
 */
public interface ProductRepository {

    // 根据id查询商品信息
    ProductEntity find(long id);

    // 根据名称搜索商品信息
    List<ProductEntity> findByName(String name);

    // 保存商品信息
    long save(ProductEntity product);
}
