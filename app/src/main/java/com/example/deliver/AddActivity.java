package com.example.deliver;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddActivity extends AppCompatActivity {

    EditText address,email,id,image,name,tpno;
    Button btnAdd,btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        address=(EditText)findViewById(R.id.addtext);
        email=(EditText)findViewById(R.id.mailtext);
        id=(EditText)findViewById(R.id.idno);
        image=(EditText)findViewById(R.id.img1);
        name=(EditText)findViewById(R.id.nametext);
        tpno=(EditText)findViewById(R.id.tpno);

        btnAdd =(Button)findViewById(R.id.btnAdd);
        btnBack=(Button)findViewById(R.id.btnBack);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
                clearAll();

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void insertData(){
        Map<String,Object> map = new HashMap<>();
        map.put("address",address.getText().toString());
        map.put("email",email.getText().toString());
        map.put("id",id.getText().toString());
        map.put("image",image.getText().toString());
        map.put("name",name.getText().toString());
        map.put("tpno",tpno.getText().toString());

        FirebaseDatabase.getInstance().getReference().child("deliverpersons").push()
                .setValue(map)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(AddActivity.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(Exception e) {
                        Toast.makeText(AddActivity.this,"Error while Insertion",Toast.LENGTH_SHORT).show();

                    }
                });

    }

    private void clearAll(){
        address.setText("");
        email.setText("");
        id.setText("");
        image.setText("");
        name.setText("");
        tpno.setText("");
    }
}