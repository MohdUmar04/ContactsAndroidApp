package com.example.recyclerviewexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.recyclerviewexample.R.id;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
List<ContactModel> arr = new ArrayList<>();
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView viewRecycler = findViewById(R.id.ViewRecycler);

        viewRecycler.setLayoutManager(new LinearLayoutManager(this));


        arr.add(new ContactModel(R.drawable.rajanpic,"Sergio","8965412354"));
        arr.add(new ContactModel(R.drawable.rajanpic,"Ricado","8965412354"));
        arr.add(new ContactModel(R.drawable.rajanpic,"Lewis","8965412354"));
        arr.add(new ContactModel(R.drawable.rajanpic,"Max","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Niki","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Michael","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Mike","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Alex","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Alson","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Charles","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Carlos","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Ocon","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Robert","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Grant","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Vin","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Gasley","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Torro","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Alpine","8965412354"));
        arr.add(new ContactModel(R.drawable.user,"Stakes","8965412354"));

        RecyclerContactAdapter adapter = new RecyclerContactAdapter(this, arr);

        viewRecycler.setAdapter(adapter);
        MaterialButton openDialoge ;
        openDialoge = findViewById(id.OpenSaveContact);

        openDialoge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(MainActivity.this);
                dialog.setContentView(R.layout.add_update_contact_layout);


                EditText newName, newNumber;
                newName = dialog.findViewById(id.NewName);
                newNumber = dialog.findViewById(id.NewContactNo);
                MaterialButton save = dialog.findViewById(id.SaveNewContact);

                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = "",num = "";
                        boolean flag = true;
                        if(!newName.getText().toString().equals(""))
                            name = newName.getText().toString();
                        else {
                            flag = false;
                            Toast.makeText(MainActivity.this, "Invalid Name", Toast.LENGTH_SHORT).show();
                        }
                        if(!newNumber.getText().toString().equals(""))
                            num = newNumber.getText().toString();
                        else {
                            flag = false;
                            Toast.makeText(MainActivity.this, "Invalid Number", Toast.LENGTH_SHORT).show();
                        }

                        if(flag){
                            arr.add(new ContactModel(name, num));

                            adapter.notifyItemInserted(arr.size() - 1);
                            viewRecycler.scrollToPosition(arr.size() - 1);

                            dialog.dismiss();
                        }



                    }
                });
                dialog.show();

            }
        });

    }
}