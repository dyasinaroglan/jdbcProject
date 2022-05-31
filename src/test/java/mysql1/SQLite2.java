package mysql1;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.sql.*;
import java.util.Scanner;

public class SQLite2 {

    public static void main(String[] args) throws SQLException {

        Connection conn;
        Statement stmt;
        String url = "jdbc:sqlite:src/data.sqlite";

        conn = DriverManager.getConnection(url);
        stmt = conn.createStatement();

        Scanner sc = new Scanner(System.in);
        System.out.print("Ulke Giriniz : ");
        String ulke = sc.nextLine();

        ResultSet rs = stmt.executeQuery("SELECT * FROM personel WHERE country = '" + ulke + "'");
        while (rs.next()){
            System.out.println(rs.getString("first_name") + "\t" +
                    rs.getString("last_name") + "\t" +
                    rs.getString("country") + "\t" +
                    rs.getString("age"));
        }
        conn.close();
        stmt.close();

    }
}
