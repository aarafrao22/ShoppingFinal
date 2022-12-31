package com.aarafrao.ecommerceapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aarafrao.ecommerceapp.R;


public class ProfileFragment extends Fragment {
    ConstraintLayout txtItem;
    private TextView txtAcc, txtNameMain;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txtItem = view.findViewById(R.id.txtItem);


        txtAcc = view.findViewById(R.id.txtAcc);
        txtNameMain = view.findViewById(R.id.txtName);


        SharedPreferences preferences = getActivity().getSharedPreferences("Details", Context.MODE_PRIVATE);
        String name = preferences.getString("Name", " User");
        txtNameMain.setText(name);
        txtAcc.setText(name);

        txtItem.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_navigation_profile_to_detailProfileFrag);
        });
        return view;
    }


}