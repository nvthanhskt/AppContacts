package com.example.nguyenthanh.appcontacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguyenthanh.appcontacts.Model.TaiKhoan;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DangNhap extends AppCompatActivity {
    EditText sdt, mk;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangnhap);

        mk = findViewById(R.id.ed_mk_dangnhap);
        sdt = findViewById(R.id.ed_sdt_dangnhap);

        findViewById(R.id.dangky_dn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhap.this,DangKy.class));
            }
        });
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mk.getText().toString().compareTo("")>0 && sdt.getText().toString().compareTo("")>0){
                    API.initRetrofit().create(API.ApiInterface.class)
                            .dangnhap(sdt.getText().toString(),mk.getText().toString())
                            .enqueue(new Callback<TaiKhoan>() {
                                @Override
                                public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {
                                    try {
                                        if(response.body().getStatus()==true){
                                            Toast.makeText(DangNhap.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                                            new DataBase(DangNhap.this).them_taikhoan(response.body().getSdt(),response.body().getHoten());
                                            startActivity(new Intent(DangNhap.this,MainActivity.class));
                                        }else {

                                            Toast.makeText(DangNhap.this, "Xem lại mật khẩu hoặc số điện thoại", Toast.LENGTH_SHORT).show();

                                        }
                                    } catch (Exception e) {
                                        Toast.makeText(DangNhap.this, "Lỗi server", Toast.LENGTH_SHORT).show();

                                    }
                                }

                                @Override
                                public void onFailure(Call<TaiKhoan> call, Throwable t) {

                                    Toast.makeText(DangNhap.this, "Lỗi server", Toast.LENGTH_SHORT).show();

                                }
                            });
                }

            }
        });


        findViewById(R.id.btn_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DangNhap.this,MainActivity.class));
            }
        });
    }
}
