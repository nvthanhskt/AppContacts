package com.example.nguyenthanh.appcontacts.Adapter;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.nguyenthanh.appcontacts.DataBase;
import com.example.nguyenthanh.appcontacts.MainActivity;
import com.example.nguyenthanh.appcontacts.Model.DanhBa;
import com.example.nguyenthanh.appcontacts.ChiTietLienLac;
import com.example.nguyenthanh.appcontacts.Sua_LienLac;
import com.example.nguyenthanh.appcontacts.R;

import java.util.List;

public class Adapter_DanhBa extends RecyclerView.Adapter<Adapter_DanhBa.viewHolder>{
    Context mycontext;
    List<DanhBa> list;

    public  Adapter_DanhBa(Context mycontext, List<DanhBa> list) {
        this.mycontext = mycontext;
        this.list = list;
    }



    @Override
    public int getItemCount() {
        if(list==null) return  0;
        return list.size();
    }


    class viewHolder extends RecyclerView.ViewHolder {

        TextView txtTen,txtSDT;
        ImageView menu;



        public viewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txt_tenLL);
            txtSDT = itemView.findViewById(R.id.txt_sdt);
            menu = itemView.findViewById(R.id.LL_menu);
        }

    }
    @NonNull
    @Override
    public Adapter_DanhBa.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mycontext);
        View view = inflater.inflate(R.layout.danhba_item, null, false);
        Adapter_DanhBa.viewHolder v = new  Adapter_DanhBa.viewHolder(view);

        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, int i) {
            final int posi = i;


            viewHolder.txtSDT.setText(list.get(i).getSdt());
            viewHolder.txtTen.setText(list.get(i).getTen());
            viewHolder.txtSDT.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                chitietlienlac(posi);
                }
            });
            viewHolder.txtTen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chitietlienlac(posi);
                }
            });

            viewHolder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                PopupMenu pmenu = new PopupMenu(mycontext,viewHolder.menu);
                pmenu.getMenuInflater().inflate(R.menu.menu_lienlac,pmenu.getMenu());
                pmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {


                        switch (item.getItemId()){
                            case R.id.ll_suaLL:
                                Intent intent  = new Intent(mycontext,Sua_LienLac.class);
                                intent.putExtra("id",list.get(posi).getId());
                                mycontext.startActivity(intent);
//                                Toast.makeText(mycontext, " "+list.get(posi).getId(), Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.ll_xoaLL:
                                deleteGR(posi);
                                break;
                            case R.id.ll_goi:
                                lienhe(posi);
                                break;
                            case R.id.ll_themyeuthich:
                                new DataBase(mycontext).them_yeuthich(list.get(posi).getId());
                                mycontext.startActivity(new Intent(mycontext,MainActivity.class));
                                break;
                            case R.id.ll_chitiet:
                                chitietlienlac(posi);
                                break;
                        }
                        return true;
                    }

                });
                pmenu.show();
            }
        });
    }
    // Chi tiết liên lạc
    private  void chitietlienlac(int i){
        Intent intent = new Intent(mycontext,ChiTietLienLac.class);
        intent.putExtra("id",list.get(i).getId());
        mycontext.startActivity(intent);
    }
    // Gọi điện
    private void lienhe(int posi){

        Intent intent = new Intent(Intent.ACTION_CALL,Uri.parse("tel:"+list.get(posi).getSdt()));
        if (ActivityCompat.checkSelfPermission(mycontext, Manifest.permission.CALL_PHONE)!=PackageManager.PERMISSION_GRANTED)
        {
            return;
        }
        mycontext.startActivity(intent);
    }

    private void deleteGR(final int i){
        new AlertDialog.Builder(mycontext)
                .setMessage("Bạn chắc chắn muốn xóa ?")
                .setTitle("Cảnh báo")
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DataBase(mycontext).xoa_lienlac(list.get(i).getId());
                        list.remove(i);
                        notifyDataSetChanged();
                    }
                })
                .setPositiveButton("Cancel",null)
                .show();
    }

}
