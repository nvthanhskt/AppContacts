package com.example.nguyenthanh.appcontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Sua_Nhom extends AppCompatActivity {
    EditText edTen;
    String ten = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua_nhom);
        edTen = findViewById(R.id.ed_tennhom_sua);
        final Intent intent = getIntent();

        if(intent!=null){
            ten = intent.getStringExtra("tennhom");
            edTen.setText(ten);
        }
        findViewById(R.id.quaylaisuanhom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.btsuanhom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edTen.getText().toString().compareTo("")==0){
                    Toast.makeText(Sua_Nhom.this, "Không bỏ trống", Toast.LENGTH_SHORT).show();
                    edTen.setText("");
                }else {
                    new DataBase(Sua_Nhom.this).sua_nhom(edTen.getText().toString(),ten);
                    Intent intent1 = new Intent(Sua_Nhom.this,MainActivity.class);
                    startActivity(intent1);

                }
            }
        });

    }
}
