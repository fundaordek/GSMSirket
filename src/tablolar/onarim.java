package tablolar;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;
import java.lang.String;
import java.util.Date;
 
public class onarim {
    static Connection con;

    public static void onarimcrud(){
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
            ResultSet rs=stmt.executeQuery("SELECT * FROM onarim where sonbakim_tarih between '01.01.2019' AND '26.11.2019'"); 
            while(rs.next())  
            System.out.println(rs.getInt(1)+"  "+rs.getString(2)+" "+rs.getString(3));  
 
        }catch(Exception e){ System.out.println(e);}
         
    }
    
    public static void Ekle()
    {
        Scanner scan= new Scanner(System.in);
        System.out.print("Yeni Onarim Id Giriniz     :");
        int onarim_id = scan.nextInt();
        System.out.print("Son_bakim Tarihi Giriniz   :");
        String sonbakim_tarih=scan.next();
        System.out.print("Onarim Nedeni Giriniz :");
        String onarim_nedeni=scan.next();
        try{
            Statement stmt=con.createStatement(); 
            String sorgu=String.format("insert into onarim values( %d, '%s','%s')",onarim_id,sonbakim_tarih,onarim_nedeni);
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
            System.out.println("Eski Onarim Id Giriniz");
            int eski_onarimid=scan.nextInt();
            System.out.print("Yeni Onarim Id giriniz");
            int onarim_id=scan.nextInt();
            System.out.print("Yeni Sonbakim tarihi Giriniz:");
            String sonbakim_tarih = scan.next();
            System.out.print("Yeni Onarim Nedeni Giriniz   :");
            String onarim_nedeni=scan.next();
            
            Statement stmt=con.createStatement(); 
            String sorgu=String.format("update onarim set onarim_id=%d,sonbakim_tarih='%s',onarim_nedeni='%s' where onarim_id=%d ", onarim_id,sonbakim_tarih,onarim_nedeni,eski_onarimid);      
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
            System.out.print("Silinecek Onarimin Id'sini Giriniz");
            int onarim_id=scan.nextInt();  
            
            String sorgu=String.format("delete from onarim where onarim_id= %d",onarim_id);
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

