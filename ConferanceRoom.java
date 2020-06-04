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
public class ConferanceRoom extends Rooms{
    int numberofseats;
    double seatprice;

    public ConferanceRoom(  int roomnumber,int numberofseats,double seatprice){
        super(roomnumber);
        this.numberofseats = numberofseats;
        this.seatprice = seatprice;
    }
  
    @Override
      public double totalpriceofreservation(){
      return numberofseats*seatprice*daysofreservation;
      }
     
      public String tostring(){
      if(isreserved==true)
          return "the room number is "+roomnumber+"the days of reservation are "+daysofreservation+" the number of seats is "+numberofseats
                  +"the price per seat is "+seatprice+" the room is reserved";
     else
           return "the room number is "+roomnumber+"the days of reservation are "+daysofreservation+" the number of seats is "+numberofseats
                  +"the price per seat is "+seatprice+" the room is not reserved";
      
      }
              
        
        
        
        
        
        
        
        
        
    
    
    
    
    
    
    
}
