package github.hxw.mybatis.domain.customer;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 送货地址
 *
 * @author huangxuewei
 * @date 2021/11/13
 */
@Getter
@Setter
@ToString
public class AddressEntity {

    private Long id;

    /**
     * 用户
     */
    private CustomerEntity customer;

    /**
     * 街道
     */
    private String street;

    /**
     * 城市
     */
    private String city;

    /**
     * 国家
     */
    private String country;
}
