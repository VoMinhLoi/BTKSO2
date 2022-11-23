package com.example.bktso2;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CayThuocNamAdapter extends BaseAdapter {
    Activity activity;
    List<CayThuocNam> cayThuocNamList;

    public CayThuocNamAdapter(Activity activity, List<CayThuocNam> cayThuocNamList) {
        this.activity = activity;
        this.cayThuocNamList = cayThuocNamList;
    }

    @Override
    public int getCount() {
        return cayThuocNamList.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater inflater = activity.getLayoutInflater();
        view = inflater.inflate(R.layout.customor_caythuocnam, null);
        CayThuocNam cayThuocNam = cayThuocNamList.get(i);
        ImageView imageView = view.findViewById(R.id.thuocIMG);
        TextView tenKHTV = view.findViewById(R.id.tenKhoaHocTV);
        TextView tenThTV = view.findViewById(R.id.tenThuongGoiTV);
        imageView.setImageResource(cayThuocNam.getHinhAnh());
        tenKHTV.setText(cayThuocNam.getTenKhoaHoc());
        tenThTV.setText(cayThuocNam.getTenThuongGoi());
        return view;
    }
}
