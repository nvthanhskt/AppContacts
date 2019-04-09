package com.example.nguyenthanh.appcontacts.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nguyenthanh.appcontacts.Adapter.Adapter_Nhom;
import com.example.nguyenthanh.appcontacts.DataBase;
import com.example.nguyenthanh.appcontacts.R;

import java.util.List;

public class NhomFragment extends Fragment {
    List<String> list;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.nhom_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // set adapter recycler
        final RecyclerView recyclerView;
        list = new DataBase(getContext()).lay_nhom();
        final Adapter_Nhom adapter_nhom = new Adapter_Nhom(getContext(),list);
        recyclerView = view.findViewById(R.id.list_nhom);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_nhom);
        // Thêm nhóm
        final View vi = view;
        view.findViewById(R.id.fab_nhom).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vi.findViewById(R.id.list_nhom).setVisibility(View.INVISIBLE);
                vi.findViewById(R.id.fab_nhom).setVisibility(View.INVISIBLE);
                vi.findViewById(R.id.them_nhom).setVisibility(View.VISIBLE);


                vi.findViewById(R.id.btthemnhom).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText ed_ten = vi.findViewById(R.id.ed_tennhom);
                        if(ed_ten.getText().toString().compareTo("")==0){
                            Toast.makeText(getActivity(), "Không bỏ trống", Toast.LENGTH_SHORT).show();
                            ed_ten.setBackgroundResource(R.drawable.boder);
                        }else {
                            try {
                                Boolean c = new DataBase(getActivity()).them_nhom(ed_ten.getText().toString());
                                if(c==true){
                                    vi.findViewById(R.id.list_nhom).setVisibility(View.VISIBLE);
                                    vi.findViewById(R.id.fab_nhom).setVisibility(View.VISIBLE);
                                    vi.findViewById(R.id.them_nhom).setVisibility(View.INVISIBLE);
                                    list.add(ed_ten.getText().toString());
                                    adapter_nhom.notifyDataSetChanged();
                                    ed_ten.setText("");
                                }
                            }catch (Exception e){  return; };




                        }
                    }
                });
                vi.findViewById(R.id.quaylai).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EditText ed_ten = vi.findViewById(R.id.ed_tennhom);
                        ed_ten.setText("");
                        adapter_nhom.notifyDataSetChanged();
                        vi.findViewById(R.id.list_nhom).setVisibility(View.VISIBLE);
                        vi.findViewById(R.id.fab_nhom).setVisibility(View.VISIBLE);
                        vi.findViewById(R.id.them_nhom).setVisibility(View.INVISIBLE);
                    }
                });

            }
        });



    }
}