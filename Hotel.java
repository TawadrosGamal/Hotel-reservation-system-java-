/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

/**
 *
 * @author tawadros*/
 import java.io.*;
import java.util.Scanner;
public class Hotel {

    
    public static void main(String[] args) throws FileNotFoundException {
     File f=new File("hotel.txt");
     Scanner input =new Scanner(f);
     String s1=input.nextLine();
     String [] s=new String[2];
     s=s1.split(" ");
     int numberoffloors =Integer.parseInt(s[1]);
     Floor [] foo=new Floor[numberoffloors];
     for(int i=0;i<numberoffloors;i++){
     s1=input.nextLine();
     s=new String[3];
     s=s1.split(" ");
     int numberofrooms =Integer.parseInt(s[2]);
     Rooms [] t=new Rooms[numberofrooms];
         for (int j = 0; j < t.length; j++) {
             s1=input.nextLine();
             s=new String[4];
             s=s1.split(", ");
             if(s[0].equals("Standard")){
                 int roomnumber=Integer.parseInt(s[1]);
                 int numberofbeds=Integer.parseInt(s[2]);
                 double bedprice=Double.parseDouble(s[3]);
                 t[j]=new StandardRoom(roomnumber, numberofbeds, bedprice);
             }
             else if(s[0].equals("Conference")){
                  int roomnumber=Integer.parseInt(s[1]);
                 int numberofseats=Integer.parseInt(s[2]);
                 double seatprice=Double.parseDouble(s[3]);
                 t[j]=new ConferanceRoom(roomnumber, numberofseats, seatprice);
             }
             else if(s[0].equals("Sweet")){
             int roomnumber=Integer.parseInt(s[1]);
                 double pricepernight=Double.parseDouble(s[2]);
                 t[j]=new SweetRoom(roomnumber,pricepernight);
             
             
             }    
              
         }
         foo[i]=new Floor(i+1, t, numberofrooms);
     
     }
    }
    
}
