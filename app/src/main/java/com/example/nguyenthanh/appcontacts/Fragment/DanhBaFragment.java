package com.example.nguyenthanh.appcontacts.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.nguyenthanh.appcontacts.Adapter.Adapter_DanhBa;
import com.example.nguyenthanh.appcontacts.DataBase;
import com.example.nguyenthanh.appcontacts.Model.DanhBa;
import com.example.nguyenthanh.appcontacts.R;
import com.example.nguyenthanh.appcontacts.ThemDanhBa;

import java.util.ArrayList;
import java.util.List;

public class DanhBaFragment extends Fragment {
    ArrayList<DanhBa> list ;
    RecyclerView recyclerView;
    Adapter_DanhBa adapter_danhBa;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.danh_ba_fragment,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // them danh ba
        FloatingActionButton fab = (FloatingActionButton) view.findViewById(R.id.fab_danhba);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),ThemDanhBa.class);
                startActivity(intent);
            }
        });

        // Hiển thị danh sách
        recyclerView = view.findViewById(R.id.danhba_list);
        list = new ArrayList<>();
        list = new  DataBase(getContext()).lay_danhba();
        setDanhBaAdapter(list);

        SearchView searchView =(SearchView)view.findViewById(R.id.search_bar);
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
                setDanhBaAdapter(newList);
                return false;
            }
        });
    }

    private void setDanhBaAdapter(List<DanhBa> list){
        adapter_danhBa = new Adapter_DanhBa(getContext(),list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_danhBa);
    }

}
