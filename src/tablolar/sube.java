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


public class sube {
    static Connection con;
    public static void subecrud(){
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
            ResultSet rs=stmt.executeQuery("select * from sube"); 
            while(rs.next())  
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
 
        }catch(Exception e){ System.out.println(e);}
         
    }
    
    public static void Ekle()
    {
        Scanner scan= new Scanner(System.in);
        System.out.print("Yeni Sube Id Giriniz     :");
        int sube_id = scan.nextInt();
        System.out.print("Yeni Subenin İsmini Giriniz :");
        String sube_isim=scan.next();
        System.out.print("Yeni Subenin Adresini Giriniz :");
        String sube_adres=scan.next();
        try{
            Statement stmt=con.createStatement(); 
            String sorgu=String.format("insert into sube values( %d,'%s','%s')", sube_id,sube_isim,sube_adres);
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
            System.out.println("Eski Sube Id giriniz");
            int eski_subeid=scan.nextInt();
            System.out.print("Sube Id Giriniz:");
            int sube_id=scan.nextInt();
            System.out.print("Yeni İsim Giriniz:");
            String sube_isim = scan.next();
            System.out.print("Yeni Adres Giriniz    :");
            String sube_adres=scan.next();
            
            Statement stmt=con.createStatement(); 
            String sorgu=String.format("update sube set sube_id=%d,sube_isim='%s',sube_adres='%s' where sube_id=%d ",sube_id,sube_isim,sube_adres,eski_subeid);      
            int guncelleme = stmt.executeUpdate(sorgu);  
            System.out.println("Kayıtlar Güncellendi");
        }catch(Exception e){ System.out.println(e);}
    }
    
    public static void Sil()
    {
        Scanner scan= new Scanner(System.in);
        try{
            Listele();
            System.out.print("Silinecek Subenin Id'sini Giriniz");
            int sube_id=scan.nextInt();  
            
            String sorgu=String.format("delete from sube where sube_id= %d",sube_id);
            Statement stmt=con.createStatement(); 
            int silindi = stmt.executeUpdate(sorgu);  
            System.out.println("Kayıtlar Silindi");
            
        }catch(Exception e){ System.out.println(e);}
    }
    public static void exit(){
        return ;
    }
}
