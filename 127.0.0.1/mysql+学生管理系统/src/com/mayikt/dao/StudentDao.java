package com.mayikt.dao;

import com.mayikt.Utils.MayiktjdbcUtils;
import com.mayikt.entity.StudentEntity;
import com.mysql.cj.jdbc.Driver;

import java.lang.reflect.Array;
import java.sql.*;
import java.util.ArrayList;

public class StudentDao {

    public ArrayList<StudentEntity> allStudent() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try{
        connection = MayiktjdbcUtils.getConnection();
        statement = connection.createStatement();
        resultSet = statement.executeQuery("select * from mayikt_student");
        ArrayList<StudentEntity> list = new ArrayList<>();
        while(resultSet.next()){
            Long id = resultSet.getLong("id");
            String name = resultSet.getString("name");
            Integer age = resultSet.getInt("age");
            String address = resultSet.getString("address");
            StudentEntity studentEntity = new StudentEntity(id,name,age,address);
            list.add(studentEntity);
        }
        return list;
    }catch(Exception e){
        e.printStackTrace();
        return null;
    }finally{
        try{
            MayiktjdbcUtils.closeConnection(resultSet, statement, connection);
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("资源关闭失败");
            }
        }
    }




    public StudentEntity getByidStudent(Long id) throws ClassNotFoundException, SQLException {
        if(id == null)
            return null;
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            //java 连接到mysql数据库中
            connection = MayiktjdbcUtils.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from mayikt_student where id="+id);
            //若没有差查询到数据
            boolean result = resultSet.next();
            if (!result)
                return null;
            String name = resultSet.getString("name");
            Integer age = resultSet.getInt("age");
            String address = resultSet.getString("address");
            StudentEntity studentEntity = new StudentEntity(id, name, age, address);
            return studentEntity;

        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally{
            try{
                MayiktjdbcUtils.closeConnection(resultSet, statement, connection);
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("资源关闭失败");
            }
        }
    }




    public int Insertstudent (StudentEntity studentEntity) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            //java 连接到mysql数据库中
            connection = MayiktjdbcUtils.getConnection();
            statement = connection.createStatement();
            String insertStudentSql = "INSERT INTO mayikt_student VALUES (null, '"+studentEntity.getName()+"',"+studentEntity.getAge()+",'"+studentEntity.getAddress()+"')";
            int result = statement.executeUpdate(insertStudentSql);
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }finally{
            try{
                MayiktjdbcUtils.closeConnection(statement, connection);
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("资源关闭失败");
            }
        }
    }





    public int Updatestudent (StudentEntity studentEntity) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        try {
            //java 连接到mysql数据库中
            connection = MayiktjdbcUtils.getConnection();
            statement = connection.createStatement();
            String updateStudentSql = "UPDATE `mayikt`.`mayikt_student` SET `name` = '"+studentEntity.getName()+"', `age` = "+studentEntity.getAge()+"," +
                    " `address` = '"+studentEntity.getAddress()+"' WHERE `id` = "+studentEntity.getId()+";";
            int result = statement.executeUpdate(updateStudentSql);
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }finally{
            try{
                MayiktjdbcUtils.closeConnection(statement, connection);
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("资源关闭失败");
            }
        }
    }




    public int deletestudent (Long id) throws ClassNotFoundException, SQLException {
        if(id == null)
            return 0;
        Connection connection = null;
        Statement statement = null;

        try {
            //java 连接到mysql数据库中
            connection = MayiktjdbcUtils.getConnection();
            statement = connection.createStatement();
            String deleteStudentSql = "DELETE FROM mayikt_student WHERE id = "+id;
            System.out.println(deleteStudentSql);
            int result = statement.executeUpdate(deleteStudentSql);
            return result;
        }catch(Exception e){
            e.printStackTrace();
            return 0;
        }finally{
            try{
                MayiktjdbcUtils.closeConnection(statement, connection);
            }catch(Exception e){
                e.printStackTrace();
                System.out.println("资源关闭失败");
            }
        }
    }


}
