package com.mayikt.Utils;

import java.io.Console;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class MayiktjdbcUtils {
    //将构造方法私有化
    //定义工具类变量
    private static String driverClass;
    private static String url;
    private static String user;
    private static String password;

    private MayiktjdbcUtils() {

    }

    //使用静待代码块给变量进行赋值
    static{
        try {
            //读取到config。properties配置文件
            InputStream resourceAsStream = MayiktjdbcUtils.class.getClassLoader().
                getResourceAsStream("config.properties");

            //赋值变量
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            driverClass = properties.getProperty("driverClass");
            url = properties.getProperty("url");
            user = properties.getProperty("user");
            password = properties.getProperty("password");

            //注册驱动类
            Class.forName(driverClass);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
    }

    //封装连接方法
    public static Connection getConnection(){
        try{
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }


    //封装释放方法
    public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection){
        try{
            if(resultSet != null){
                resultSet.close();
            }

            if(statement != null){
                statement.close();
            }

            if(connection != null){
                connection.close();
            }
        }catch(Exception e){
            e.printStackTrace();
            return;
        }
    }

    //重载
    public static void closeConnection(Statement statement, Connection connection) {
        closeConnection(null, statement, connection);
    }
}
