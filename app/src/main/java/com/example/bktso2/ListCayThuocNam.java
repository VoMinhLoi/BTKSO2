package com.example.bktso2;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListCayThuocNam extends AppCompatActivity {
    List<CayThuocNam> cayThuocNamList;
    CayThuocNamAdapter cayThuocNamAdapter;
    ListView listView;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference cayThuoc1, myRefTenKH, myRefTenThuong, myRefCongDung, myRefDacTinh, myRefLieuLuongCachDung, myRefHinhAnh;
    String tenKH, tenThuong, congDung, dacTinh, lieuLuongCachDung;
    Integer hinhAnh;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caythuocnam);
//        GetDataOfFirebaseDatabase();
        cayThuocNamList = new ArrayList<>();
        firebaseDatabase = FirebaseDatabase.getInstance();
        cayThuoc1 = firebaseDatabase.getReference("caythuoc1");
        listView = findViewById(R.id.cayThuocLV);

        cayThuoc1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tenKH = cayThuoc1.child("tenKhoaHoc").toString();
                System.out.println("Ben trong: "+ tenKH);
                tenThuong = cayThuoc1.child("tenThuong").toString();
                dacTinh = cayThuoc1.child("dacTinh").toString();
                congDung = cayThuoc1.child("congDung").toString();
                cayThuocNamList.add(new CayThuocNam(tenKH,tenThuong,dacTinh, "",congDung, "", R.drawable.background));
                cayThuocNamList.add(new CayThuocNam(tenKH,tenThuong,dacTinh, "",congDung, "", R.drawable.background));
                cayThuocNamList.add(new CayThuocNam(tenKH,tenThuong,dacTinh, "",congDung, "", R.drawable.background));
                cayThuocNamList.add(new CayThuocNam(tenKH,tenThuong,dacTinh, "",congDung, "", R.drawable.background));
                cayThuocNamList.add(new CayThuocNam(tenKH,tenThuong,dacTinh, "",congDung, "", R.drawable.background));
                cayThuocNamAdapter = new CayThuocNamAdapter(ListCayThuocNam.this, cayThuocNamList);
                listView.setAdapter(cayThuocNamAdapter);
            }
            public void onCancelled(@NonNull DatabaseError error) {}
        });
        System.out.println("ra ngoaif: "+ tenKH );
    }
}