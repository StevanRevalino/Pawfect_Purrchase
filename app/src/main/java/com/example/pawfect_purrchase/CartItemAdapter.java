package com.example.pawfect_purrchase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import java.util.HashMap;
import java.util.List;

public class CartItemAdapter extends ArrayAdapter<ProductModel> {

    private final Context context;
    private final List<ProductModel> productList;
    private TextView totalPriceTextView;

    // Gunakan Map untuk menyimpan kuantitas produk secara lokal
    private HashMap<Integer, Integer> productQuantities = new HashMap<>();

    public CartItemAdapter(@NonNull Context context, @NonNull List<ProductModel> products, TextView totalPriceView) {
        super(context, R.layout.cart_list, products);
        this.context = context;
        this.productList = products;
        this.totalPriceTextView = totalPriceView;

        // Inisialisasi kuantitas untuk setiap produk
        for (int i = 0; i < products.size(); i++) {
            productQuantities.put(i, 1);  // Set default quantity 1
        }
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public ProductModel getItem(int position) {
        return productList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(R.layout.cart_list, parent, false);
        }


        ProductModel product = productList.get(position);

        // Set the product data into the layout views
        ImageView imageView = convertView.findViewById(R.id.cartProductImg);
        TextView nameTextView = convertView.findViewById(R.id.cartProductName);
        TextView priceTextView = convertView.findViewById(R.id.cartProductPrice);
        TextView quantityNum = convertView.findViewById(R.id.quantityNum);

        AppCompatImageView minQuantityButton = convertView.findViewById(R.id.minQuantityButton);
        AppCompatImageView plusQuantityButton = convertView.findViewById(R.id.plusQuantityButton);

        imageView.setImageResource(product.getImage());
        nameTextView.setText(product.getName());
        priceTextView.setText(product.getPrice());

        // Ambil kuantitas lokal untuk produk ini
        int quantity = productQuantities.get(position);
        quantityNum.setText(String.valueOf(quantity));


        // Tambah/kurangi kuantitas
        minQuantityButton.setOnClickListener(v -> {
            int currentQuantity = productQuantities.getOrDefault(position, 1); // Dapatkan kuantitas terbaru
            if (currentQuantity > 1) {
                int newQuantity = currentQuantity - 1;
                productQuantities.put(position, newQuantity);
                quantityNum.setText(String.valueOf(newQuantity)); // Tampilkan kuantitas terbaru
                updateTotalPrice();
            }
        });

        plusQuantityButton.setOnClickListener(v -> {
            int currentQuantity = productQuantities.getOrDefault(position, 1); // Dapatkan kuantitas terbaru
            int newQuantity = currentQuantity + 1;
            productQuantities.put(position, newQuantity);
            quantityNum.setText(String.valueOf(newQuantity)); // Tampilkan kuantitas terbaru
            updateTotalPrice();
        });

        return convertView;
        
    }

    // Update the total price of all items in the cart
    public void updateTotalPrice() {
        double totalPrice = 0;
        for (int i = 0; i < productList.size(); i++) {
            ProductModel product = productList.get(i);
            int quantity = productQuantities.get(i);
            double parsedPrice = Double.parseDouble(product.getPrice().replace("Rp", "").replace(",", ""));
            totalPrice += quantity * parsedPrice;
        }
        totalPriceTextView.setText("Rp " + totalPrice);
    }
}
