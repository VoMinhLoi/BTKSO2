package com.example.bktso2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ListCayThuocNam extends AppCompatActivity {
    List<CayThuocNam> cayThuocNamList;
    CayThuocNamAdapter cayThuocNamAdapter;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caythuocnam);
        cayThuocNamList = new ArrayList<>();
        cayThuocNamList.add(new CayThuocNam("1","2","3","4","5","6",R.drawable.background));
        cayThuocNamList.add(new CayThuocNam("1","2","3","4","5","6",R.drawable.background));
        cayThuocNamList.add(new CayThuocNam("1","2","3","4","5","6",R.drawable.background));

        cayThuocNamAdapter = new CayThuocNamAdapter(ListCayThuocNam.this,cayThuocNamList);
        listView = findViewById(R.id.cayThuocLV);
        listView.setAdapter(cayThuocNamAdapter);

    }

}