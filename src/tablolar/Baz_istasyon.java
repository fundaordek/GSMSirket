package tablolar;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;


public class Baz_istasyon {

    static Connection con;

    public static void istasyoncrud() {
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
            ResultSet rs = stmt.executeQuery("select * from baz_istasyon");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3));
            }

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void Ekle() {
        Scanner scan = new Scanner(System.in);
        System.out.print("Yeni Istasyon Id Giriniz     :");
        int istasyon_id = scan.nextInt();
        System.out.print("Yeni Baz Istasyon İlini Giriniz :");
        String il = scan.next();
        System.out.print("Yeni Baz Istasyon İlce Giriniz :");
        String ilce = scan.next();
        try {
            Statement stmt = con.createStatement();
            String sorgu = String.format("insert into sube values( %d,'%s','%s')", istasyon_id, il, ilce);
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
            System.out.println("Eski İstasyon Id giriniz");
            int eski_istasyonid = scan.nextInt();
            System.out.print("İstasyon Id Giriniz:");
            int istasyon_id = scan.nextInt();
            System.out.print("İl Giriniz:");
            String il = scan.next();
            System.out.print("İlce Giriniz :");
            String ilce = scan.next();

            Statement stmt = con.createStatement();
            String sorgu = String.format("update baz_istasyon set istasyon_id=%d,il='%s',ilce='%s' where istasyon_id=%d ", istasyon_id, il, ilce, eski_istasyonid);
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
            System.out.print("Silinecek Baz Istasyon Id'sini Giriniz");
            int istasyon_id = scan.nextInt();

            String sorgu = String.format("delete from baz_istasyon where istasyon_id= %d", istasyon_id);
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
