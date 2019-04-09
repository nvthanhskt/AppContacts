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

import com.example.nguyenthanh.appcontacts.Adapter.Adapter_DanhBa;
import com.example.nguyenthanh.appcontacts.Adapter.Adapter_YeuThich;
import com.example.nguyenthanh.appcontacts.DataBase;
import com.example.nguyenthanh.appcontacts.Model.DanhBa;
import com.example.nguyenthanh.appcontacts.R;

import java.util.ArrayList;

public class YeuThich_Fragment extends Fragment {
    ArrayList<DanhBa> list;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView = view.findViewById(R.id.list_yeuthich);
        list = new ArrayList<>();

        list = new DataBase(getContext()).lay_yeuthich();

        Adapter_YeuThich adapter_yeuThich = new Adapter_YeuThich(getContext(),list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter_yeuThich);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.yeuthich_fragment,container,false);
    }
}
