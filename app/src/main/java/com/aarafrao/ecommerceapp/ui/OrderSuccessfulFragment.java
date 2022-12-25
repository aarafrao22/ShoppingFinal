package com.aarafrao.ecommerceapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aarafrao.ecommerceapp.R;


public class OrderSuccessfulFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_order_successful, container, false);

        Button continue_checkout = view.findViewById(R.id.continue_checkout1);

        continue_checkout.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_orderSuccessfulFragment_to_navigation_home);

        });
        return view;
    }
}