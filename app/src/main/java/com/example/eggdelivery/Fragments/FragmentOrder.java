package com.example.eggdelivery.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.eggdelivery.Models.Orders;
import com.example.eggdelivery.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FragmentOrder extends Fragment {
    private static final String TAG = "Order Fragment";
    private EditText etname, etemail, etnumber, etitem;
    private Button btn5;

    DatabaseReference order;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.orderfrag, container, false);
        etname = view.findViewById(R.id.edtname);
        etemail = view.findViewById(R.id.edtemail);
        etnumber = view.findViewById(R.id.edtnum);
        etitem = view.findViewById(R.id.qtty);
        btn5 = view.findViewById(R.id.btn5);

        order = FirebaseDatabase.getInstance().getReference().child("Orders");

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertOrder();
            }
        });

        return view;
    }

    private void insertOrder() {

        String name = etname.getText().toString();
        String email = etemail.getText().toString();
        String number = etnumber.getText().toString();
        String item = etitem.getText().toString();

        if (name.isEmpty()) {
            etname.setError("Fill Name Field");
            etname.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            etemail.setError("Fill Email Field");
            etemail.requestFocus();
            return;
        }

        if (number.isEmpty()) {
            etnumber.setError("Fill Telephone Field");
            etnumber.requestFocus();
            return;
        }

        if (item.isEmpty()) {
            etitem.setError("Fill Item Field");
            etitem.requestFocus();
            return;
        }

        Orders orders = new Orders(name, email, number, item);

        order.push().setValue(orders).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(getActivity(), "Successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getActivity(), "Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


}
