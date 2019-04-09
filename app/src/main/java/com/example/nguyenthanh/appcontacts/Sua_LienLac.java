package com.example.nguyenthanh.appcontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.nguyenthanh.appcontacts.DataBase;
import com.example.nguyenthanh.appcontacts.MainActivity;
import com.example.nguyenthanh.appcontacts.Model.DanhBa;
import com.example.nguyenthanh.appcontacts.R;

import java.util.ArrayList;

public class Sua_LienLac extends AppCompatActivity {
    private  int id;
    EditText ten, sdt;
    CheckBox yeuthich;
    Spinner spinner;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sua_lienlac);
        ten = findViewById(R.id.ed_tenll_sua);
        sdt = findViewById(R.id.ed_sdt_sua);
        yeuthich = findViewById(R.id.cb_yeuthich_sua);
        spinner = findViewById(R.id.spinner_sua);
        final Intent intent = getIntent();

        if(intent!=null){

            id = intent.getIntExtra("id",-1);
           DanhBa db = new DanhBa( ) ;
            db =   new DataBase(this).lay_danhba(id);
           ten.setText(db.getTen());
           sdt.setText(db.getSdt());
           yeuthich.setChecked(db.isYeuthich());
            ArrayList<String> list = new ArrayList<>();
            list.add(db.getNhom());
            for(String s : new DataBase(this).lay_nhom()){
                if(db.getNhom().compareTo(s)!=0)
                    list.add(s);
            }
            list.add("");
            ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
            arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(arrayAdapter);
        }

        findViewById(R.id.btluulienlac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            sua_lienlac(ten.getText().toString(),sdt.getText().toString(),yeuthich.isChecked(),spinner.getSelectedItem().toString());
            }
        });

        findViewById(R.id.quaylaisua).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    private  void sua_lienlac(String ten,String sdt, Boolean yeuthich,String nhom){
        if(ten.trim().compareTo("")==0){
            findViewById(R.id.ed_tenll_sua).setBackgroundResource(R.drawable.boder);
        }
        if(sdt.trim().compareTo("")==0){
            findViewById(R.id.ed_sdt_sua).setBackgroundResource(R.drawable.boder);
        }
        if(sdt.trim().compareTo("")!=0&&ten.trim().compareTo("")!=0){
            new DataBase(this).sua_lienlac(id,ten,sdt,yeuthich,nhom);
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }


    }
}