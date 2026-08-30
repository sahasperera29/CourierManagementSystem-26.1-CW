
package courier;


public class StandardParcel extends Parcel{
    private static final double RATE_PER_KG = 150.0;
    
    public StandardParcel(String trackingId, String sender, String receiver, double weight, String status){
        super(trackingId, sender, receiver, weight, status);
    }
  
//Polymorphism: provide calculation formula for standard deliveries.    
  @Override
  public double calculatePrice(){
      return getWeight() * RATE_PER_KG;
  }
  
  @Override
  public String getParcelType(){
      return "Standard";
  }
 
  @Override
  public double getExtraField(){
      return 0.0;
      //standard parcels dont have surcharges, se we return 0
  }
}
