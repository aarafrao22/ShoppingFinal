package com.aarafrao.ecommerceapp.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.aarafrao.ecommerceapp.R;


public class DetailProfileFrag extends Fragment {
    Button btnCancel, btnUpdate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_profile, container, false);

        btnCancel = view.findViewById(R.id.btnCancel);
        btnUpdate = view.findViewById(R.id.btn_sign_up);

        btnCancel.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_detailProfileFrag_to_navigation_profile);
        });

        btnUpdate.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_detailProfileFrag_to_navigation_profile);
        });

        return view;
    }
}