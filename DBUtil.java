package util;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// 方便其他代码和数据库建立连接
public class DBUtil {
    // DataSource 单例模式，有一个就够了
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/oj?characterEncoding=utf-8&useSSL=true";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "200034Tt";

    private static DataSource dataSource = null;

    // 目的是只创建一个 DataSource 实例
    public static DataSource getDataSource() {
        if(dataSource == null){
            dataSource = new MysqlDataSource();
            ((MysqlDataSource)dataSource).setURL(URL);
            ((MysqlDataSource)dataSource).setUser(USERNAME);
            ((MysqlDataSource)dataSource).setPassword(PASSWORD);
        }
        return dataSource;
    }

    public static Connection getConnection() {
        try {
            return getDataSource().getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 断开连接，回收资源(分开写互不影响)
    public static void close(Connection connection, PreparedStatement statement, ResultSet resultSet){
        if(resultSet != null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(statement != null){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(connection != null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
