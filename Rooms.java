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
public abstract class  Rooms {
  int roomnumber;
int daysofreservation;
boolean isreserved;

    public Rooms() {
        isreserved=false;
        daysofreservation=0;
    }

    public Rooms(int roomnumber) {
        this.roomnumber = roomnumber;
    }
     public void reserve(int c){
    isreserved=true;
        System.out.println("now the room is reserved");
        daysofreservation=c;
    }
     
    public boolean check(){
    if(isreserved==true)
            return true;
    else
          return false;
    }
     public abstract double  totalpriceofreservation ();
      
     

}
