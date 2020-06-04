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
public class SweetRoom extends Rooms{
   double pricepernight;

    public SweetRoom( int roomnumber,double pricepernight) {
        super(roomnumber);
        this.pricepernight = pricepernight;
    }
  
   @Override
   public double totalpriceofreservation(){
      return pricepernight*daysofreservation;
      }
       public String tostring(){
      if(isreserved==true)
          return "the room number is "+roomnumber+"the days of reservation are "+daysofreservation+" the price per night is "+pricepernight
           +  "the room is reserved";
     else
           return "the room number is "+roomnumber+"the days of reservation are "+daysofreservation+" the price per nigt is "+pricepernight
         +" the room is not reserved";
      
  }
}
