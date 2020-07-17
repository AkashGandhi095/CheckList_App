package com.example.app_api.Model;

public class Category extends ListItem{
    private String categoryName;
    private int categoryId;
    private boolean isCategoryChecked;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public boolean isCategoryChecked() {
        return isCategoryChecked;
    }

    public void setCategoryChecked(boolean categoryChecked) {
        isCategoryChecked = categoryChecked;
    }

}
