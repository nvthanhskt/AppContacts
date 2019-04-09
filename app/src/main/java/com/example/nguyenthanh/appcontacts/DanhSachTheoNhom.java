package com.example.nguyenthanh.appcontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.nguyenthanh.appcontacts.Adapter.Adapter_DanhBa;
import com.example.nguyenthanh.appcontacts.Adapter.Adapter_LienLacTheoNhom;
import com.example.nguyenthanh.appcontacts.Model.DanhBa;

import java.util.ArrayList;

public class DanhSachTheoNhom extends AppCompatActivity {
    ArrayList<DanhBa> list ;
    RecyclerView recyclerView;
    Adapter_LienLacTheoNhom adapter_danhBa;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nhomlienhe);
       TextView ten = findViewById(R.id.txttennhom_nhom);
        recyclerView = findViewById(R.id.list_nhomlienhe);
        findViewById(R.id.back_nhomlienlac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        list = new ArrayList<>();

        Intent intent = getIntent();
        if(intent!=null){
            String nhom = intent.getStringExtra("tennhom");
            ten.setText(nhom);
            list = new DataBase(this).lay_danhbatheonhom(nhom);
            adapter_danhBa = new Adapter_LienLacTheoNhom(this,list);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(adapter_danhBa);

        }

    }
}
