package github.hxw.mybatis.domain.customer;

/**
 * 用户 DAO
 *
 * @author huangxuewei
 * @date 2021/11/14
 */
public interface CustomerRepository {

    /**
     * 根据用户 ID 查询 Customer
     *
     * @param id 用户 ID
     * @return 用户实体
     */
    CustomerEntity findById(Long id);

    /**
     * 根据用户 ID 查询 Customer，同时查询 Address
     *
     * @param id 用户 ID
     * @return 用户实体
     */
    CustomerEntity findByIdWithAddress(Long id);

    /**
     * 根据 Order ID 查询 Customer
     *
     * @param orderId 订单 ID
     * @return 用户实体
     */
    CustomerEntity findByOrderId(Long orderId);

    /**
     * 持久化 CustomerEntity 对象
     *
     * @param customer 用户对象
     * @return 自增 ID
     */
    int save(CustomerEntity customer);
}