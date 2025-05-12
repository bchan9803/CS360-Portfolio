package com.bryanchan.cs360_project;

public class ItemModel {
    String itemName;
    int itemCount;

    public ItemModel(String itemName, int itemCount) {
        this.itemName = itemName;
        this.itemCount = itemCount;
    }

    public String getItemName() {
        return this.itemName;
    }

    public int getItemCount() {
        return this.itemCount;
    }
}
