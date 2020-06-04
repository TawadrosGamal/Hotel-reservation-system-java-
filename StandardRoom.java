/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel;

/**
 *
 * @author tawadros
 */
public class StandardRoom  extends Rooms{
int numberofbeds;
double bedprice;

public StandardRoom(int roomnumber,int numberofbeds,double bedprice){
super(roomnumber);
this.bedprice=bedprice;
this.numberofbeds=numberofbeds;


}

@Override
public double totalpriceofreservation(){
      return numberofbeds*bedprice*daysofreservation;
      }
     

public String tostring(){
      if(isreserved==true)
          return "the room number is "+roomnumber+"the days of reservation are "+daysofreservation+" the number of beds is "+numberofbeds
                  +"the price per bed is "+bedprice+" the room is reserved";
     else
           return "the room number is "+roomnumber+"the days of reservation are "+daysofreservation+" the number of beds is "+numberofbeds
                  +"the price per bed is "+bedprice+" the room is not reserved";
      
}





    
}
