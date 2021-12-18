package dev.marco.example.springboot;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MysqlCon {
    public static void main(String args[]){
        List<String> students = new ArrayList<String>();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/university","root","");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from student");
            while(rs.next())
                students.add(rs.getString(3));

            con.close();

            students.forEach((n) -> System.out.println(n));
        }catch(Exception e){ System.out.println(e);}
    }
}
