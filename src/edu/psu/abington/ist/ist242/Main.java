package edu.psu.abington.ist.ist242;
/*
Project: Lab 9
Purpose Details: Pizza ordering application
Course: IST 242
Author: Amal Sabirov
Date Developed: 3/29/20
Last Date Changed: 3/29/20
Rev: 4
 */

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static int tCount = 1; //transaction count
    private static int oCount = 1; //order count
    int cCount = 1;  // customer count
    private static Scanner scnr = new Scanner(System.in);

    public static void main(String[] args) {

        Main main = new Main();

        final char EXIT_CODE = 'E';
        final char CUST_CODE = 'C';
        final char MENU_CODE = 'M';
        final char ORDE_CODE = 'O';
        final char TRAN_CODE = 'T';
        final char CUST_PRNT = 'P';
        char userAction;

        final String PROMPT_ACTION = "Add 'C'ustomer, 'P'rint Customer, List 'M'enu, Add 'O'rder, List 'T'ransaction or 'E'xit: ";
        ArrayList<Customer> cList = new ArrayList<>();
        ArrayList<Menu> mList = new ArrayList<>();
        ArrayList<Order> oList = new ArrayList<>();
        ArrayList<Transaction> tList = new ArrayList<>();

        Transaction trans1;

        Menu menu1 = new Menu(1, "Plain", 9.99, 10);
        Menu menu2 = new Menu(2, "Meat Lovers", 12.99, 8);
        Menu menu3 = new Menu(3, "Extra Cheese", 11.50, 6);
        Menu menu4 = new Menu(4, "Vegan", 11.99, 10);

        mList.add(menu1);
        mList.add(menu2);
        mList.add(menu3);
        mList.add(menu4);



        userAction = getAction(PROMPT_ACTION);

        while (userAction != EXIT_CODE) {
            switch (userAction) {
                case CUST_CODE:
                    Customer c = main.addCustomer(cList);
                    cList.add(main.addCustomer(cList));
                    if (c.getCustomerId() < cList.size()) {
                        cList.remove(c.getCustomerId());
                    }
                    cList.add(c.getCustomerId(), c);
                    break;
                case CUST_PRNT: // p
                    Customer.printCustomer(cList);
                    break;
                case MENU_CODE: // m
                    Menu.listMenu(mList);
                    break;
                case ORDE_CODE: // o

                    System.out.print("Enter customer ID: go by Index");
                    int cid = scnr.nextInt();
                    if (cid < cList.size()){
                        ArrayList<Menu> subMenu = selectMenu(mList);
                        Order x = new Order (oCount++); // increases order count
                        Order.addOrder(x, cList.get(cid), subMenu); // adds order from order.java using menuList
                        oList.add(x); // adds order to order list
                        trans1 = makePayment(x);
                        tList.add(trans1);
                    } else {
                        System.out.println("Customer ID Does NOT Exist!");
                    }
                    break;
                case TRAN_CODE:
                    Transaction.listTransactions(tList);
                    break;
            }

            userAction = getAction(PROMPT_ACTION);
        }
    }

    private static Transaction makePayment(Order order1) {
        double total = 0;
        double amt;
        System.out.println("Your bill is:");
        for (Menu menu : order1.getMenuItem()) {
            System.out.print(menu.getMenuItem()); // prints out menu item customer selected
            System.out.print(" quantity: ");
            System.out.println(menu.getQuantityOfItem()); // quantity of how much they purchased
            amt = menu.getQuantityOfItem() * menu.getItemPrice(); // quantity x item price
            total = total + amt;
        }
        System.out.println("Your Total is:");
        System.out.println(total); // prints out total amount

        int option;
        Transaction t;

        while (true) {
            System.out.print("Select Payment Option: "); // prompts user how they would like to pay
            System.out.println("\n1. Cash");
            System.out.println("2. Credit");
            option = scnr.nextInt();
            if (option == 1) {

                t = new Transaction(tCount++, order1, PaymentType.cash); // if user selects 1 then cash
                return t;
            } else if (option == 2) {
                t = new Transaction(tCount++, order1, PaymentType.credit); // if 2 then credit
                return t;
            }
        }
    }

    public static ArrayList<Menu> selectMenu(ArrayList<Menu> menus) {
        System.out.println("Select menu by ID: Enter 0 to finish");  // menu for when they are adding an order
        for (Menu menu : menus)
            System.out.println("" + menu.getMenuId() + " for " + menu.getMenuItem()); //lists 1-4 and each menu item
        int x;
        ArrayList<Menu> menus1 = new ArrayList<>();
        while (true) {
            x = scnr.nextInt();
            if (x == 0)
                break;
            System.out.print("Quantity of Items: "); // prompts user how many they would like
            int quantityOfItems = scnr.nextInt();
            Menu item = menus.get(x - 1);
            item.setQuantityOfItem(quantityOfItems); // changes quantity of that item
            menus1.add(item);
            return menus1;
        }
        return menus1;
    }

    public static char getAction(String prompt) {
        String answer = "";
        System.out.println(prompt);
        answer = scnr.nextLine().toUpperCase() + " ";
        char firstChar = answer.charAt(0);
        return firstChar;
    }

    public Customer addCustomer(ArrayList<Customer> cList) { //asks user if they want to edit a existing or new customer
        Customer cust = null;
        char in = '1';
        while (true) {
            System.out.println("'E'xisting Customer, 'N'ew Customer?"); // if e then edit info and if N then add new customer
            in = scnr.nextLine().toUpperCase().charAt(0);
            if (in == 'E' || in == 'N') {
                break;
            }
        }

        if (in == 'E') {  // if e  then asks for
            System.out.println("Please Enter your ID: ");
            int id = Integer.parseInt(scnr.nextLine());
            cust = cList.get(id);
            System.out.println("\nCustomer ID:" + cust.getCustomerId());
            System.out.println("Customer Name:" + cust.getCustomerName());
            System.out.println("Customer Phone:" + cust.getCustomerPhoneNumber());
            while (true) {
                System.out.println("Change 'N'ame, Change 'P'honeNumber or 'E'xit?");
                in = scnr.nextLine().toUpperCase().charAt(0);
                if (in == 'N') {
                    System.out.println("Please Enter your Name: ");
                    cust.setCustomerName(scnr.nextLine());
                } else if (in == 'P') {
                    System.out.println("Please Enter your Phone: ");
                    cust.setCustomerPhoneNumber(scnr.nextLine());
                } else if (in == 'E') {
                    break;
                }
            }
        }
        else { // if a then will add a new customer
            cust = new Customer(cCount++); //increases customer count in list
            Scanner scnr = new Scanner(System.in);
            System.out.println("Please Enter your Name: ");
            cust.setCustomerName(scnr.nextLine());
            System.out.println("Please Enter your Phone: ");
            cust.setCustomerPhoneNumber(scnr.nextLine());
        }
        return cust;
    }
}
