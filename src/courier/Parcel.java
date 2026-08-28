
package courier;

//abstract class
public abstract class Parcel {
      //encapsulation: private
  private String trackingId;
  private String sender;
  private String receiver;
  private double weight;
  private String status;
//constructor
public Parcel(String trackingId, String sender, String receiver, double weight, String status){
    this.trackingId = trackingId;
    this.sender = sender;
    this.receiver = receiver;
    this.weight = weight;
    this.status = status;
}  
//getters: recive data
public String getTrackingId(){
    return trackingId;
}
public String getSender(){
    return sender;
}
public String getReceiver(){
    return receiver;
}
public double getWeight(){
    return weight;
}
public String getStatus(){
    return status;
}
//setters: update data
  public void setSender(String sender){
      this.sender = sender;
  }
  public void setReceiver(String receiver){
      this.receiver = receiver;
  }
  public void setWeight(double weight){
      this.weight = weight;
  }
  public void setStatus(String status){
      this.status = status;
  }

  //abstract method:
  public abstract double calculatePrice();
  public abstract String getParcelType();
  public abstract double getExtraField(); //used for saving data.
  
  //prepare for object data for text file
  public String toFileLine(){
      return getParcelType() + "," + trackingId + "," + sender + "," + receiver + "," + weight + "," + status + "," + getExtraField();
  }
  
  //Overriding the default toString method to format object printout.
  @Override
  public String toString(){
     return trackingId + "|" + getParcelType() + "|" + sender + "|" + receiver + "|" + weight + "KG |" 
             + status + "| Rs." + calculatePrice();  
  }
}
