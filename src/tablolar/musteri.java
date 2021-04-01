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


public class musteri {
    static Connection con;
    public static void mustericrud(){
        
         try{
            Class.forName("org.postgresql.Driver");  
            con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Gsm","postgres","123");  
        }catch(Exception e){ System.out.println(e);}
       
        
        Scanner scan= new Scanner(System.in);
        int secim;
        
        while(true)
        {
            System.out.println("*************");
            System.out.println("1.Listele");
            System.out.println("2.Ekle");
            System.out.println("3.Güncelle");
            System.out.println("4.Sil");
            System.out.println("5.Çıkış");
            System.out.print("Seçiminiz:");
            secim=scan.nextInt();
 
            System.out.println("*************");
            
            if(secim==1) Listele();
            if(secim==2) Ekle();
            if(secim==3) Guncelle();
            if(secim==4) Sil();
            if(secim==5) exit();{
                try{
                    con.close(); 
                }catch(Exception e){ System.out.println(e);}
                
                break;
            }            
        }
    }
    
    public static void Listele()
    {
        try{  
            Statement stmt=con.createStatement();  
            ResultSet rs=stmt.executeQuery("select * from musteri"); 
            while(rs.next())  
            System.out.println(rs.getInt(1)+"  "+rs.getInt(2)+" "+rs.getString(3)+"  "+rs.getString(4));  
 
        }catch(Exception e){ System.out.println(e);}
         
    }
    
    public static void Ekle()
    {
        Scanner scan= new Scanner(System.in);
        System.out.print("Yeni Musteri ID     :");
        int musteri_id = scan.nextInt();
        System.out.print("Yeni Telefon Numarasi    :");
        int telefon_no=scan.nextInt();
        System.out.print("Yeni Müsteri Ismi :");
        String isim=scan.next();
        System.out.print("Yeni Müsteri Soyismi :");
        String soyisim=scan.next();
        try{
            Statement stmt=con.createStatement(); 
            String sorgu=String.format("insert into musteri values( %d, %d , '%s','%s')", musteri_id,telefon_no,isim,soyisim);
            int ekleme = stmt.executeUpdate(sorgu);
            System.out.println("Kayıt Eklendi");
        }catch(Exception e){ System.out.println(e);}
        Listele();
        
            
    }
    
    public static void Guncelle()
    {
        Scanner scan= new Scanner(System.in);
        try{
            Listele();
            System.out.println("Eski Musteri Id giriniz");
            int eski_musteriid=scan.nextInt();
            System.out.print("Musteri Id Giriniz:");
            int musteri_id=scan.nextInt();
            System.out.print("Musteri Telefon No Giriniz:");
            int telefon_no = scan.nextInt();
            System.out.print("Yeni Musteri Adı    :");
            String isim=scan.next();
            System.out.print("Yeni Musteri Soyadı :");
            String soyisim=scan.next();
            
            Statement stmt=con.createStatement(); 
            String sorgu=String.format("update musteri set musteri_id=%d, telefon_no=%d,isim='%s',soyisim='%s' where musteri_id=%d ", musteri_id,telefon_no,isim,soyisim,eski_musteriid);      
            int guncelleme = stmt.executeUpdate(sorgu);  
            System.out.println("Kayıtlar Güncellendi");
        }catch(Exception e){ System.out.println(e);}
        Listele();
    }
    
    public static void Sil()
    {
        Scanner scan= new Scanner(System.in);
        try{
            Listele();
            System.out.print("Silinecek Musterinin Id'sini Giriniz");
            int musteri_id=scan.nextInt();  
            
            String sorgu=String.format("delete from musteri where musteri_id= %d",musteri_id);
            Statement stmt=con.createStatement(); 
            int silindi = stmt.executeUpdate(sorgu);  
            System.out.println("Kayıtlar Silindi");
            
        }catch(Exception e){ System.out.println(e);}
        Listele();
    }
    public static void exit(){
        return ;
    }
}
