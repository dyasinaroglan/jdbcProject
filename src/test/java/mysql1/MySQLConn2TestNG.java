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
        String sql = "SELECT * FROM personel WHERE age>50 AND country LIKE 'U%'";
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        Assert.assertEquals(rs.getString(2),"Vevay");

        rs.next();//sonraki kaydı açıyoruz
        Assert.assertEquals(rs.getString("country"),"United States");
    }
    @Test(priority = 1)
    public void test2() throws SQLException {
        String sql = "SELECT country, gender, COUNT(*) AS count FROM personel GROUP BY country, gender ORDER BY country, gender";
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();
        //getMetaData --> elde edilen ResulSet nesnesi hakkında sütun sayısı,sütun adları, sütunların veri türleri,tablo adı vs.
        //gibi bilgiler sağlar
        int colCount = rsmd.getColumnCount();
        System.out.println(colCount); // kaç tane kolon var. 3 tane

        for (int i = 1; i <= colCount; i++) {
            System.out.println(rsmd.getColumnLabel(i) + "," + rsmd.getColumnTypeName(i) + "," + rsmd.getColumnDisplaySize(i));
        }

    }
    @Test
    public void test3() throws SQLException {
        String sql = "SELECT country, gender, COUNT(*) AS count FROM personel GROUP BY country, gender ORDER BY country, gender";
        ResultSet rs = stmt.executeQuery(sql);
        ResultSetMetaData rsmd = rs.getMetaData();

        int col1 = rsmd.getColumnDisplaySize(1);
        int col2 = rsmd.getColumnDisplaySize(2);
        int col3 = rsmd.getColumnDisplaySize(3);

        String title1 = rsmd.getColumnLabel(1);
        String title2 = rsmd.getColumnLabel(2);
        String title3 = rsmd.getColumnLabel(3);

        String strFormat = "%-" + col1 + "s %-" + col2 + "s %-" + col3 + "s\n";

        System.out.printf(strFormat,title1,title2,title3);

        while (rs.next()){
            System.out.printf(strFormat, rs.getString(1), rs.getString(2), rs.getString(3));
        }

    }
    @Test
    public void test4() throws SQLException {
        String sql = "SELECT first_name,last_name FROM personel ORDER BY first_name LIMIT 10";
        ResultSet rs = stmt.executeQuery(sql);

        ResultSetMetaData resultSetMetaData = rs.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount(); // 2 tane column var
        int[] columnWith = new int[columnCount];

        for (int i = 1; i <= columnCount; i++) {
            columnWith[i-1] = resultSetMetaData.getColumnDisplaySize(i);
        }
        String[] columnName = new String[columnCount];
        for (int i = 1; i <= columnCount; i++) {
            columnName[i-1] = resultSetMetaData.getColumnLabel(i);
        }
        String strFormat = "";

        for (int i = 0; i < columnCount; i++) {
            strFormat += "%-" + columnWith[i] + "s";
        }
        strFormat += "\n";
        System.out.printf(strFormat, columnName);

        while (rs.next()){
            String[] fieldName = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                fieldName[i] = rs.getString(i+1);
            }
            System.out.printf(strFormat,fieldName);
        }

    }

    @AfterClass
    public void afterClass() throws SQLException {
        //connection kapatılacak
        stmt.close();
        conn.close();

    }
}
