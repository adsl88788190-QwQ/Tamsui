package com.example.nora.tamsui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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
                Intent intent = new Intent();
                intent.setClass(Tamsui_menu.this,Random.class);
                startActivity(intent);
                Tamsui_menu.this.finish();
            }
        });
    }
}
