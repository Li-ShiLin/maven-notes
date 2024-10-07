package com.sin.imperial.court.dao;


import com.sin.imperial.court.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * BaseDao 类:  所有Dao实现类的基类
 *
 * @param <T> 泛型：代表实体类的类型
 */
public class BaseDao<T> {


    // DBUtils 工具包提供的数据库作对象
    private final QueryRunner runner = new QueryRunner();

    /**
     * 查询单个对象
     *
     * @param sql         执行查询的sql语句
     * @param entityClass 实体类对应的class对象
     * @param parameters  传给sql语句的参数
     * @return 查询到的实体类对象
     */
    public T getSingleBean(String sql, Class<T> entityClass, Object... parameters) {
        try {
            // 获取数据库链接
            Connection connection = JDBCUtils.getConnection();
            T entity = runner.query(connection, sql, new BeanHandler<>(entityClass), parameters);
            return entity;
        } catch (SQLException e) {
            e.printStackTrace();
            // 如果真的抛出异常，则将编译时异常封装成运行时异常抛出
            throw new RuntimeException(e);
        }
    }


    /**
     * 查询返回多个对象的方法
     *
     * @param sql         执行查询操作的SQL语句
     * @param entityClass 实体类的class对象
     * @param parameters  SQL语句的参数
     * @return 查询结果
     */
    public List<T> getBeanList(String sql, Class<T> entityClass, Object... parameters) {
        // 获取数据库链接
        try {
            Connection connection = JDBCUtils.getConnection();
            return runner.query(connection, sql, new BeanListHandler<T>(entityClass), parameters);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 通用的增删改查方法，insert、delete、update操作都可以用这个方法
     *
     * @param sql        执行的sql语句
     * @param parameters 语句的参数
     * @return 受影响的行数
     */
    public int update(String sql, Object... parameters) {
        try {
            Connection connection = JDBCUtils.getConnection();
            int affectedNumbers = 0;
            affectedNumbers = runner.update(connection, sql, parameters);
            return affectedNumbers;
        } catch (SQLException e) {
            e.printStackTrace();
            // 如果真的抛出异常，则将编译时异常封装成运行时异常抛出
            throw new RuntimeException(e);
        }
    }



}
