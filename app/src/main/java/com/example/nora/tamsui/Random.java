package com.example.nora.tamsui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Nora on 2017/12/17.
 */

public class Random extends AppCompatActivity {
    Button back;
    ImageView image1,image2,image3,image4;
    TextView PlaceName1,PlaceName2,PlaceName3,PlaceName4;
    String[] PlaceName,PlaceImage;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rand);

        RandomData randomData = new RandomData();
        PlaceName= randomData.getTextData();
        PlaceImage=randomData.getImageData();


        back = (Button)findViewById(R.id.rand_back);

        image1 = (ImageView)findViewById(R.id.PlaceImage_1);
        image2 = (ImageView)findViewById(R.id.PlaceImage_2);
        image3 = (ImageView)findViewById(R.id.PlaceImage_3);
        image4 = (ImageView)findViewById(R.id.PlaceImage_4);

        PlaceName1 = (TextView)findViewById(R.id.PlaceName_1);
        PlaceName2 = (TextView)findViewById(R.id.PlaceName_2);
        PlaceName3 = (TextView)findViewById(R.id.PlaceName_3);
        PlaceName4 = (TextView)findViewById(R.id.PlaceName_4);

        PlaceName1.setText(PlaceName[0]);
        PlaceName2.setText(PlaceName[1]);
        PlaceName3.setText(PlaceName[2]);
        PlaceName4.setText(PlaceName[3]);


        int[] id ={getResources().getIdentifier(PlaceImage[0], "drawable", getPackageName())
                ,getResources().getIdentifier(PlaceImage[1], "drawable", getPackageName())
                ,getResources().getIdentifier(PlaceImage[2], "drawable", getPackageName())
                ,getResources().getIdentifier(PlaceImage[3], "drawable", getPackageName())};


        image1.setImageResource(id[0]);
        image2.setImageResource(id[1]);
        image3.setImageResource(id[2]);
        image4.setImageResource(id[3]);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("PlaceName",PlaceName[0]);
                bundle.putString("PlaceImage",PlaceImage[0]);
                intent.setClass(Random.this,Introduction.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("PlaceName",PlaceName[1]);
                bundle.putString("PlaceImage",PlaceImage[1]);
                intent.setClass(Random.this,Introduction.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("PlaceName",PlaceName[2]);
                bundle.putString("PlaceImage",PlaceImage[2]);
                intent.setClass(Random.this,Introduction.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                Bundle bundle = new Bundle();
                bundle.putString("PlaceName",PlaceName[3]);
                bundle.putString("PlaceImage",PlaceImage[3]);
                intent.setClass(Random.this,Introduction.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Random.this,Tamsui_menu.class);
                startActivity(intent);
                Random.this.finish();
            }
        });
    }
}
