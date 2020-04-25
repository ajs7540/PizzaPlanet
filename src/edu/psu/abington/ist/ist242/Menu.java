package edu.psu.abington.ist.ist242;


import java.util.ArrayList;

public class Menu {

    //Class Level Variables - Protect the data
    private int menuId;
    private String menuItem;
    private double itemPrice;
    private int quantityOfItem;

    //Constructor Method
    public Menu(int _menuId, String _menuItem,double _itemPrice,int _quantityOfItem){
        this.menuId = _menuId;
        this.menuItem = _menuItem;
        this.itemPrice = _itemPrice;
        this.quantityOfItem = _quantityOfItem;
    }

    //Setters and Getters
    public int getMenuId() {
        return menuId;
    }
    public void setMenuId(int _menuId) {
        this.menuId = _menuId;
    }

    public String getMenuItem() {
        return menuItem;
    }
    public void setMenuItem(String _menuItem) {
        this.menuItem = _menuItem;
    }
    public double getItemPrice(){
        return itemPrice;
    }
    public void setItemPrice(double _itemPrice){
        this.itemPrice = _itemPrice;
    }
    public int getQuantityOfItem(){
        return quantityOfItem;
    }
    public void setQuantityOfItem(int _quantityOfItem){
        this.quantityOfItem = _quantityOfItem;
    }

    public static void listMenu(ArrayList<Menu> mList){
        for (Menu menu: mList){
            System.out.println("Item: " + menu.getMenuItem());
            System.out.print(" ");
            System.out.println("Price: $"+ menu.getItemPrice());
            System.out.println("Quantity: " + menu.getQuantityOfItem());
        }
    }
}
