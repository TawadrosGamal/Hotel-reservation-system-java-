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
public class Floor {
    int floornumber;
    Rooms []a;
    int numberofrooms;

    public Floor() {
    }

    public Floor(int floornumber, Rooms[] a, int numberofrooms) {
        this.floornumber = floornumber;
        this.a = a;
        this.numberofrooms = numberofrooms;
    }
    
    public void displayfree(){
      
    for(int i=0;i<numberofrooms;i++){
    if(a[i].check()==false){
        System.out.println("the room number is "+a[i].roomnumber);  
    }
    }
    }
    public void totalprofit(){
    double sum=0;
    for(int z=0;z<numberofrooms;z++){
    sum+=a[z].totalpriceofreservation();
    
    
    }
        System.out.println("the total profit of the floor is "+sum);
    }
    
    
    
    
}
