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


public class DetailProfileFrag extends Fragment {
    Button btnCancel, btnUpdate;
    private TextInputEditText txtEmail, txtNameMain, txtPass, txtPhone;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail_profile, container, false);

        btnCancel = view.findViewById(R.id.btnCancel);
        btnUpdate = view.findViewById(R.id.btnContinue);

        txtNameMain = view.findViewById(R.id.edSirName);
        txtEmail = view.findViewById(R.id.edName);
        txtPass = view.findViewById(R.id.edEmail);
        txtPhone = view.findViewById(R.id.edHouse);


        SharedPreferences preferences = getActivity().getSharedPreferences("Details", Context.MODE_PRIVATE);
        String name = preferences.getString("Name", "");
        String email = preferences.getString("Email", "");
        String pass = preferences.getString("pass", "");
        String phone = preferences.getString("phone", "");
        txtNameMain.setText(name);
        txtEmail.setText(email);
        txtPass.setText(pass);
        txtPhone.setText(phone);


        btnCancel.setOnClickListener(v -> {
            Navigation.findNavController(v).navigate(R.id.action_detailProfileFrag_to_navigation_profile);
        });

        btnUpdate.setOnClickListener(v -> {
            if (txtPhone.getText() != null &&
                    txtEmail.getText().toString() != null &&
                    txtPass.getText().toString() != null &&
                    txtNameMain.getText().toString() != null) {
                SharedPreferences.Editor myEdit = preferences.edit();
                myEdit.putString("phone", txtPhone.getText().toString());
                myEdit.putString("Email", txtEmail.getText().toString());
                myEdit.putString("Name", txtNameMain.getText().toString());
                myEdit.putString("pass", txtPass.getText().toString());
                myEdit.apply();

                Navigation.findNavController(v).navigate(R.id.action_detailProfileFrag_to_navigation_profile);
            } else {
                Toast.makeText(getContext(), "Fill All Fields", Toast.LENGTH_SHORT).show();
            }

        });

        return view;
    }
}