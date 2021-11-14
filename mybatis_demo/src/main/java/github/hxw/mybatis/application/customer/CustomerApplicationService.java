package github.hxw.mybatis.application.customer;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import github.hxw.mybatis.domain.customer.CustomerEntity;
import github.hxw.mybatis.domain.customer.CustomerRepository;
import github.hxw.mybatis.util.DaoUtils;

/**
 * 用户资源的应用服务接口
 *
 * @author huangxuewei
 * @date 2021/11/14
 */
public class CustomerApplicationService {

    public Long register(String name, String phone) {
        // 检查传入的 name 参数以及 phone 参数是否合法
        Preconditions.checkArgument(!Strings.isNullOrEmpty(name), "name is empty!");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(phone), "phone is empty!");
        // 其他业务逻辑，例如检查用户名是否重复、手机号是否重复等
        return DaoUtils.execute(sqlSession -> {
            // 创建 CustomerEntity 对象，并通过 CustomerRepository::save 方法完成持久化
            CustomerRepository mapper = sqlSession.getMapper(CustomerRepository.class);
            CustomerEntity customer = CustomerEntity.builder().name(name).phone(phone).build();
            int affected = mapper.save(customer);
            if (affected <= 0) {
                throw new RuntimeException("Save Customer fail...");
            }
            return customer.getId();
        });
    }
}
