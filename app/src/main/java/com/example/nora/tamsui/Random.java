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

import java.util.ArrayList;

/**
 * Created by Nora on 2017/12/17.
 */

public class Random extends AppCompatActivity {
    Button back;
    ImageView image1, image2, image3, image4;
    TextView PlaceName1, PlaceName2, PlaceName3, PlaceName4;
    String[] PlaceName, PlaceImage;

    ArrayList<String> SceneData;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rand);

        //RandomData randomData = new RandomData();
        //Bundle bundle = getIntent().getExtras();
        //SceneData =  bundle.getStringArrayList("data");

        //PlaceName = randomData.getTextData();
        //PlaceImage = randomData.getImageData();

        //CompontSetting();
        //返回按鈕設定
        back = (Button)findViewById(R.id.rand_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(Random.this, Tamsui_menu.class);
                startActivity(intent);
                Random.this.finish();
            }
        });
    }

    //元件設定
    private void CompontSetting() {
        //設定返回按鈕
        back = (Button) findViewById(R.id.rand_back);
        //設定景點圖片
        image1 = (ImageView) findViewById(R.id.PlaceImage_1);
        image2 = (ImageView) findViewById(R.id.PlaceImage_2);
        image3 = (ImageView) findViewById(R.id.PlaceImage_3);
        image4 = (ImageView) findViewById(R.id.PlaceImage_4);
        //設定景點名稱
        PlaceName1 = (TextView) findViewById(R.id.PlaceName_1);
        PlaceName2 = (TextView) findViewById(R.id.PlaceName_2);
        PlaceName3 = (TextView) findViewById(R.id.PlaceName_3);
        PlaceName4 = (TextView) findViewById(R.id.PlaceName_4);
        //顯示景點名稱
        String LogText = getString(getResources().getIdentifier(SceneData.get(0),"string",getPackageName()));
        Log.e("Class Random","LogText : "+LogText);
        PlaceName1.setText(SceneData.get(0));
        PlaceName2.setText(SceneData.get(1));
        PlaceName3.setText(SceneData.get(2));
        PlaceName4.setText(SceneData.get(3));
        //搜尋圖片資源
        int[] id = {getResources().getIdentifier(PlaceImage[0], "drawable", getPackageName())
                , getResources().getIdentifier(PlaceImage[1], "drawable", getPackageName())
                , getResources().getIdentifier(PlaceImage[2], "drawable", getPackageName())
                , getResources().getIdentifier(PlaceImage[3], "drawable", getPackageName())};
        //設定圖片資源
        image1.setImageResource(id[0]);
        image2.setImageResource(id[1]);
        image3.setImageResource(id[2]);
        image4.setImageResource(id[3]);
        //設定圖片點擊
        image1.setOnClickListener(onClickListener);
        image2.setOnClickListener(onClickListener);
        image3.setOnClickListener(onClickListener);
        image4.setOnClickListener(onClickListener);
    }
    //點擊偵聽器
    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int index = 0;
            switch (((Button) view).getId()) {
                case R.id.PlaceImage_1:
                    index = 0;
                    break;
                case R.id.PlaceImage_2:
                    index = 1;
                    break;
                case R.id.PlaceImage_3:
                    index = 2;
                    break;
                case R.id.PlaceImage_4:
                    index = 3;
                    break;
            }
            Intent intent = new Intent();
            Bundle bundle = new Bundle();
            bundle.putString("PlaceName", PlaceName[index]);
            bundle.putString("PlaceImage", PlaceImage[index]);
            intent.setClass(Random.this, Introduction.class);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    };
}
