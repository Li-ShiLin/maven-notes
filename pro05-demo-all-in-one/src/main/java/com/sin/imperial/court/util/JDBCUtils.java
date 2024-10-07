package com.sin.imperial.court.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * 功能1：从数据源获取数据库连接
 * 功能2：从数据库获取到数据库连接后，绑定到本地线程（借助 ThreadLocal）
 * 功能3：释放线程时和本地线程解除绑定
 */
public class JDBCUtils {

    // 数据源成员变量设置成静态资源，保证对象的单例性；同时保证静态方法中可以访问
    private static DataSource dataSource;

    // 由于 ThreadLocal 对象需要作为绑定数据时 k-v 对中的 key，所以要保证唯一性
    // 加 static 声明为静态资源即可保证唯一性
    private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

    // 在静态代码块中初始化数据源
    static {
        //操作思路分析：
        // 从 jdbc.properties 文件中读取连接数据库的信息
        // 为了保证程序代码的可移植性，需要基于一个确定的基准来读取这个文件
        // 确定的基准: 类路径的根目录。resources 目录下的内容经过构建操作中打包操作后确定放在
        //  WEB-INFO/classes 目录下。 WEB-INFO/classes : 目录存放编译好的 *.class 字节码文件，
        //  所以这个目录我们就称之为类路径。
        // 类路径无论在本地运行还是在服务器端运行都是一个确定的基准。
        //操作具体实现：
        try {
            //1、获取当前类的类加载器
            ClassLoader classLoader = JDBCUtils.class.getClassLoader();

            //2、通过类加载器对象从类路径根目录下读取文件
            InputStream stream = classLoader.getResourceAsStream("jdbc.properties");

            //3、使用 Properties 类封装属性文件中的数据
            Properties properties = new Properties();
            properties.load(stream);

            //4、根据 Properties 对象（已经封装了数据库连接信息）来创建数据源对象
            dataSource = DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
            //为了避免在真正抛出异常后， catch 块捕获到异常从而掩盖问题，
            //这里将所捕获到的异常封装为运行异常继续抛出
            throw new RuntimeException(e);
        }
    }

    /**
     * 工具方法：获取数据库连接并返回
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            // 1、尝试从当前线程检查是否存在已经绑定的 Connection 对象
            connection = threadLocal.get();
            // 2、检查 Connection 对象是否为 null
            if (connection == null) {
                // 3、如果为 null，则从数据源获取数据库连接
                connection = dataSource.getConnection();
                // 4、获取到数据库连接后绑定到当前线程
                threadLocal.set(connection);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // 为了调用工具方法方便，编译时异常不往外抛
            // 为了不掩盖问题，捕获到的编译时异常封装为运行时异常抛出
            throw new RuntimeException(e);
        }
        return connection;
    }

    /**
     * 释放数据库连接
     */
    public static void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                // 在数据库连接池中将当前连接对象标记为空闲
                connection.close();
                // 将当前数据库连接从当前线程上移除
                threadLocal.remove();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }


//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) {
//        try {
//            //1、获取数据库连接
//            //重要:要保证参与事务的多个数据库操作(SQL语句)使用的是同一个数据库连接
//            Connection conn = JDBCUtils.getConnection();
//            //2、核心操作:通过 chain 对象放行当前请求
//            // 这样就可以保证当前请求覆盖的 Servlet 方法、Service 方法、Dao 方法都在同一个事务中
//            //同时各个请求都经过这个 Fi1ter，所以当前事务控制的代码在这里只写一遍就行了避免了代码的几余。
//            chain.doFilter(request,response);
//            //3、核心操作成功结束，可以提交事务
//            conn.commit();
//        } catch (Exception e) {
//            //4、核心操作抛出异常，必须回滚事务
//            conn.rollBack();
//        } finally {
//            //5、释放数据库连接
//            JDBCUtils.releaseConnection(conn);
//        }
//    }


}
