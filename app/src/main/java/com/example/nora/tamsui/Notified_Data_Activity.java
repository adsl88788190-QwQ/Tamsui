package com.example.nora.tamsui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class Notified_Data_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notifi_data);
        CompontSetting();
        getData();
        setData();

    }

    EditText Name_et, Address_et, Description_et;
    ImageView Image;
    Button Back;
    public void CompontSetting() {
        Name_et = (EditText) findViewById(R.id.Name_et);
        Address_et = (EditText) findViewById(R.id.Address_et);
        Description_et = (EditText) findViewById(R.id.Description_et);
        Image = (ImageView) findViewById(R.id.Image);
        Back = (Button)findViewById(R.id.SentData_bt);
    }

    SceneData data;

    private void getData() {
        data = getIntent().getParcelableExtra("Data");
    }

    private void setData() {
        Name_et.setText(data.getName());
        Address_et.setText(data.getAddress());
        Description_et.setText(data.getDescription());
        Glide.with(this)
                .load(data.getImagePath())
                .apply(new RequestOptions().centerCrop().diskCacheStrategy(DiskCacheStrategy.RESOURCE))
                .into(Image);
    }

    private void UpdateData(){

    }

}
