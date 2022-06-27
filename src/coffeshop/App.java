package coffeshop;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        boolean close;
        do {
            System.out.println("               ------------------------");
            System.out.println("**************| Welcome to Coffee Shop |**************");
            System.out.println("               ------------------------");
            CoffeeShop shop = new CoffeeShop();
            shop.insertItem("Expresso", 25);
            shop.insertItem("Ristretto", 26);
            shop.insertItem("Long Black", 30);
            shop.insertItem("Mezzo \t", 32);
            shop.insertItem("Cappuccino", 35);
            shop.insertItem("Doppio\t", 27);
            shop.insertItem("Shrt Macchiato", 20);
            shop.insertItem("Long Macchiato", 23);
            shop.insertItem("Latte\t", 21);
            shop.insertItem("Flat White", 24);
            shop.insertItem("Mocha\t", 33);
            shop.insertItem("Affogato", 34);
            shop.insertItem("Batch Brew", 38);
            shop.insertItem("Cold Drip", 37);
            
            System.out.println(shop.getItemsList());
            ArrayList<CoffeeShop> itemsList = shop.getList();
            System.out.print("Enter Item number from the abovle table: ");
            int coffeeChoice;
            int quantity;
            int paymentChoice;
            while(true){
                try{
                    coffeeChoice = getInput();
                    if(coffeeChoice < 1 || coffeeChoice > itemsList.size()){
                        System.err.print("Wrong Input, Please enter correct input: ");
                        continue;
                    }
                   while(true){
                    System.out.print("Enter quantity: ");
                    quantity = getInput();
                    if(quantity < 1){
                        System.out.println("Enter valid quantity");
                        continue;
                    }
                    break;
                   }
                    break;
                }catch(InputMismatchException e){
                    System.out.println("Invalid Input. Please number within the range of the above table.");
                    continue;
                }
            }
            System.out.println(itemsList.get(coffeeChoice - 1).getItemName() + " " + itemsList.get(coffeeChoice - 1).getItemPrice());

            // Selecting Pyment mode
            System.out.println("               --------------");
            System.out.println("**************| Payment Mode |**************");
            System.out.println("               --------------");
            System.out.println("1.\t Cash \n2. \t Card");
            System.out.print("Enter correct choice: ");
            while(true){
                try{
                    paymentChoice = getInput();
                    if(paymentChoice < 1 || paymentChoice > 2){
                        System.out.print("Enter \"1\" or \"2\": ");
                        continue;
                    }
                    break;
                }catch(InputMismatchException e){
                    System.err.println("Invalid Input, Enter \"1\" or \"2\"");
                    System.out.print("Enter correct choice: ");
                    continue;
                }
            }
            switch(paymentChoice){
                case 1: 
                    while(true){
                        System.out.print("Amount Tendered: ");
                        double tenderedAmount = getDoubleInput();
                        if(tenderedAmount < (itemsList.get(coffeeChoice - 1).getItemPrice() * quantity)){
                            System.out.println("Add cash this amount is not enogh to complete the order");
                            continue;
                        }
                        Invoice invoice = new Invoice(itemsList.get(coffeeChoice - 1).getItemName(), itemsList.get(coffeeChoice - 1).getItemPrice(), quantity, tenderedAmount, (tenderedAmount - (itemsList.get(coffeeChoice - 1).getItemPrice() * quantity)));
                        System.out.println(invoice.getCashInvoice());
                        break;
                    }
                    break;
                case 2:
                    while(true){
                        String card = "";
                        System.out.println("\t 1. Visa \n\t 2. Master");
                        System.out.print("Select Your option");
                        int cardType = getInput();
                        if(cardType < 1 || cardType > 2){
                            System.out.println("Ivalid Choice");
                            System.out.println("Select corred option");
                            continue;
                        }
                        if(cardType == 1){
                            card = "Visa";
                        }
                        else{
                            card = "Master";
                        }
                        Invoice invoice = new Invoice(itemsList.get(coffeeChoice - 1).getItemName(), itemsList.get(coffeeChoice - 1).getItemPrice(), quantity, card);
                        System.out.println(invoice.getCardInvoice());
                        break;
                    }
                    break;
            }
            System.out.print("Enter \"0\" to close the shop or any other number to continue shopping: ");
            close = getInput() == 0;
            
        } while (!close);
    }

    public static int getInput(){
        Scanner input = new Scanner(System.in);
        int num = -1;

        while(true){
            try{
                num = input.nextInt();
                break;
            }
            catch(InputMismatchException e ){
                input.next();
                System.out.println("Please enter a valid integer.");
                continue;
            }
        }
        return num;
    }

    public static double getDoubleInput(){
        Scanner input = new Scanner(System.in);
        double num = -1;

        while(true){
            try{
                num = input.nextDouble();
                break;
            }
            catch(InputMismatchException e ){
                input.next();
                System.out.println("Please enter a valid integer.");
                continue;
            }
        }
        return num;
    }
}
