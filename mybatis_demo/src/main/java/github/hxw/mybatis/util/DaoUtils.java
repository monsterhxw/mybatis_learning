package github.hxw.mybatis.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.function.Function;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * DAO 工具类
 * <p>
 * 完成 MyBatis 中 SqlSession 以及事务的相关操作
 *
 * @author huangxuewei
 * @date 2021/11/14
 */
public class DaoUtils {

    private static final SqlSessionFactory SQL_SESSION_FACTORY;

    private static final String MYBATIS_CONFIG_RESOURCE = "config/mybatis-config.xml";

    static {
        // 静态代码库中支架读取 MyBatis 的 mybatis-config.xml 配置文件
        InputStream inputStream = null;
        try {
            inputStream = Resources.getResourceAsStream(MYBATIS_CONFIG_RESOURCE);
        } catch (IOException e) {
            System.err.println("read mybatis-config.xml fail");
            e.printStackTrace();
            System.exit(1);
        }
        // 加载 mybatis-config.xml 配置文件，并创建 SqlSessionFactory 对象
        SQL_SESSION_FACTORY = new SqlSessionFactoryBuilder().build(inputStream);
    }

    public static <R> R execute(Function<SqlSession, R> function) {
        // 创建 SqlSession
        SqlSession session = SQL_SESSION_FACTORY.openSession();
        try {
            R apply = function.apply(session);
            // 提交事务
            session.commit();
            return apply;
        } catch (Throwable t) {
            // 出现异常的时候，回滚事务
            session.rollback();
            System.out.println("execute error");
            throw t;
        } finally {
            // 关闭 SqlSession
            session.close();
        }
    }
}
