/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tablolar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class odeme{ 

    static Connection con;
public static void odemecrud() {
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gsm", "postgres", "123");
        } catch (Exception e) {
            System.out.println(e);
        }

        Scanner scan = new Scanner(System.in);
        int secim;

        while (true) {
            System.out.println("*************");
            System.out.println("1.Listele");
            System.out.println("2.Ekle");
            System.out.println("3.Güncelle");
            System.out.println("4.Sil");
            System.out.println("5.Çıkış");
            System.out.print("Seçiminiz:");
            secim = scan.nextInt();

            System.out.println("*************");

            if (secim == 1) {
                Listele();
            }
            if (secim == 2) {
                Ekle();
            }
            if (secim == 3) {
                Guncelle();
            }
            if (secim == 4) {
                Sil();
            }
            if (secim == 5) {
                exit();
            }
            {
                try {
                    con.close();
                } catch (Exception e) {
                    System.out.println(e);
                }

                break;
            }
        }
    }

    public static void Listele() {
        try {
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from odeme");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getInt(3));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void Ekle() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Yeni Odeme Id Giriniz     :");
        int odeme_id = scan.nextInt();
        System.out.print("Odeme Alan Kisiyi Giriniz :");
        String odeme_alan = scan.next();
        System.out.print("Alınan Yeni Ucreti Giriniz :");
        int ucret = scan.nextInt();
        try {
            Statement stmt = con.createStatement();
            String sorgu = String.format("insert into sube values( %d,'%s',%d)", odeme_id, odeme_alan, ucret);
            int ekleme = stmt.executeUpdate(sorgu);
            System.out.println("Kayıt Eklendi");
        } catch (Exception e) {
            System.out.println(e);
        }
        Listele();

    }

    public static void Guncelle() {
        Scanner scan = new Scanner(System.in);
        try {
            Listele();
            System.out.println("Eski Odeme Id giriniz");
            int eski_odemeid = scan.nextInt();
            System.out.print("Yeni Odeme Id Giriniz:");
            int odeme_id = scan.nextInt();
            System.out.print("Odeme Alanı Giriniz:");
            String odeme_alan = scan.next();
            System.out.print("Yeni Ucreti Giriniz :");
            int ucret = scan.nextInt();

            Statement stmt = con.createStatement();
            String sorgu = String.format("update odeme set odeme_id=%d,odeme_alan='%s',ucret=%d where odeme_id=%d ", odeme_id, odeme_alan, ucret, eski_odemeid);
            int guncelleme = stmt.executeUpdate(sorgu);
            System.out.println("Kayıtlar Güncellendi");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void Sil() {
        Scanner scan = new Scanner(System.in);
        try {
            Listele();
            System.out.print("Silinecek Odemenin Id'sini Giriniz");
            int odeme_id = scan.nextInt();

            String sorgu = String.format("delete from odeme where odeme_id= %d", odeme_id);
            Statement stmt = con.createStatement();
            int silindi = stmt.executeUpdate(sorgu);
            System.out.println("Kayıtlar Silindi");

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void exit() {
        return;
    }
}
