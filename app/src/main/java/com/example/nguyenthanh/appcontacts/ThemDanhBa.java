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

public class ThemDanhBa extends AppCompatActivity {
    Spinner spinner;
    EditText ten,sdt;
    CheckBox yeuthich;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_lienlac);
        findViewById(R.id.quaylai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        ArrayList<String> list = new ArrayList<>();
        list.add("");
        if(new DataBase(this).lay_nhom()!=null)
        list.addAll(new DataBase(this).lay_nhom());
        spinner = findViewById(R.id.spinner);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        ten = findViewById(R.id.ed_tenll);
        sdt = findViewById(R.id.ed_sdt);
        yeuthich = findViewById(R.id.cb_yeuthich);

        findViewById(R.id.btthemlienlac).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              them_lienlac(ten.getText().toString(),sdt.getText().toString(),yeuthich.isChecked(),spinner.getSelectedItem().toString());

            }
        });
    }
    private  void them_lienlac(String ten,String sdt, Boolean yeuthich,String nhom){
        if(ten.trim().compareTo("")==0){
            findViewById(R.id.ed_tenll).setBackgroundResource(R.drawable.boder);
        }
        if(sdt.trim().compareTo("")==0){
            findViewById(R.id.ed_sdt).setBackgroundResource(R.drawable.boder);
        }
        if(sdt.trim().compareTo("")!=0&&ten.trim().compareTo("")!=0){
            if(new DataBase(this).kiemTraTen(ten)==false)
            {
                new DataBase(this).them_danhba(new DanhBa(ten,sdt,nhom,yeuthich));
                Toast.makeText(ThemDanhBa.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this,MainActivity.class);
                startActivity(intent);

            }else {
                this.ten.setText("");
                Toast.makeText(this, "Tên liên lạc đã có", Toast.LENGTH_SHORT).show();
            }

        }


    }
}
