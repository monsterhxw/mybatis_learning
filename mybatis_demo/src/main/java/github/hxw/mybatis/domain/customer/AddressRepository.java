package github.hxw.mybatis.domain.customer;

import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * @author huangxuewei
 * @date 2021/11/14
 */
public interface AddressRepository {

    // 根据id查询Address对象
    AddressEntity find(long id);

    // 查询一个用户的全部地址信息
    List<AddressEntity> findAll(long customerId);

    // 查询指定订单的送货地址
    AddressEntity findByOrderId(long orderId);

    // 存储Address对象，同时会记录关联的Customer
    int save(@Param("address") AddressEntity address, @Param("customerId") long customerId);
}
