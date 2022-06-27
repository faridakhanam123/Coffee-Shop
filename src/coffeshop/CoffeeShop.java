package coffeshop;

import java.util.ArrayList;

public class CoffeeShop {

  private String itemName;
  private double itemPrice;
  
  private ArrayList<CoffeeShop> itemsList;

  CoffeeShop(){
    this.itemsList = new ArrayList<>();
  }

  private CoffeeShop(String itemName, double price){
    this.itemName = itemName;
    this.itemPrice = price;
  }
  public void insertItem(String itemName, double price){
    this.itemsList.add(new CoffeeShop(itemName, price));
  }

  public String getItemName(){
    return this.itemName;
  }

  public double getItemPrice(){
    return this.itemPrice;
  }

  public String getItemsList(){
    String str = "";
    str += "Item No. \t Item Name \t    Item Price\n"; 
    for(int i = 0; i < itemsList.size(); i ++){
      str += "  " + (i + 1) + ".\t\t " + itemsList.get(i).getItemName() + "\t\t " + itemsList.get(i).getItemPrice() + "$" + "\n";
    }

    return str;
  }
  
  public ArrayList<CoffeeShop> getList(){
    return this.itemsList;
  }
}