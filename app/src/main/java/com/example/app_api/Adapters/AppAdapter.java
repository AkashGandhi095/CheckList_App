package com.example.app_api.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.app_api.Model.Category;
import com.example.app_api.Model.ListItem;
import com.example.app_api.Model.SubCategory;
import com.example.app_api.R;
import com.example.app_api.Utils.OnClick;
import com.google.android.material.checkbox.MaterialCheckBox;

import java.util.List;

public class AppAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private List<ListItem> list;

    private OnClick onClick;

    public AppAdapter(List<ListItem> list , OnClick onClick) {
        this.list = list;
        this.onClick = onClick;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == TYPE_HEADER)
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_view , parent, false);
            return new CategoryViewHolder(view);
        }
        else
        {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.subcategory_view , parent , false);
            return new SubCategoryViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        if(holder instanceof CategoryViewHolder)
        {
            Category category = (Category) list.get(position);
            CategoryViewHolder viewHolder = (CategoryViewHolder) holder;
            viewHolder.categoryName.setText(category.getCategoryName());

            viewHolder.categoryCheckBox.setChecked(category.isCategoryChecked());

            viewHolder.categoryCheckBox.setOnClickListener(v -> {

                onClick.onCategoryClick(category);

            });


           /* viewHolder.categoryCheckBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
                Log.d("categoryCheckBox", "onBindViewHolder: " +isChecked);

            });*/

        }
        else
        {
            SubCategory subCategory = (SubCategory) list.get(position);
            SubCategoryViewHolder viewHolder = (SubCategoryViewHolder) holder;
            viewHolder.subCategoryName.setText(subCategory.getSubCategoryName());
            viewHolder.subCategoryCheckBox.setChecked(subCategory.isSubcategoryChecked());

            viewHolder.subCategoryCheckBox.setOnClickListener(v -> {

                onClick.onSubCategoryClick(subCategory);

            });

        }
    }



    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        if(list.get(position) instanceof Category)
            return TYPE_HEADER;
        else
            return TYPE_ITEM;
    }

    protected static class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView categoryName;
        MaterialCheckBox categoryCheckBox;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryCheckBox = itemView.findViewById(R.id.categoryCheck);
        }
    }
    protected static class SubCategoryViewHolder extends RecyclerView.ViewHolder{
        TextView subCategoryName;
        MaterialCheckBox subCategoryCheckBox;

        public SubCategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            subCategoryName = itemView.findViewById(R.id.subCategoryText);
            subCategoryCheckBox = itemView.findViewById(R.id.subCategoryCheck);
        }
    }
}
