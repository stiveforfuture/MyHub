package com.mayikt.Test;

import com.mayikt.Service.StudentService;
import com.mayikt.entity.StudentEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class IndexTest {
    private static StudentService studentService = new StudentService();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        while(true){
            //定义主菜单的入口
            System.out.println("                 学生管理系统");
            System.out.println("             一.输出所有学生的信息");
            System.out.println("             二.根据id查询学生信息");
            System.out.println("             三.新增学生的信息");
            System.out.println("             四.根据id修改学生信息");
            System.out.println("             五.根据id删除学生信息");
            System.out.println("             请选择对应的序号(输入0退出)");

            //
            Scanner scanner = new Scanner(System.in);
            int result = scanner.nextInt();

            //
            switch(result){
                case 1:
                    showALLStudent();
                    break;
                case 2:
                    findByIDStudent();
                    break;
                case 3:
                    insertStudent();
                    break;
                case 4:
                    updateStudent();
                    break;
                case 5:
                    delIDStudent();
                    break;
                case 0:
                    System.exit(0);
                default:
                    System.out.println("请输入0-5之间的整数");
                    break;
            }
        }
    }



    public static void showALLStudent() throws SQLException, ClassNotFoundException {
        ArrayList<StudentEntity> list = studentService.allStudent();
        for(StudentEntity studentEntity : list){
            System.out.println(studentEntity);
        }
    }

    public static void findByIDStudent() throws SQLException, ClassNotFoundException {
        System.out.println("请输入学生id:");
        Long id = (long) scanner.nextInt();
        StudentEntity student = studentService.getStudentById(id);
        if(student != null){
            System.out.println(student);
        }else
            System.out.println("该学生不存在");
    }

    public static void delIDStudent() throws SQLException, ClassNotFoundException {
        System.out.println("请输入学生id:");
        Long id = (long) scanner.nextInt();
        int result = studentService.deleteStudent(id);
        if(result >= 1){
            System.out.println("删除成功");
        }else
            System.out.println("删除失败");
    }

    public static void insertStudent() throws SQLException, ClassNotFoundException {
        System.out.println("请输入学生的名称:");
        String name = scanner.nextLine();
        System.out.println("请输入学生的年龄:");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.println("请输入学生的地址:");
        String address = scanner.nextLine();
        StudentEntity studentEntity = new StudentEntity(null,name,age,address);
        int result = studentService.insertStudent(studentEntity);
        if(result >= 1){
            System.out.println("插入成功");
        }else
            System.out.println("插入失败");
    }

    public static void updateStudent() throws SQLException, ClassNotFoundException {
        System.out.println("请输入需要修改的学生id");
        Long id = (long) scanner.nextInt();
        StudentEntity studentEntity = studentService.getStudentById(id);
        if(studentEntity != null){
            System.out.println("请输入要修改后学生的名称:");
            scanner.nextLine();
            String name = scanner.nextLine();
            System.out.println("请输入修改后学生的年龄:");
            int age = scanner.nextInt();
            scanner.nextLine();
            System.out.println("请输入修改后学生的地址:");
            String address = scanner.nextLine();
            StudentEntity studentEntity2 = new StudentEntity(id,name,age,address);
            int result = studentService.updateStudent(studentEntity2);
            if(result >= 1){
                System.out.println("修改成功");
            }else
                System.out.println("修改失败");
        }else{
            System.out.println("该学生不存在");
        }
    }

}
