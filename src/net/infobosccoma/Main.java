/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.infobosccoma;

import java.io.File;
import java.io.PrintStream;
import net.infobosccoma.view.View;
import net.infobosccoma.view.Vista;

/**
 *
 * @author Maxi
 */
public class Main {
    
    public static void main(String[]args){
        //test();
        Vista window = new Vista();
        window.setTitle("Formulari Java");
        //window.setIconImage(null);
        window.setSize(830 ,420);
        window.setResizable(false);
        window.setVisible(true);
      
    }
     public static void test(){
             try {
            PrintStream ps = new PrintStream(new File("./test.csv"));
            ps.println("12345;maxi;villanueva;tolcachier;21");
            ps.println("ghd34;albert;martinez;serrano;22");
            ps.println("987sda;albert;quer;x;20");
        } catch (Exception ex) {
            
        }
   
    }
    
}
