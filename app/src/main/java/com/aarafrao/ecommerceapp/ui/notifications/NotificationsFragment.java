package com.aarafrao.ecommerceapp.ui.notifications;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aarafrao.ecommerceapp.MainActivity3;
import com.aarafrao.ecommerceapp.OnUpdateListener;
import com.aarafrao.ecommerceapp.R;
import com.aarafrao.ecommerceapp.databinding.FragmentNotificationsBinding;
import com.aarafrao.ecommerceapp.myadaptercard;
import com.aarafrao.ecommerceapp.ProductModel;
import com.aarafrao.ecommerceapp.ui.home.HomeFragment;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class NotificationsFragment extends Fragment /*implements OnUpdateListener*/ {

    private FragmentNotificationsBinding binding;
    RecyclerView recyclerView;
    Intent refresh;
    myadaptercard myadpater;
    private OnUpdateListener listener;
    ArrayList<ProductModel> list;
    SharedPreferences sharedPreferences;
    String MyPREFERENCES = "MyPrefs";
    TextView totalamount;
    TextView countitem;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


//        listener = (OnUpdateListener) getActivity();

        recyclerView = root.findViewById(R.id.productlistcard);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        totalamount = root.findViewById(R.id.totalamount);
        countitem = root.findViewById(R.id.itemtext);

        refresh = new Intent(getContext(), HomeFragment.class);
        list = new ArrayList<>();
        myadpater = new myadaptercard(getContext(), list, refresh);

        recyclerView.setAdapter(myadpater);

        sharedPreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        Map<String, ?> keys = sharedPreferences.getAll();
        int total = 0;
        int count = 0;
        for (Map.Entry<String, ?> entry : keys.entrySet()) {
            Log.d("map values", entry.getKey() + ": " +
                    entry.getValue().toString());

            Type type = new TypeToken<List<String>>() {
            }.getType();
            Gson gson = new Gson();
            List<String> arrPackageData = gson.fromJson(entry.getValue().toString(), type);
            ProductModel pd = new ProductModel();
            pd.setPrice(Integer.parseInt(arrPackageData.get(0)));
            pd.setProduct_desc(arrPackageData.get(1));
            pd.setProduct_name(arrPackageData.get(3));
            pd.setUrl(arrPackageData.get(2));
            pd.setQuantity(Integer.parseInt(arrPackageData.get(4)));
            list.add(pd);
            total += Integer.parseInt(arrPackageData.get(0)) * Integer.parseInt(arrPackageData.get(4));
            count += 1;
        }
        countitem.setText(String.valueOf(count) + " item");
        myadpater.notifyDataSetChanged();
        total += 3.95;
        totalamount.setText(String.valueOf(total));
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    @Override
//    public void onUpdate(ArrayList<ProductModel> text) {
//        text = new ArrayList<>();
//        myadpater = new myadaptercard(getContext(), text, refresh);
//        recyclerView.setAdapter(myadpater);
//        myadpater.notifyDataSetChanged();
//    }
}