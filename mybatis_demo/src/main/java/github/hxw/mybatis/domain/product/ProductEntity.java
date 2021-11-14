package github.hxw.mybatis.domain.product;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 商品实体类
 *
 * @author huangxuewei
 * @date 2021/11/14
 */
@Getter
@Setter
@ToString
public class ProductEntity {

    private Long id;

    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品描述
     */
    private String description;

    /**
     * 商品价格
     */
    private BigDecimal price;
}
