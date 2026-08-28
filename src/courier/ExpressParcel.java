
package courier;

public class ExpressParcel extends Parcel{
  private static double RATE_PER_KG = 250.0;
    
    //express parcels have a unique field that standard ones do not
    private double prioritySurchage;
    
    public ExpressParcel(String trackingId, String sender, String receiver, double weight, String status, double prioritySurchage){
        super(trackingId, sender, receiver, weight, status);
        this.prioritySurchage = prioritySurchage; //unique field
    }
    
    //getters and setters for prioritySurchages
    public double getPrioritySurchage(){
        return prioritySurchage;
    }
    public void setPrioritySurchage(double prioritySurchage){
        this.prioritySurchage = prioritySurchage;
    }
    
 //Polymorphism: price logic include extra priority Surchage   
   @Override
   public double calculatePrice(){
       return(getWeight() * RATE_PER_KG )+ prioritySurchage;
   }
   
   @Override
   public String getParcelType(){
       return "Express";
   }
   
   
    @Override
   public double getExtraField(){
       return prioritySurchage;
       //save the surchage to the text file.
   }  
}
*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package courier;

/**
 *
 * @author sathinthasilva
 */
public class ExpressParcel {
    
}
