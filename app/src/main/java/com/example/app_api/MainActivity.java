package com.example.app_api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.app_api.Adapters.AppAdapter;
import com.example.app_api.Model.Category;
import com.example.app_api.Model.ListItem;
import com.example.app_api.Model.SubCategory;
import com.example.app_api.Utils.ApiSingleton;
import com.example.app_api.Utils.OnClick;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnClick {

    private static final String URL = "https://run.mocky.io/v3/f79cbce1-a70e-4313-8d76-00d19ee3b4c1";
    private ArrayList<ListItem> listItems;
    private AppAdapter adapter;
    private RecyclerView recyclerView;
    private int clickCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        getResponseFromServer();



    }

    private void initViews() {
        listItems = new ArrayList<>();
        recyclerView = findViewById(R.id.parent_recycler);
        adapter = new AppAdapter(listItems , this);
        recyclerView.setAdapter(adapter);
    }

    private void getResponseFromServer() {
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null,
                this::BindResponseToView,
                error -> {
                    Log.e("getResponseFromServer", ""+error.getLocalizedMessage());
                });


        ApiSingleton.
                getInstance(MainActivity.this).
                getRequestQueue().
                add(request);
    }

    private void BindResponseToView(JSONArray response) {

        Log.d("response", "Data->\n" +response);
        Category category;
        SubCategory subCategory;
        JSONArray subCategoryArray;
        try {

            for (int i = 0; i < response.length(); i++) {
                JSONObject object = response.getJSONObject(i);
                category = new Category();
                category.setCategoryId(object.getInt("id"));
                Log.d("category", "BindResponseToView: ");
                category.setCategoryName(object.getString("name"));
                category.setCategoryChecked(false);
                listItems.add(category);
                if(object.has("subCategory"))
                {
                    subCategoryArray = object.getJSONArray("subCategory");

                    for(int j = 0; j<subCategoryArray.length(); j++)
                    {
                        JSONObject jsonObject = subCategoryArray.getJSONObject(j);

                        subCategory = new SubCategory();
                        Log.d("subCategory", "BindResponseToView: ");
                        subCategory.setSubCategoryId(jsonObject.optInt("parent_id"));
                        subCategory.setSubCategoryName(jsonObject.optString("name"));
                        subCategory.setSubcategoryChecked(false);
                        listItems.add(subCategory);

                    }
                }

            }

            adapter.notifyDataSetChanged();


        }
        catch (JSONException e)
        {
            Log.e("JSONException", "something went wrong : "+e.getLocalizedMessage() );
            e.printStackTrace();
        }
    }

    @Override
    public void onCategoryClick(Category category) {
        Log.d("categoryClickListener", ""+category.isCategoryChecked());



            for(int i=0;i<listItems.size();i++)
            {
                if(listItems.get(i) instanceof SubCategory)
                {

                    if(((SubCategory) listItems.get(i)).getSubCategoryId() == category.getCategoryId())
                    {
                        Log.d("subCategoryList", ""+((SubCategory) listItems.get(i)).getSubCategoryName());
                        if(!category.isCategoryChecked())
                        {
                            //category.setCategoryChecked(true);
                            ((SubCategory) listItems.get(i)).setSubcategoryChecked(true);
                        }
                        else
                        {
                            //category.setCategoryChecked(false);
                            ((SubCategory) listItems.get(i)).setSubcategoryChecked(false);
                        }


                    }
                }
            }

            if(!category.isCategoryChecked())
            {

                category.setCategoryChecked(true);
            }
            else
            {
                category.setCategoryChecked(false);
            }




        adapter.notifyDataSetChanged();
    }

    @Override
    public void onSubCategoryClick(SubCategory subCategory) {
        Log.d("subCategoryClickListen", ""+subCategory.isSubcategoryChecked());




        for(int i=0;i<listItems.size();i++)
        {
            if(listItems.get(i) instanceof Category)
            {
                if(((Category) listItems.get(i)).getCategoryId() == subCategory.getSubCategoryId())
                {
                    Log.d("onSubCategoryClick", ""+((Category) listItems.get(i)).getCategoryName());

                    if(!subCategory.isSubcategoryChecked())
                    {
                        clickCount++;
                        ((Category) listItems.get(i)).setCategoryChecked(true);
                    }
                    else
                    {
                        clickCount--;
                        if (clickCount == 0)
                        {
                            
                            ((Category) listItems.get(i)).setCategoryChecked(false);
                        }

                    }
                }
            }
        }

        if(!subCategory.isSubcategoryChecked())
        {

            subCategory.setSubcategoryChecked(true);
        }
        else
        {

            subCategory.setSubcategoryChecked(false);
        }

        adapter.notifyDataSetChanged();
    }


  /*  @Override
    public void onCategoryCheck(boolean isChecked, Category category) {
        Log.d("MainOnCategoryCheck", "onCategoryCheck: " + isChecked+ category.getCategoryId());


        for (int i = 0; i < listItems.size(); i++) {
            if(listItems.get(i) instanceof SubCategory) {

                SubCategory subCategory = (SubCategory) listItems.get(i);

                if (subCategory.getSubCategoryId() == category.getCategoryId()) {
                    category.setCategoryChecked(isChecked);
                    subCategory.setSubcategoryChecked(isChecked);

                }

            }
        }

        recyclerView.post(() -> {
            adapter.notifyDataSetChanged();
        });

       // Log.d("onCategoryCheck", "listItem-length: " + listItems.size());
    }

    @Override
    public void onSubCategoryCheck(boolean isChecked, SubCategory subCategory) {




        for (int i = 0; i < listItems.size(); i++) {

            if(listItems.get(i) instanceof Category)
            {
                Category category = (Category) listItems.get(i);

                if(category.getCategoryId() == subCategory.getSubCategoryId())
                {
                    Log.d("categoryName", category.getCategoryName());

                }
            }
        }
        // Log.d("onSubCategoryCheck", "listItem-length: " +listItems.size());


    }*/
}