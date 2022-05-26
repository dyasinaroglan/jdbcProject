package mysql1;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class MySQLConn2TestNG {

    Connection conn;
    Statement stmt;

    @BeforeClass
    public void beforeClass() throws SQLException {
        //veritabanı connection
        String url = "jdbc:mysql://localhost:3306/database3";
        String userName = "root";
        String pasword = "";

        conn = DriverManager.getConnection(url,userName,pasword);
        stmt = conn.createStatement();
    }

    @Test
    public void test1() throws SQLException {
        String sql = "SELECT*FROM personel WHERE age>50 AND country LIKE 'U%' ORDER BY first_name";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next(); 
        Assert.assertEquals(rs.getString(2),"Anselm");

        rs.next();//sonraki kaydı açıyoruz
        Assert.assertEquals(rs.getString("country"),"Ukraine");
    }



    @AfterClass
    public void afterClass() throws SQLException {
        //connection kapatılacak
        stmt.close();
        conn.close();
    }
}
