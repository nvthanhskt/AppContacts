package com.example.nguyenthanh.appcontacts.Adapter;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nguyenthanh.appcontacts.Model.DanhBa;
import com.example.nguyenthanh.appcontacts.R;

import java.util.List;

public class Adapter_DanhBa_land extends RecyclerView.Adapter<Adapter_DanhBa_land.viewHolder>{
        Context mycontext;
        List<DanhBa> list;

public Adapter_DanhBa_land(Context mycontext, List<DanhBa> list) {
        this.mycontext = mycontext;
        this.list = list;
}



@Override
public int getItemCount() {
        if(list==null) return  0;
        return list.size();
        }
    @NonNull
    @Override
    public Adapter_DanhBa_land.viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mycontext);
        View view = inflater.inflate(R.layout.danhba_item, null, false);
        Adapter_DanhBa_land.viewHolder v = new  Adapter_DanhBa_land.viewHolder(view);
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapter_DanhBa_land.viewHolder viewHolder, int i) {
        final int posi = i;


        viewHolder.txtSDT.setText(list.get(i).getSdt());
        viewHolder.txtTen.setText(list.get(i).getTen());
        viewHolder.txtSDT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               lienhe(posi);
            }
        });
        viewHolder.txtTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lienhe(posi);
            }
        });

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

    class viewHolder extends RecyclerView.ViewHolder {
        TextView txtTen,txtSDT;
        ImageView menu,img;
        public viewHolder(@NonNull final View itemView) {
            super(itemView);
            txtTen = itemView.findViewById(R.id.txt_tenLL);
            txtSDT = itemView.findViewById(R.id.txt_sdt);
            menu =itemView.findViewById(R.id.LL_menu);
            menu.setVisibility(View.INVISIBLE);
        }

    }
}
