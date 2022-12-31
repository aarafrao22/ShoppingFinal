package com.aarafrao.ecommerceapp;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    Context context;
    ArrayList<ProductModel> list;
    Intent refresh;

    SharedPreferences sharedPreferences;
    String MyPREFERENCES = "MyPrefs";

    public CartAdapter(Context context, ArrayList<ProductModel> list, Intent refresh) {
        this.context = context;
        this.refresh = refresh;
        this.list = list;
    }

    @NonNull
    @Override
    public CartAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cart_item_layout, parent, false);
        return new CartAdapter.MyViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.MyViewHolder holder, int position) {
        sharedPreferences = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        ProductModel pd = list.get(position);

        holder.productname.setText(pd.getProduct_name());
        holder.productdes.setText(pd.getProduct_desc());
        holder.productprice.setText(String.valueOf(pd.getPrice()));
        System.out.println(pd.quantity);

        if (holder.selectbox != null) {
            holder.selectbox.setText(String.valueOf(pd.getQuantity()) + " | S");
        }

        Picasso.get().load(pd.getUrl()).into(holder.productimg);
        holder.btnDelete.setOnClickListener(n -> {

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(String.valueOf(pd.getProduct_id()));
            editor.apply();

            Toast.makeText(context.getApplicationContext(), "ItemDeleted", Toast.LENGTH_SHORT).show();
            String value = sharedPreferences.getString(String.valueOf(pd.getProduct_id()), "");
            Log.d(TAG, "onBindViewHolder: " + value);


        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productname, productdes, productprice, selectbox, productquanty;
        ImageView productimg, addtocard;
        Button btnDelete;
        AutoCompleteTextView autoCompleteTextView;

        public MyViewHolder(@NonNull View itemview) {
            super(itemview);
            productname = itemview.findViewById(R.id.productName);
            btnDelete = itemview.findViewById(R.id.btnDelete);
            productimg = itemview.findViewById(R.id.productImg);
            productdes = itemview.findViewById(R.id.productDes);
            productprice = itemview.findViewById(R.id.productPrice);
            selectbox = itemview.findViewById(R.id.productquanity);


        }
    }
}
