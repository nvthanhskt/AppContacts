package com.example.nguyenthanh.appcontacts.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.nguyenthanh.appcontacts.DanhSachTheoNhom;
import com.example.nguyenthanh.appcontacts.DataBase;
import com.example.nguyenthanh.appcontacts.Sua_Nhom;
import com.example.nguyenthanh.appcontacts.R;

import java.util.List;

public class Adapter_Nhom  extends RecyclerView.Adapter<Adapter_Nhom.viewHolder>{
    Context mycontext;
    List<String> list;

    public Adapter_Nhom(Context mycontext, List<String> list) {
        this.mycontext = mycontext;
        this.list = list;
    }

    @Override
    public void onBindViewHolder(@NonNull final viewHolder viewHolder, int i) {
        viewHolder.txtTen.setText(list.get(i));

        final  int x =i;
        viewHolder.txtTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mycontext,DanhSachTheoNhom.class);
                intent.putExtra("tennhom",list.get(x));
                mycontext.startActivity(intent);
            }
        });
        // Click menu
        viewHolder.menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View vi = v;
                PopupMenu pmenu = new PopupMenu(mycontext,viewHolder.menu);
                pmenu.getMenuInflater().inflate(R.menu.nhom_menu,pmenu.getMenu());
                pmenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        if(item.getItemId()== R.id.nav_suanhom){
                            Intent intent = new Intent(mycontext,Sua_Nhom.class);
                            intent.putExtra("tennhom",list.get(x));
                            mycontext.startActivity(intent);

                        }
                        if(item.getItemId()== R.id.nav_xoanhom){
                            deleteGR(x);
                        }
                        return true;
                    }

                });
                pmenu.show();
            }
        });

        // Click nhom
    }
    private void deleteGR(final int i){
        new AlertDialog.Builder(mycontext)
                .setMessage("Bạn chắc chắn muốn xóa ?")
                .setTitle("Cảnh báo")
                .setNegativeButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        new DataBase(mycontext).xoa_nhom(list.get(i));
                        list.remove(i);
                        notifyDataSetChanged();
                    }
                })
                .setPositiveButton("Cancel",null)
                .show();
    }
    @Override
    public int getItemCount() {
        if(list==null) return  0;
        return list.size();
    }



    class viewHolder extends RecyclerView.ViewHolder {

        TextView txtTen;
        ImageView menu;


        public viewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txt_tennhom);
            menu = itemView.findViewById(R.id.nhommenu);
        }

    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mycontext);
        View view = inflater.inflate(R.layout.nhom_item, null, false);
        viewHolder v = new viewHolder(view);
        return v;
    }

}

