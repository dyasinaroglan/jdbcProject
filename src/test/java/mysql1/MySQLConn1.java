package mysql1;

import java.sql.*;

public class MySQLConn1 {

    /*
     JDBC --> JAva databese connections
     veritabanı yeri
     kullanıcı
     şifre


     ConnectionString
     JDBC url format

     Connection (BAĞLANTI)
     statement (hat oluşturmamız lazım)
     RecordSet
     */
    public static void main(String[] args) {

        //veritabanına bağlanmak için connection oluşturuyorum

        try {
            Connection conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/database3", //JDBC url format a bakmak için
                    "root",
                    ""
            );
            //Connection'ı oluşturduk
            Statement stmt = conn.createStatement();

            //veritabanından verileri çekerken. İstenen SQL sonucunu resultSet'e alıyoruz. bir kümeye almış gibi
            ResultSet rs = stmt.executeQuery("SELECT first_name,last_name, age FROM personel LIMIT 10");

            while (rs.next()) {
                System.out.println(rs.getString(1) + ","
                        + rs.getString("last_name") + ","
                        + rs.getInt("age")); //db'deki age integer olmalı
            }
            stmt.close();
            conn.close();  //veritabanında bsğlsntıyı kapatmayı unutmayalım

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

















