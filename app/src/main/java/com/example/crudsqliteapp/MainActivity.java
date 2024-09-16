package com.example.crudsqliteapp;

import android.app.AlertDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtTxtName, edtTxtContact, edtTxtDof;
    Button btnCreate, btnUpdate, btnView, btnDelete;
    DBHelper DB ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtTxtName = findViewById(R.id.name);
        edtTxtContact = findViewById(R.id.contact);
        edtTxtDof = findViewById(R.id.dof);

        btnCreate = findViewById(R.id.btnCreate);
        btnDelete = findViewById(R.id.btnDelete);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnView = findViewById(R.id.btnViewData);

        DB = new DBHelper(this);

        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtTxtName.getText().toString();
                String contact = edtTxtContact.getText().toString();
                String dof = edtTxtDof.getText().toString();

                Boolean isDataInserted = DB.inserUserDetails(name, contact, dof);
                if(isDataInserted){
                    Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtTxtName.getText().toString();
                String contact = edtTxtContact.getText().toString();
                String dof = edtTxtDof.getText().toString();

                Boolean isDataUpdated = DB.updateUserDetails(name, contact, dof);
                if(isDataUpdated){
                    Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data not Updated", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtTxtName.getText().toString();


                Boolean isDataDeleted = DB.deleteUserDetails(name);
                if(isDataDeleted){
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getData();
                if(res.getCount() == 0){
                    Toast.makeText(MainActivity.this, "No Data", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Name: "+ res.getString(0)+"\n");
                    buffer.append("Contact: "+ res.getString(1)+"\n");
                    buffer.append("D of B: "+ res.getString(2)+"\n\n");

                }
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Data");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });



    }



}