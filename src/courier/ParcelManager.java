
package courier;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParcelManager {
   //A dynamic list to hold all our parcel object in memory
    private List<Parcel> parcels;
    //text file save data
    private static final String File_Name = "parcels.txt";  //local database file
    
    //constructor
    public ParcelManager(){
        parcels = new ArrayList<>();
        loadFromFile(); //automatically load saved data when the manager starts
    }
    
    //1.new parcel add to the system(create)
    public boolean addParcel(Parcel parcel){
        //prevent duplicate tracking ids
        if(findByTrackingId(parcel.getTrackingId()) != null) {
            return false;
        }
        parcels.add(parcel); //add new parcel into list
        saveToFile(); //save changes to text file
        return true;
    }
    
    //2.get all parcel from system (Read)
    public List<Parcel> getAllParcels() {
        return parcels;
    }
    
    //3. give track id and search all parcel (Search)
    public Parcel findByTrackingId(String trackingId){
        for(Parcel p : parcels){
            //equalsIgnoreCase prevent error
            if (p.getTrackingId().equalsIgnoreCase(trackingId)){
                return p;
            }
        }
        return null; //if cant find null
    }
    
  //4.change parcel status (Update)
    public boolean updateStatus(String trackingId, String newStatus){
        Parcel p = findByTrackingId(trackingId); //find parcel
        if(p == null) return false;
        
        p.setStatus(newStatus); //set new status
        saveToFile(); 
        return true;
    }
    
  //5.clear parcel (Delete)
    public boolean deleteParcel(String trackingId) {
        Parcel p = findByTrackingId(trackingId);
        if (p == null) return false;
        
        parcels.remove(p); //remove from list
        saveToFile(); //remove from text file to save
        return true;
    }
 
//== System Logics    
    
 //Loops through all parcels to count how many are not "delivered"
    public int getActiveDeliveriesCount(){
        int count = 0;
        for(Parcel p : parcels){
            if(!p.getStatus().equalsIgnoreCase("Delivered")){
                count++;
            }
        }
        return count;
    }
    
    //Find the total revenue by adding up the prices of all parcels in the system.
    public double getSystemRevenue(){
        double total = 0;
        for(Parcel p : parcels){
            total += p.calculatePrice();
        }
        return total;
    }

//== File Handling (to write in text file)   
    
    //Writes the list of objects into a textfile.
    private  void saveToFile(){
        try(PrintWriter writer = new PrintWriter(new FileWriter(File_Name))){
            for(Parcel p : parcels){
                writer.println(p.toFileLine());
            }
        } catch(IOException e){
            System.out.println("Error saving parcels: "+ e.getMessage());
        }
    }
    
    //Read the text file to list
    private void loadFromFile(){
        File file = new File(File_Name);
        if(!file.exists())
            return; //if not file not doing
        
        try (BufferedReader reader = new BufferedReader(new FileReader(file))){
            String line;
            while((line = reader.readLine()) != null) {
                if(line.trim().isEmpty()) continue;
                
                Parcel p = parseLine(line);  //String line convert into parcel obj
                if(p != null) parcels.add(p);
            }
            
        }catch(IOException e){
            System.out.println("Error loading parcels: " + e.getMessage());
        }
    }
    
   //Helper method to turn a line of text back into a proper statndard ore express
    private Parcel parseLine(String line){
        String[] parts = line.split(",");//splint the string by commas
        if(parts.length < 7 )
            return null;
        
        String type = parts[0];
        String trackingId = parts[1];
        String sender = parts[2];
        String receiver = parts[3];
        double weight = Double.parseDouble(parts[4]); //convert string double
        String status = parts[5];
        double extra = Double.parseDouble(parts[6]);
        
        if(type.equalsIgnoreCase("Express")){
            return new ExpressParcel(trackingId, sender, receiver, weight, status, extra);
        }else{
            return new StandardParcel(trackingId, sender, receiver, weight, status);
        }
    }
     
}
