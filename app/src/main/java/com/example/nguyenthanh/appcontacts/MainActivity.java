package com.example.nguyenthanh.appcontacts;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Display;
import android.view.Menu;
import android.view.Surface;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nguyenthanh.appcontacts.Adapter.Adapter_DanhBa;
import com.example.nguyenthanh.appcontacts.Adapter.Adapter_DanhBa_land;
import com.example.nguyenthanh.appcontacts.Fragment.HomeFragment;
import com.example.nguyenthanh.appcontacts.Fragment.NhomFragment;
import com.example.nguyenthanh.appcontacts.Model.DanhBa;
import com.example.nguyenthanh.appcontacts.Model.TaiKhoan;
import com.google.gson.JsonArray;
import com.google.gson.JsonNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView text;
    DataBase dataBase;
    TaiKhoan tk;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBase = new DataBase(this);
        Display display = ((WindowManager)getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        switch (display.getRotation()) {
            case Surface.ROTATION_0:
                // màn hình đứng
                manhinhdoc();

                break;

            case Surface.ROTATION_90:
              // Màn ngang 90 độ
               final List<DanhBa> list = dataBase.lay_danhba();
                recyclerView = findViewById(R.id.list_lienlac_land);

                 setDanhBaAdapterLand(list);

                SearchView searchView = findViewById(R.id.search_bar_land);
                searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String s) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String s) {
                        ArrayList<DanhBa> newList = new ArrayList<>();
                        for (DanhBa db: list) {
                            if(db.getTen().toLowerCase().contains(s.toLowerCase())==true){
                                newList.add(db);

                            }
                        }
                        setDanhBaAdapterLand(newList);
                        return false;
                    }
                });
                break;

        }
        tk = dataBase.get_taikhoan();



    }
    private Boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if(ni != null && ni.isConnected()) {
            return true;
        }
        return false;
    }
    private void setDanhBaAdapterLand(List<DanhBa> list){
        Adapter_DanhBa_land adapter_danhBa = new Adapter_DanhBa_land(this,list);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter_danhBa);
    }
    private void manhinhdoc(){


        // set tab pager
        nextFragment(R.id.frame_main, new HomeFragment());
        // --------------------

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        View headerView = navigationView.getHeaderView(0);
        ImageView img = headerView.findViewById(R.id.nav_img);
        TextView sdt = (TextView) headerView.findViewById(R.id.header_sdt);
        TextView ten = (TextView) headerView.findViewById(R.id.header_ten);

        tk = new DataBase(this).get_taikhoan();

        Menu menu = navigationView.getMenu();
        MenuItem item = menu.findItem(R.id.nav_dangxuat);



        if(tk.getStatus()==true){
            img.setVisibility(View.VISIBLE);
            ten.setText(tk.getHoten());
            sdt.setText(tk.getSdt());
            item.setTitle("Đăng xuất");
        }else {
            ten.setText("");
            sdt.setText("");
            item.setTitle("Đăng nhập");
        }

    }

    @Override
    public void onBackPressed() {
        try {
            DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            if (drawer.isDrawerOpen(GravityCompat.START)) {
                drawer.closeDrawer(GravityCompat.START);
            } else {
                super.onBackPressed();
            }
        }catch (Exception e){

        }

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_backup) {
            BackUp();

        }else if (id == R.id.nav_dongbo) {
            DongBo();
        }else if (id == R.id.nav_share) {
            Intent sendIntent = new Intent();
            sendIntent.setAction(Intent.ACTION_SEND);
            sendIntent.putExtra(Intent.EXTRA_TEXT, "This is my text to send.");
            sendIntent.setType("text/plain");
            startActivity(sendIntent);
        }

        else if (id == R.id.nav_dangxuat) {
            if(tk.getStatus()==false){
                Intent intent = new Intent(this,DangNhap.class);
                startActivity(intent);
            }else {

                    new AlertDialog.Builder(this)
                            .setMessage("Bạn chắc chắn muốn đăng xuất?")
                            .setTitle("Thông báo")
                            .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    new DataBase(MainActivity.this).dangxuat();
                                    startActivity(new Intent(MainActivity.this,MainActivity.class));

                                }
                            })
                            .setPositiveButton("Cancel",null)
                            .show();

            }

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    // set viewpager
    public void nextFragment(int id , Fragment fragment){
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(id,fragment);
        transaction.commit();
    }
    private void DongBo(){
        final ArrayList<DanhBa> listdb = new ArrayList<>();
        if(tk.getStatus()==true){
            API.initRetrofit().create(API.ApiInterface.class)
                    .dongbo(tk.getSdt())
                    .enqueue(new Callback<TaiKhoan>() {
                        @Override
                        public void onResponse(Call<TaiKhoan> call, Response<TaiKhoan> response) {
                            try {
                                listdb.addAll(response.body().getDanhBas());
                                for(DanhBa db : listdb){
                                    dataBase.them_danhba(db);
                                }
                                Toast.makeText(MainActivity.this, "Đồng bộ thành công", Toast.LENGTH_SHORT).show();
                            }catch (Exception e){
                                Toast.makeText(MainActivity.this, "Dữ liệu rỗng", Toast.LENGTH_SHORT).show();
                            }

                            startActivity(new Intent(MainActivity.this,MainActivity.class));


                        }

                        @Override
                        public void onFailure(Call<TaiKhoan> call, Throwable t) {

                        }
                    });
        }else {
            startActivity(new Intent(MainActivity.this,DangNhap.class));
        }
    }

    private void BackUp(){
        if (isOnline()==true && tk.getStatus()==true) {
            Toast.makeText(this, "Backup", Toast.LENGTH_SHORT).show();
            List<DanhBa> list =  dataBase.lay_danhba();
            for (DanhBa d : list){
                API.initRetrofit().create(API.ApiInterface.class)
                        .backup(tk.getSdt(),d.getTen(),d.getSdt())
                        .enqueue(new Callback<RequestBody>() {
                            @Override
                            public void onResponse(Call<RequestBody> call, Response<RequestBody> response) {
                            }

                            @Override
                            public void onFailure(Call<RequestBody> call, Throwable t) {

                            }
                        });
            }
        }
        else Toast.makeText(this, "No online", Toast.LENGTH_SHORT).show();
    }
}
