package com.aarafrao.ecommerceapp.ui;

import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aarafrao.ecommerceapp.R;


public class ProfileFragment extends Fragment {
    ConstraintLayout txtItem;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        txtItem = view.findViewById(R.id.txtItem);
        txtItem.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_navigation_profile_to_detailProfileFrag);
        });
        return view;
    }


}