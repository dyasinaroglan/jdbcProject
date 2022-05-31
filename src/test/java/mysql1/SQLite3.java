package mysql1;

import java.sql.*;
import java.util.Scanner;

public class SQLite3 {

    public static void main(String[] args) throws SQLException {

        Connection conn;
        PreparedStatement stmt;
        String url = "jdbc:sqlite:src/data.sqlite";

        conn = DriverManager.getConnection(url);
        stmt = conn.prepareStatement("INSERT INTO language(id,language) VALUES(?,?);");

        Scanner sc = new Scanner(System.in);
        System.out.print("Id Giriniz : ");
        String id = sc.nextLine();
        System.out.print("Language Giriniz : ");
        String language = sc.nextLine();

        stmt.setString(1, id);          // ilk parametre olarak, yani ilk ? yerine id yaz
        stmt.setString(2, language);    // ikinci parametre olarak, yani ikinci ? yerine language yaz

        int num = stmt.executeUpdate();
        System.out.println(num + " adet kayit eklendi"); //executeUpdate() methodu ile eklenen kayit sayisini yazdirir
        stmt.close();
        conn.close();

    }
}
