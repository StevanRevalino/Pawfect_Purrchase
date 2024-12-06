package com.example.pawfect_purrchase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

public class ContentAdapter extends BaseAdapter {
    Context ctx;
    List<PetFoodModel> petFoodList;
    LayoutInflater inflater;

    public ContentAdapter(Context ctx, List<PetFoodModel> petFoodList) {
        this.ctx = ctx;
        this.petFoodList = petFoodList;
        inflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return petFoodList.size();
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

        }

        return view;
    }
}
