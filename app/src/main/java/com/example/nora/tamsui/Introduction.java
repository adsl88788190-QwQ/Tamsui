package com.example.nora.tamsui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nora on 2017/12/17.
 */

public class Introduction extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.introdution);
        Button back = (Button)findViewById(R.id.introdution_back);
        Bundle bundle = Introduction.this.getIntent().getExtras();

        String PlaceName=bundle.getString("PlaceName");
        String PlaceImage = bundle.getString("PlaceImage");
        IntroductionData introductionData = new IntroductionData();
        introductionData.check(PlaceName);

        int id =getResources().getIdentifier(PlaceImage, "drawable", getPackageName());

        TextView title = (TextView)findViewById(R.id.introdution_title);
        TextView content = (TextView)findViewById(R.id.introdution_content);
        TextView address = (TextView)findViewById(R.id.introdution_address);
        ImageView image = (ImageView)findViewById(R.id.intorduction_image);

        image.setImageResource(id);
        title.setText(introductionData.getTitle());
        title.setTextSize(50);
        content.setText(introductionData.getContent());
        content.setTextSize(25);
        address.setText("地址："+introductionData.getAddress());
        address.setTextSize(25);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Introduction.this,Random.class);
                startActivity(intent);
                Introduction.this.finish();
            }
        });
    }
}
