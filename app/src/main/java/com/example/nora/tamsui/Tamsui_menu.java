package com.example.nora.tamsui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Map;

public class Tamsui_menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tamsui_menu);

        TextView title = (TextView)findViewById(R.id.menu_title);
        title.setTextSize(60);
        Button rand = (Button)findViewById(R.id.rand);
        rand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("ScenceData","RAND CLICK");
                getData();
                Intent intent = new Intent();
                intent.setClass(Tamsui_menu.this,Random.class);
                startActivity(intent);
                Tamsui_menu.this.finish();
            }
        });

        Button add = (Button)findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Tamsui_menu.this,SingInActivity.class);
                startActivity(intent);
            }
        });
    }
    SceneData data;
    void getData(){
        //從資料庫下載資料
        data = new SceneData(1);
        //隨機打亂
        data.shuffle();
        //取得資料

    }
}
