package mysql1;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class SQLitePreviousAbsolute {

    Connection conn;
    Statement stmt;

    @BeforeClass
    public void beforeClass(){
        String url = "jdbc:mysql://localhost:3306/database3";

        try{
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                                                       //ınsensitive duyarsız

        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void test1() {

        ResultSet rs = null;
        try {
            String sql = "SELECT * FROM personel " +
                    "WHERE age>50 AND country LIKE 'u%'" +
                    "ORDER BY first_name";

            rs = stmt.executeQuery(sql);

            rs.next();
                System.out.println(
                        rs.getString(2) + "\t" +
                                rs.getString("last_name") + "\t" +
                                rs.getString("country") + "\t" +
                                rs.getString("age")
                );

            rs.next();
            System.out.println(
                    rs.getString(2) + "\t" +
                            rs.getString("last_name") + "\t" +
                            rs.getString("country") + "\t" +
                            rs.getString("age")
            );

            rs.previous();  //öncekine git yaz diyorum
            System.out.println(
                    rs.getString(2) + "\t" +
                            rs.getString("last_name") + "\t" +
                            rs.getString("country") + "\t" +
                            rs.getString("age")
            );

            rs.absolute(10);  //hangi kayda gitmek istiyorsam 10. kayda git
            System.out.println(
                    rs.getString(2) + "\t" +
                            rs.getString("last_name") + "\t" +
                            rs.getString("country") + "\t" +
                            rs.getString("age")
            );

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
    @AfterClass
    public void afterClass() {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
