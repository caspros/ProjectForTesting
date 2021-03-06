package dev.marco.example.springboot;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.*;

import java.io.StringWriter;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
// we allow localhost:4200 for testing purposes
@CrossOrigin(origins = "http://localhost:4200")
public class HelloController {

    @RequestMapping(value = "/message", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, List<String>> index() {
        List<String> students = new ArrayList<String>();
        String result = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            Connection con=DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/university","root","");

            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select * from student");
            while(rs.next())
                students.add(rs.getString(3));

            con.close();

            final StringBuilder studentsList = new StringBuilder();
            students.forEach((n) -> {
               // studentsList = studentsList + n + ", ";
                studentsList.append(n + ", ");
                    }

            );
            result = studentsList.toString();
        }catch(Exception e){
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            String sStackTrace = sw.toString();
            students.add(sStackTrace);
        }
        students.add("Message from backend");
        return Collections.singletonMap("message", students);
    }

}
