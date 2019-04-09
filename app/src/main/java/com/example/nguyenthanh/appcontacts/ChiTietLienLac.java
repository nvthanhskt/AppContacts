package com.example.nguyenthanh.appcontacts;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.nguyenthanh.appcontacts.Model.DanhBa;

public class ChiTietLienLac extends AppCompatActivity {
    int id ;
    TextView txtten,txtsdt,txtnhom;
    DanhBa db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.thongtin_lienhe);
        Intent intent = getIntent();
        id = intent.getIntExtra("id",-1);

        db = new DataBase(this).lay_danhba(id);
       txtten = findViewById(R.id.txt_ten_ct);

       txtsdt = findViewById(R.id.txt_sdt_ct);
       txtnhom = findViewById(R.id.txt_nhom_ct);
       txtten.setText(db.getTen());
       txtsdt.setText(db.getSdt());
       txtnhom.setText(db.getNhom());

        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        findViewById(R.id.cell_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+db.getSdt()));
                if (ActivityCompat.checkSelfPermission(ChiTietLienLac.this, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
                {
                    return;
                }
              startActivity(intent);
            }
        });



    }
}
