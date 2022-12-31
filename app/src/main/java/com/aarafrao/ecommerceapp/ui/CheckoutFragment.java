package com.aarafrao.ecommerceapp.ui;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.aarafrao.ecommerceapp.R;
import com.google.android.material.textfield.TextInputEditText;

public class CheckoutFragment extends Fragment {
    private TextInputEditText txtEmail, txtNameMain, txtPass, txtPhone, edHouse, edPost, edTown, edStreet;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_checkout, container, false);

        Button btnContinue = view.findViewById(R.id.btnContinue);


        txtNameMain = view.findViewById(R.id.edName);
        txtEmail = view.findViewById(R.id.edEmail);
        txtPhone = view.findViewById(R.id.edNameOnCard);
        edHouse = view.findViewById(R.id.edHouse);
        edStreet = view.findViewById(R.id.edStreet);
        edTown = view.findViewById(R.id.edTownCity);
        edPost = view.findViewById(R.id.edPostCode);


        SharedPreferences preferences = getActivity().getSharedPreferences("Details", Context.MODE_PRIVATE);
        String name = preferences.getString("Name", "");
        String email = preferences.getString("Email", "");
        String phone = preferences.getString("phone", "");
        txtNameMain.setText(name);
        txtEmail.setText(email);
        txtPhone.setText(phone);


        btnContinue.setOnClickListener(v -> {

            if (!edHouse.getText().toString().equals("")
                    && !edStreet.getText().toString().equals("")
                    && !edPost.getText().toString().equals("")
                    && !edTown.getText().toString().equals("")
                    && !txtPhone.getText().toString().equals("")
            ) {
                Navigation.findNavController(v).navigate(R.id.action_checkoutFragment_to_paymentFragment);
            } else {
                Toast.makeText(getContext(), "Enter Mandatory Fields to Continue", Toast.LENGTH_SHORT).show();
            }

        });


        return view;
    }
}