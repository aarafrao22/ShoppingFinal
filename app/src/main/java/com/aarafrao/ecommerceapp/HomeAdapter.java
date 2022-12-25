package com.aarafrao.ecommerceapp;

import android.content.Context;

import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder> {
    Context context;
    ArrayList<ProductModel> list;
    String[] item = {"S", "M", "L"};
    SharedPreferences sharedPreferences;
    ArrayAdapter<String> adapteritem;

    String MyPREFERENCES = "MyPrefs";
    String WISHLIST = "WISHLIST";

    public HomeAdapter(Context context, ArrayList<ProductModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        ProductModel pd = list.get(position);
        holder.productname.setText(pd.getProduct_name());
        holder.productdes.setText(pd.getProduct_desc());
        holder.productprice.setText(String.valueOf(pd.getPrice()) + " GBP");
        Picasso.get().load(pd.getUrl()).into(holder.productimg);
        holder.addtocard.setId(pd.getProduct_id());

        holder.imgAddToFav.setOnClickListener(v -> {
            Toast.makeText(context, "Added to Wishlist", Toast.LENGTH_SHORT).show();
            holder.imgAddToFav.setImageResource(R.drawable.heart_fill);

            sharedPreferences = context.getSharedPreferences(WISHLIST, Context.MODE_PRIVATE);

            ProductModel pd1 = list.get(position);
            ArrayList<String> store = new ArrayList<>();
            store.add(String.valueOf(pd1.getProduct_id()));
            store.add(String.valueOf(pd1.getPrice()));
            store.add(String.valueOf(pd1.getProduct_desc()));
            store.add(String.valueOf(pd1.getUrl()));
            store.add(String.valueOf(pd1.getProduct_name()));

            SharedPreferences.Editor editor = sharedPreferences.edit();
            String value = sharedPreferences.getString(String.valueOf(pd1.getProduct_id()), "");
            System.out.println("value");
            System.out.println(value);

            if (value.isEmpty()) {

//                   // add insert data
                System.out.println("new added item");
                store.add(String.valueOf(1));
                Gson gson = new Gson();
                String json = gson.toJson(store);
                editor.putString(String.valueOf(pd1.getProduct_id()), json);
                editor.apply();

            } else {
                Type type = new TypeToken<List<String>>() {
                }.getType();
                System.out.println("Increase item");
                Gson gson = new Gson();
                List<String> arrPackageData = gson.fromJson(value, type);
                store.add(String.valueOf(Integer.parseInt(arrPackageData.get(4)) + 1));
                String json = gson.toJson(store);
                editor.putString(String.valueOf(pd1.getProduct_id()), json);
                editor.apply();
                System.out.println(arrPackageData);
            }

        });

        holder.addtocard.setOnClickListener(view -> {

            sharedPreferences =
                    context.getSharedPreferences(MyPREFERENCES,
                            Context.MODE_PRIVATE);

            int id = view.getId();
            System.out.println(String.valueOf(id));
            ProductModel pd1 = list.get(id - 1);
            ArrayList<String> store = new ArrayList<>();
            store.add(String.valueOf(pd1.getPrice()));
            store.add(String.valueOf(pd1.getProduct_desc()));
            store.add(String.valueOf(pd1.getUrl()));
            store.add(String.valueOf(pd1.getProduct_name()));

            SharedPreferences.Editor editor = sharedPreferences.edit();
            String value = sharedPreferences.getString(String.valueOf(pd1.getProduct_id()), "");
            System.out.println("value");
            System.out.println(value);

            if (value.isEmpty()) {

//                   // add insert data
                System.out.println("new added item");
                store.add(String.valueOf(1));
                Gson gson = new Gson();
                String json = gson.toJson(store);
                editor.putString(String.valueOf(pd1.getProduct_id()), json);
                editor.apply();

            } else {
                Type type = new TypeToken<List<String>>() {
                }.getType();
                System.out.println("Increase item");
                Gson gson = new Gson();
                List<String> arrPackageData = gson.fromJson(value, type);
                store.add(String.valueOf(Integer.parseInt(arrPackageData.get(4)) + 1));
                String json = gson.toJson(store);
                editor.putString(String.valueOf(pd1.getProduct_id()), json);
                editor.apply();
                System.out.println(arrPackageData);
            }

            Toast.makeText(context.getApplicationContext(), pd1.getProduct_name() + " added to the bag", Toast.LENGTH_SHORT).show();

        });


        adapteritem = new ArrayAdapter<>(context.getApplicationContext(), R.layout.item, item);
        holder.autoCompleteTextView.setAdapter(adapteritem);

        holder.autoCompleteTextView.setOnItemClickListener((adapterView, view, i, l) -> {
            String item = adapterView.getItemAtPosition(i).toString();
            System.out.println(String.valueOf(item));
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productname, productdes, productprice, selectbox;
        ImageView productimg, addtocard, imgAddToFav;
        AutoCompleteTextView autoCompleteTextView;

        public MyViewHolder(@NonNull View itemview) {
            super(itemview);
            imgAddToFav = itemview.findViewById(R.id.addTOFav);
            productname = itemview.findViewById(R.id.productName);
            productimg = itemview.findViewById(R.id.productImg);
            productdes = itemview.findViewById(R.id.productDes);
            productprice = itemview.findViewById(R.id.productPrice);
            addtocard = itemview.findViewById(R.id.addToCart);
            autoCompleteTextView = itemview.findViewById(R.id.auto_text);
        }
    }
}
