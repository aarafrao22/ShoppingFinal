package com.aarafrao.ecommerceapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aarafrao.ecommerceapp.R;

public class CheckoutFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        Button btnContinue = view.findViewById(R.id.btnContinue);


        btnContinue.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_checkoutFragment_to_paymentFragment);

        });


        return view;
    }
}