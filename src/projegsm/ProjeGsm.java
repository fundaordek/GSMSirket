/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projegsm;

import java.util.Scanner;
import tablolar.Baz_istasyon;
import tablolar.gsm;
import tablolar.musteri;
import tablolar.odeme;
import tablolar.onarim;
import tablolar.paket;
import tablolar.sube;


public class ProjeGsm {


    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while(true){
        System.out.println("*************");
        System.out.print("1->Musteri Tablosu İcin 1'e Basiniz"
                + ""
                + "\n2->Gsm Tablosu İcin 2'ye Basiniz"
                + ""
                + "\n3->Paket Tablosu İcin 3'e Basiniz"
                + ""
                + "\n4->Onarim Tablosu İcin 4'e Basiniz"
                + ""
                + "\n5->Sube Tablosu İcin 5'e Basiniz"
                + ""
                + "\n6->Baz İstasyon Tablosu İcin 6'ya Basiniz"
                + ""
                + "\n7->Odeme Tablosu İcin 7'ye Basiniz");
        System.out.println("\n*************");
        System.out.print("SECİM YAPİNİZ-->");
        int sec = scanner.nextInt();
        switch (sec) {
            case 1:
                musteri.mustericrud();
                break;
            case 2:
                gsm.gsmcrud();
                break;
            case 3:
                paket.paketcrud();
                break;
            case 4:
                onarim.onarimcrud();
                break;
            case 5:
                sube.subecrud();
                break;
            case 6:
                Baz_istasyon.istasyoncrud();
                break;
            case 7:
                odeme.odemecrud();
                break;
            default:
                System.out.println("Hatali Operator");
        }

    }
    }
}
