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


public class gsm {
    static Connection con;
    
    public static void gsmcrud(){
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
            ResultSet rs=stmt.executeQuery("select * from gsm_sİrket"); 
            while(rs.next())  
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3)+"  "+rs.getString(4));  
 
        }catch(Exception e){ System.out.println(e);}
         
    }
    
    public static void Ekle()
    {
        Scanner scan= new Scanner(System.in);
        System.out.print("Sirket Id giriniz  :");
        int sirket_id = scan.nextInt();
        System.out.print("Sirket ismi giriniz :");
        String sirket_isim=scan.next();
        System.out.print("Sirket Sahibi Giriniz :");
        String sirket_sahibi=scan.next();
        System.out.print("Hakkimizda :");
        String hakkimizda=scan.next();
        try{
            Statement stmt=con.createStatement(); 
            String sorgu=String.format("insert into gsm_sİrket values( %d, '%s' , '%s', '%s')", sirket_id,sirket_isim,sirket_sahibi,hakkimizda);
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
            System.out.println("Eski Id giriniz");
            int eski_sirketid= scan.nextInt();
            System.out.print("Sırket Id Giriniz:");
            int sirket_id=scan.nextInt();
            System.out.print("Yeni Sırket İsimi Giriniz:");
            String sirket_isim = scan.next();
            System.out.print("Yeni Sirket Sahibi  :");
            String sirket_sahibi=scan.next();
            System.out.print("Hakkimizda :");
            String hakkimizda=scan.next();
            
            String sorgu=String.format("update gsm_sİrket set sirket_id=%d, sirket_isim='%s',sirket_sahibi='%s',hakkimizda='%s' where sirket_id=%d ",sirket_id,sirket_isim,sirket_sahibi,hakkimizda,eski_sirketid) ;
            
            Statement stmt=con.createStatement(); 
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
            System.out.print("Silinecek Sirketini Id'sini Giriniz");
            int sirket_id=scan.nextInt();  
            
            String sorgu=String.format("delete from gsm_sİrket where sirket_id= %d",sirket_id);
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
