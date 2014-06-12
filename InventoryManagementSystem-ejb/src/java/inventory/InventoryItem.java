package inventory;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import com.google.gson.Gson;

/**
 *
 * @author markburton
 */
public class InventoryItem {
    private String name;
    private double price;
    private int quantity;
            
    public InventoryItem() {
    }

    public InventoryItem(String name, String price, String quantity) {
        this.name = name;
        if(isDouble(price)){
            this.price = Double.parseDouble(price);
        }
        if(isInteger(quantity)){
            this.price = Integer.parseInt(quantity);
        }
    }
    
    public InventoryItem(String name, Double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
    
    /**
     * Creates Inventory Item from JSON string
     * @param json 
     */
    public InventoryItem(String json) {
        this.equals(new Gson().fromJson(json, InventoryItem.class));
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getName() {
        return name;
    }
    
    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
   
    public String convertItemToJSON() {
        return new Gson().toJson(this);
    }
}
