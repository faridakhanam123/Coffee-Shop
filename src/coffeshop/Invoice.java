package coffeshop;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Invoice {

  private LocalDateTime dateAndTime;
  private String itemName;
  private double itemPrice;
  private double amountTendered;
  private double changeGiven;
  private String cardType;
  private int quantity;
  
  Invoice(String itemName, double price, int quantity,  double amountTendered, double changeGiven){
    this.dateAndTime = LocalDateTime.now();
    this.itemName = itemName;
    this.itemPrice = price;
    this.amountTendered = amountTendered;
    this.changeGiven = changeGiven;
    this.quantity = quantity;
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formattedDateAndTime = dateAndTime.format(format);

    writeCastItemToFile(formattedDateAndTime, itemName, price, quantity, amountTendered, changeGiven);
  }

  Invoice(String itemName, double price, int quantity, String cardType){
    this.dateAndTime = LocalDateTime.now();
    this.itemName = itemName;
    this.itemPrice = price;
    this.cardType = cardType;
    this.quantity = quantity;

    
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    String formattedDateAndTime = dateAndTime.format(format);
    writeCardItemToFile(formattedDateAndTime, itemName, price, quantity, cardType);
  }

  private void writeCastItemToFile(String formattedDateAndTime, String itemName, double price, int quantity, double amountTendered,
      double changeGiven) {

        try {
          FileWriter writer = new FileWriter("transactions.txt", true);
          
          String textToAppend = "********Cash Payment)********\n";          
          textToAppend  += formattedDateAndTime + ", " + itemName + " " + price + " " + amountTendered + " " + -(amountTendered - (price * quantity)) +"\n";
          writer.write(textToAppend);
          writer.close();
          System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
          System.out.println("An error occurred.");
          e.printStackTrace();
        }
  }

  private void writeCardItemToFile(String formattedDateAndTime, String itemName, double price, int quantity, String cardType) {

    try {
      FileWriter writer = new FileWriter("transactions.txt", true);
      String textToAppend = "********Card Payment)********\n";
      textToAppend  += formattedDateAndTime + ", " + itemName + " " + price + " " + cardType + "\n";
      writer.write(textToAppend);
      writer.close();
      System.out.println("Successfully wrote to the file.");
    } catch (IOException e) {
      System.out.println("An error occurred.");
      e.printStackTrace();
    }
  }

  public String getCardInvoice(){
    String str = "         ---------\n********| Invoice |********\n         ---------\n";
    str += "Item Name : " + this.itemName + "\n";
    str += "Item Price : " + this.itemPrice + "\n";
    str += "Item Qty : " + this.quantity+ "\n";
    str += "Card Type : " + this.cardType+ "\n";
    str += "\nTotal : " + (this.quantity * this.itemPrice)+ "\n";
    str += "---(Thank Your for your choice)----";
    return str;
  }

  public String getCashInvoice(){
    String str = "         ---------\n********| Invoice |********\n         ---------\n";
    str += "Item Name : " + this.itemName + "\n";
    str += "Item Price : " + this.itemPrice + "\n";
    str += "Item Qty : " + this.quantity+ "\n";
    str += "Tendered Amount : " + this.amountTendered+ "\n";
    str += "\nTotal : " + (this.quantity * this.itemPrice)+ "\n";
    str += "Changes Given: " + (-this.changeGiven)+ "\n";
    str += "---(Thank Your for your choice)----";
    return str;
  }
}
