package mysql1;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;
import java.util.Locale;

public class Deneme1 {

    Connection conn = null;
    Statement stmt = null;

    @BeforeClass
    public void beforeClass() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/database3";
        String userName = "root";
        String password = "";

        conn = DriverManager.getConnection(url,userName,password);
        stmt = conn.createStatement();
    }
    @Test(priority = 1)
    public void test1() throws SQLException {

        String sql = "SELECT * FROM personel " +
                "WHERE age>50 AND country LIKE 'u%'" +
                "ORDER BY first_name";
        ResultSet sr = stmt.executeQuery(sql);

        sr.next();
        Assert.assertEquals(sr.getString(2).toLowerCase(Locale.ROOT),"anselm");
                                       //2.kolondaki first_name anselm mi?
        sr.next();
        Assert.assertEquals(sr.getString("country").toLowerCase(Locale.ROOT),"ukraine");
                                      // country kolonu ukraine mı
    }
    @Test(priority = 2)
    public void test2() throws SQLException {
        String sql = "SELECT*FROM personel";

        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData resultSetMetaData = rs.getMetaData();

        int columnCount = resultSetMetaData.getColumnCount();
        System.out.println(columnCount);

        for (int i = 1; i <= columnCount; i++) {
            System.out.println(resultSetMetaData.getColumnLabel(i) + "," + resultSetMetaData.getColumnDisplaySize(i));
                                         //kolonların isimleri            1.kolonun (id) uzunluğu en büyük 10 birim

        }
        System.out.println("**********************");
        System.out.println("Table Name : " + resultSetMetaData.getTableName(1));
        System.out.println("Column Name : " + resultSetMetaData.getColumnName(2));
        System.out.println("Column Type : " + resultSetMetaData.getColumnType(1));
    }

    @AfterClass
    public void afterClass() throws SQLException {
        conn.close();
        stmt.close();
    }
}
