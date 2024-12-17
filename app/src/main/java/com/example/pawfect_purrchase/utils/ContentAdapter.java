package com.example.pawfect_purrchase.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pawfect_purrchase.models.ProductModel;
import com.example.pawfect_purrchase.R;

import java.util.List;

public class ContentAdapter extends BaseAdapter {
    Context ctx;
    List<ProductModel> productList;
    LayoutInflater inflater;

    public ContentAdapter(Context ctx, List<ProductModel> petFoodList) {
        this.ctx = ctx;
        this.productList = petFoodList;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = inflater.inflate(R.layout.product_list,null);
        }

        ImageView image = view.findViewById(R.id.productImg);
        TextView name = view.findViewById(R.id.productName);
        TextView price = view.findViewById(R.id.productPrice);

        image.setImageResource(productList.get(i).getImage());
        name.setText(productList.get(i).getName());
        price.setText(productList.get(i).getPrice());

        return view;
    }
}
