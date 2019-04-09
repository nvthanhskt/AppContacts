package com.example.nguyenthanh.appcontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangKy extends AppCompatActivity {
    EditText sdt, ten, ngays ,mk, nlmk;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangky);




        sdt = findViewById(R.id.ed_sdt_dangky);
        ten = findViewById(R.id.ed_ten_dangky);
        ngays = findViewById(R.id.ed_ngays_dangky);
        mk = findViewById(R.id.ed_mk_dangky);
        nlmk = findViewById(R.id.ed_mknl_dangky);
        findViewById(R.id.btn_cancel_dky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangKy.this,MainActivity.class));
            }
        });

        findViewById(R.id.btn_dangky).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Boolean c = true;


                if(ten.getText().toString().trim().compareTo("")==0 || sdt.getText().toString().trim().compareTo("")==0 || ngays.getText().toString().trim().compareTo("")==0){
                    c=false;
                    Toast.makeText(DangKy.this, "Không được bỏ trống", Toast.LENGTH_SHORT).show();
                }
                if(mk.getText().toString().compareTo(nlmk.getText().toString())!=0){
                    c=false;
                    Toast.makeText(DangKy.this, "Mật khẩu không khớp", Toast.LENGTH_LONG).show();
                }

                if(c==true){
                    themtaikhoan(sdt.getText().toString(),ten.getText().toString(), ngays.getText().toString(),mk.getText().toString());
                }else {
                    Toast.makeText(DangKy.this, "sai", Toast.LENGTH_SHORT).show();
                }


            }
        });

    }

    private void themtaikhoan(String sdt,String ten, String ngaysinh,String mk){
        API.initRetrofit().create(API.ApiInterface.class)
                .dangky(sdt,ten,ngaysinh,mk)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {

                            if(response.body().string().trim().compareTo("true")==0){

                                Toast.makeText(DangKy.this, "Đăng ký thành công:"+response.body().string(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(DangKy.this,MainActivity.class));
                            }
                            else  Toast.makeText(DangKy.this, "Số điện thoại đã tồn tại", Toast.LENGTH_LONG).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(DangKy.this, "Đăng ký không thành công", Toast.LENGTH_SHORT).show();
                    }
                });

    }
}
