package edu.psu.abington.ist.ist242;


import java.util.ArrayList;

public class Order {
    //Class Level Variables - Protect the data
    private int orderId;
    private Customer cust;
    private ArrayList<Menu> menuItem;
    private static ArrayList<Order> orderList = new ArrayList<>();

    //Constructor Method
    public Order(int _orderId){
        this.orderId = _orderId;
    }
    //Setters and Getters
    public int getorderId() {
        return orderId;
    }
    public void setorderId(int _orderId) {
        this.orderId =  _orderId;
    }

    public ArrayList<Menu>getMenuItem(){
        return menuItem;
    }
    public void setMenuItem(ArrayList<Menu> menuItem){
        this.menuItem = menuItem;
    }
    public Customer getCustomer(){
        return cust;
    }
    public void setCustomer(Customer cust){
        this.cust = cust;
    }


    public static void addOrder(Order order, Customer customer, ArrayList<Menu>mList){
        order.setorderId(order.getorderId());
        order.setCustomer(customer);
        order.setMenuItem(mList);
        orderList.add(order);
        System.out.println("\nOrder ID: " + order.getorderId());
        System.out.println("\nMenu Item Sold: ");
        for(Menu menu : mList){
            System.out.print(menu.getMenuItem());
            System.out.print(" x");
            System.out.println(menu.getQuantityOfItem());
        }
        System.out.println("\nCustomer Name: "+ order.getCustomer().getCustomerName()+
                ",Customer ID: " + order.getCustomer().getCustomerId());
    }


}
