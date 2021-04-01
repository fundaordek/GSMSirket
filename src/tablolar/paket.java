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


public class paket {
    static Connection con;
    public static void paketcrud(){
    try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gsm", "postgres", "123");
        } catch (Exception e) {
            System.out.println(e);
        }

        Scanner scan = new Scanner(System.in);
        int secim;

        while (true) {
            System.out.println("1.Listele");
            System.out.println("2.Ekle");
            System.out.println("3.Güncelle");
            System.out.println("4.Sil");
            System.out.println("5.Çıkış");
            System.out.print("Seçiminiz:");
            secim = scan.nextInt();

            System.out.println("*****");

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
            ResultSet rs = stmt.executeQuery("select * from paket\n" +
"where paket_tutari =(select\n" +
"min (paket_tutari) from\n" +
"paket)");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getInt(2) + "  " + rs.getInt(3) + " " + rs.getInt(4) + " " + rs.getInt(5));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void Ekle() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Yeni paket_id     :");
        int paket_id = scan.nextInt();
        System.out.print("Yeni konusma bilgisi :");
        int konusma = scan.nextInt();
        System.out.print("Yeni sms bilgisi :");
        int sms = scan.nextInt();
        System.out.print("Yeni internet bilgisi  :");
        int internet = scan.nextInt();
        System.out.print("Yeni paket_tutari  :");
        int paket_tutari = scan.nextInt();

        try {
            Statement stmt = con.createStatement();
            String sorgu = String.format("insert into paket values( %d, %d, %d, %d, %d)", paket_id, konusma, sms, internet, paket_tutari);
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
            System.out.print("eski paket_id girin:");
            int eskipaket_id = scan.nextInt();
            System.out.print("yeni paket_id girin:");
            int paket_id = scan.nextInt();
            System.out.print("yeni konusma bilgisi:");
            int konusma = scan.nextInt();
            System.out.print("yeni sms bilgisi :");
            int sms = scan.nextInt();
            System.out.print("yeni internet bilgisi:");
            int internet = scan.nextInt();
            System.out.print("yeni tutar bilgisi:");
            int paket_tutari = scan.nextInt();

            String sorgu = String.format("update paket set paket_id= %d, konusma= %d ,sms=%d, internet= %d, paket_tutari= %d where paket_id= %d", paket_id, konusma, sms, internet, paket_tutari,eskipaket_id);

            Statement stmt = con.createStatement();
            int guncelleme = stmt.executeUpdate(sorgu);
            System.out.println("Kayıtlar Güncellendi");
        } catch (Exception e) {
            System.out.println(e);
        }
        Listele();
    }

    public static void Sil() {
        Scanner scan = new Scanner(System.in);
        try {
            Listele();
            System.out.print("paket_id girin:");
            int eskipaket_id = scan.nextInt();

            String sorgu = String.format("delete from paket where paket_id= %d", eskipaket_id);
            Statement stmt = con.createStatement();
            int silindi = stmt.executeUpdate(sorgu);
            System.out.println("Kayıtlar Silindi");

        } catch (Exception e) {
            System.out.println(e);
        }
        Listele();

    }
}
