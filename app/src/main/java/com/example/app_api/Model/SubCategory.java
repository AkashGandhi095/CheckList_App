package com.example.app_api.Model;

public class SubCategory extends ListItem{
    private String subCategoryName;
    private int subCategoryId;
    private boolean isSubcategoryChecked;

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public int getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(int subCategoryId) {
        this.subCategoryId = subCategoryId;
    }


    public boolean isSubcategoryChecked() {
        return isSubcategoryChecked;
    }

    public void setSubcategoryChecked(boolean subcategoryChecked) {
        isSubcategoryChecked = subcategoryChecked;
    }
}

