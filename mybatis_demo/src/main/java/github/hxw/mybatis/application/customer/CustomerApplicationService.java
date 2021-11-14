package github.hxw.mybatis.application.customer;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import github.hxw.mybatis.domain.customer.AddressEntity;
import github.hxw.mybatis.domain.customer.AddressRepository;
import github.hxw.mybatis.domain.customer.CustomerEntity;
import github.hxw.mybatis.domain.customer.CustomerRepository;
import github.hxw.mybatis.util.DaoUtils;
import java.util.List;

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

    // 用户添加一个新的送货地址
    public long addAddress(long customerId, String street, String city, String country) {
        // 检查传入的name参数以及phone参数是否合法
        Preconditions.checkArgument(customerId > 0, "customerId is empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(street), "street is empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(city), "city is empty");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(country), "country is empty");
        // 我们还可以完成其他业务逻辑，例如检查该地址是否超出了送货范围等，这里不再展示
        return DaoUtils.execute(sqlSession -> {
            // 创建Address对象并调用AddressMapper.save()方法完成持久化
            AddressRepository mapper = sqlSession.getMapper(AddressRepository.class);
            AddressEntity address = new AddressEntity();
            address.setStreet(street);
            address.setCity(city);
            address.setCountry(country);
            int affected = mapper.save(address, customerId);
            if (affected <= 0) {
                throw new RuntimeException("Save Customer fail...");
            }
            return address.getId();
        });
    }

    public List<AddressEntity> findAllAddress(long customerId) {
        // 检查用户id参数是否合法
        Preconditions.checkArgument(customerId > 0, "id error");
        return DaoUtils.execute(sqlSession -> {
            // 执行AddressMapper.find()方法完成查询
            AddressRepository mapper = sqlSession.getMapper(AddressRepository.class);
            return mapper.findAll(customerId);
        });
    }

    public CustomerEntity find(long id) {
        // 检查用户id参数是否合法
        Preconditions.checkArgument(id > 0, "id error");
        return DaoUtils.execute(sqlSession -> {
            // 执行CustomerMapper.find()方法完成查询
            CustomerRepository mapper = sqlSession.getMapper(CustomerRepository.class);
            return mapper.findById(id);
        });
    }

    public CustomerEntity findWithAddress(long id) {
        // 检查用户id参数是否合法
        Preconditions.checkArgument(id > 0, "id error");
        return DaoUtils.execute(sqlSession -> {
            // 执行CustomerMapper.findWithAddress()方法完成查询
            CustomerRepository mapper = sqlSession.getMapper(CustomerRepository.class);
            return mapper.findByIdWithAddress(id);
        });
    }
}
