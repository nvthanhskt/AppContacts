package com.example.nguyenthanh.appcontacts;

import android.content.Context;

import com.example.nguyenthanh.appcontacts.Model.TaiKhoan;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonNull;

import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public class API {

    public static Retrofit initRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.3.3:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;

    }

    public interface ApiInterface {
        @FormUrlEncoded
        @POST("ContactsAPI/dangky.php")
        public Call<ResponseBody> dangky(@Field("tk") String taikhoan, @Field("ht") String ten, @Field("ns") String ngaysinh,@Field("mk") String mk);

        @FormUrlEncoded
        @POST("ContactsAPI/dangnhap.php")
        public Call<TaiKhoan> dangnhap(@Field("tk") String taikhoan, @Field("mk") String mk);

        @FormUrlEncoded
        @POST("ContactsAPI/backup.php")
        public Call<RequestBody> backup(@Field("tk") String taikhoan, @Field("ten") String ten, @Field("sdt") String sdt);

        @FormUrlEncoded
        @POST("ContactsAPI/dongbo.php")
        public Call<TaiKhoan> dongbo(@Field("taikhoan") String taikhoan);

    }
}
