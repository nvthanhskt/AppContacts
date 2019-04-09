package com.example.nguyenthanh.appcontacts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.nguyenthanh.appcontacts.Model.DanhBa;
import com.example.nguyenthanh.appcontacts.Model.TaiKhoan;

import java.util.ArrayList;
import java.util.Collections;

public class DataBase extends SQLiteOpenHelper {
    private static  String base="DBContacts";
    private Context context;
    public DataBase(Context context) {
        super(context,base,null,1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" Create Table tbl_danhba ( id integer primary key , ten  text , sdt text , nhom text , yeuthich boolean ) ");
        db.execSQL(" Create Table tbl_nhom (tennhom  text primary key) ");
        db.execSQL(" Create Table tbl_taikhoan (sdt text , hoten text) ");
//        Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
    }
    public  boolean them_nhom(String n){
         if(kiemtra_nhom(n)==false){

            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("tennhom",n);
            database.insert("tbl_nhom",null,values);

            database.close();
            Toast.makeText(this.context, "Đã thêm "+n, Toast.LENGTH_SHORT).show();
            return true;
         }

         return false;
    }
    public boolean kiemtra_nhom(String n) {
        String selectQuery = "SELECT  * FROM tbl_nhom where tennhom = '"+n+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

            if(cursor.moveToFirst()){
                Toast.makeText(this.context, "Nhóm "+n+" đã có", Toast.LENGTH_SHORT).show();
                return true;
            }

            return false;

    }
    public  void sua_nhom(String n1,String n2){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("update tbl_nhom set tennhom = '"+n1+"' where tennhom = '"+n2+"'" );
        database.execSQL("update tbl_danhba set nhom ='"+n1+"' where nhom ='"+n2+"'");
        database.close();
        Toast.makeText(this.context, "Đã lưu "+n1, Toast.LENGTH_SHORT).show();

    }
    public  void xoa_nhom(String n){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from tbl_nhom  where tennhom ='"+n+"'");
        database.execSQL("update tbl_danhba set nhom ='' where nhom ='"+n+"'");
        database.close();
        Toast.makeText(this.context, "Đã xóa "+n, Toast.LENGTH_SHORT).show();

    }


    public ArrayList<String> lay_nhom(){
        ArrayList<String> nhoms = new ArrayList<String>();
        String selectQuery = "SELECT  * FROM tbl_nhom ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try{

            if(cursor!=null){
                cursor.moveToFirst();
                do{
                    nhoms.add(cursor.getString(0));
                }while (cursor.moveToNext());
            }
            return nhoms;
        }
        catch (Exception e){
            return  null;
        }

    }

    /// Danh ba
    public  void xoa_lienlac(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("delete from tbl_danhba  where id ="+id);
        database.close();
        Toast.makeText(this.context, "Đã xóa ", Toast.LENGTH_SHORT).show();

    }
    public void them_danhba(DanhBa db){
        if(kiemTraTen(db.getTen())==false){
            SQLiteDatabase database = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("ten",db.getTen());
            values.put("sdt",db.getSdt());
            values.put("yeuthich",db.isYeuthich());
            values.put("nhom",db.getNhom());
            database.insert("tbl_danhba",null,values);
            database.close();
        }


    }
    public boolean kiemTraTen(String ten){
        String selectQuery = "SELECT  * FROM tbl_danhba where ten = '"+ten+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            return true;
        }

        return false;

    }

    public ArrayList<DanhBa> lay_danhba(){
        ArrayList<DanhBa> danhBas = new ArrayList<DanhBa>();
        String selectQuery = "SELECT  *  FROM tbl_danhba order by ten ASC ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try{

            if(cursor.moveToFirst()){

                do{
                    DanhBa danhBa = new DanhBa();
                    danhBa.setId(cursor.getInt(0));
                    danhBa.setTen(cursor.getString(1));
                    danhBa.setSdt(cursor.getString(2));
                    danhBa.setNhom(cursor.getString(3));
                    if(cursor.getInt(4)==1)
                    danhBa.setYeuthich(true);
                    else danhBa.setYeuthich(false);
                    danhBas.add(danhBa);
                }while (cursor.moveToNext());
            }

            Collections.sort(danhBas);
            return danhBas ;
        }
        catch (Exception e){
            return  null;
        }

    }
    public ArrayList<DanhBa> lay_danhbatheonhom(String nhom){
        ArrayList<DanhBa> danhBas = new ArrayList<DanhBa>();
        String selectQuery = "SELECT  *  FROM tbl_danhba where nhom = '"+nhom+"'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try{

            if(cursor.moveToFirst()){

                do{
                    DanhBa danhBa = new DanhBa();
                    danhBa.setId(cursor.getInt(0));
                    danhBa.setTen(cursor.getString(1));
                    danhBa.setSdt(cursor.getString(2));
                    danhBa.setNhom(cursor.getString(3));
                    if(cursor.getInt(4)==1)
                        danhBa.setYeuthich(true);
                    else danhBa.setYeuthich(false);
                    danhBas.add(danhBa);
                }while (cursor.moveToNext());
            }

            Collections.sort(danhBas);
            return danhBas ;
        }
        catch (Exception e){
            return  null;
        }

    }
    public ArrayList<DanhBa> lay_yeuthich(){
        ArrayList<DanhBa> danhBas = new ArrayList<DanhBa>();
        String selectQuery = "SELECT  *  FROM tbl_danhba where yeuthich = 1 ";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try{

            if(cursor.moveToFirst()){

                do{
                    DanhBa danhBa = new DanhBa();
                    danhBa.setId(cursor.getInt(0));
                    danhBa.setTen(cursor.getString(1));
                    danhBa.setSdt(cursor.getString(2));
                    danhBa.setNhom(cursor.getString(3));
                    if(cursor.getInt(4)==1)
                        danhBa.setYeuthich(true);
                    else danhBa.setYeuthich(false);
                    danhBas.add(danhBa);
                }while (cursor.moveToNext());
            }
            Collections.sort(danhBas);
            return danhBas ;
        }
        catch (Exception e){
            return  null;
        }

    }
    public DanhBa lay_danhba(int id){

        String selectQuery = "SELECT  * FROM tbl_danhba where id ="+id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        try{
            cursor.moveToFirst();
            DanhBa danhBa = new DanhBa();
            danhBa.setId(cursor.getInt(0));
            danhBa.setTen(cursor.getString(1));
            danhBa.setSdt(cursor.getString(2));
            danhBa.setNhom(cursor.getString(3));
            if(cursor.getInt(4)==1)
                danhBa.setYeuthich(true);
            else danhBa.setYeuthich(false);

            return danhBa;
        }
        catch (Exception e){
            return  null;
        }

    }
    public  void sua_nhomLL(String nhom,int id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("update tbl_danhba set  nhom ='"+nhom+"' where id = "+id+"" );
        database.close();

    }
    public  void sua_lienlac(int id, String ten , String sdt ,Boolean yeuthich ,String nhom){
        SQLiteDatabase database = this.getWritableDatabase();
        int yt =0;
        if(yeuthich==true) yt =1;
        database.execSQL("update tbl_danhba set ten = '"+ten+"' , sdt ='"+sdt+"' ,yeuthich = "+yt+" , nhom ='"+nhom+"' where id = "+id+"" );
        database.close();

    }
    public  void xoa_yeuthich(int id){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("update tbl_danhba set yeuthich = 0  where id = "+id );
        database.close();

    }
    public  void them_yeuthich(int id){
        SQLiteDatabase database = this.getWritableDatabase();

        database.execSQL("update tbl_danhba set yeuthich = 1  where id = "+id );
        database.close();

    }

    /// Tài khoản
    public  void dangxuat(){
        SQLiteDatabase database = this.getWritableDatabase();
        database.execSQL("Delete from tbl_taikhoan");
        database.close();

    }
    public void them_taikhoan(String sdt,String hoten){
        dangxuat();
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("sdt",sdt);
        values.put("hoten",hoten);
        database.insert("tbl_taikhoan",null,values);

        database.close();
    }
    public boolean kiemtra_taikhoan() {
        String selectQuery = "SELECT  * FROM tbl_taikhoan";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            return true;
        }

        return false;

    }
    public TaiKhoan get_taikhoan() {
        String selectQuery = "SELECT  * FROM tbl_taikhoan";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        TaiKhoan tk = new TaiKhoan();
        tk.setStatus(false);
        if(cursor.moveToFirst()){
            tk.setStatus(true);
            tk.setHoten(cursor.getString(1));
            tk.setSdt(cursor.getString(0));

        }


        return tk;

    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
