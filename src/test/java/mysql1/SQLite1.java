package mysql1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class SQLite1 {

    Connection conn;
    Statement stmt;

    @BeforeClass
    public void beforeClass(){
        String url = "jdbc:sqlite:src/data.sqlite";

        try{
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();

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

            while (rs.next()) {
                System.out.println(
                        rs.getString(2) + "\t" +
                                rs.getString("last_name") + "\t" +
                                rs.getString("country") + "\t" +
                                rs.getString("age")
                );
            }
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
