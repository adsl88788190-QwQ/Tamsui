package com.example.nora.tamsui;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SingInActivity extends AppCompatActivity {

    private Spinner user_spn;
    private EditText pwd_et;
    private Button signin_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);

        //layout元件設定
        user_spn = (Spinner)findViewById(R.id.user_spn);
        pwd_et = (EditText)findViewById(R.id.pwd_et);
        signin_btn = (Button)findViewById(R.id.signin_btn);
        //設定使用者選單
        UserSpinner();
        //設定按鈕點擊
        SignInButton();
    }

    void UserSpinner(){
        String[] User = new String[]{getString(R.string.gueest_name),getString(R.string.admin_name)};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,User);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        user_spn.setAdapter(adapter);

        //下拉式選單選到後
        user_spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                //選取普通使用者
                if( i == 0){
                    //不需要輸入密碼
                    pwd_et.setVisibility(View.INVISIBLE);
                }else{//使用者需要輸入密碼
                    pwd_et.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //沒選任何東西時  默認選第一個 Guest
                user_spn.setSelection(0);
            }
        });
    }

    void SignInButton(){
        signin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //取得下拉式選單的值
                String SelectItem = user_spn.getSelectedItem().toString();
                //如果是普通使用者
                if(SelectItem.equals(getString(R.string.gueest_name))){
                    //
                    Intent intent = new Intent(SingInActivity.this,Tamsui_menu.class);
                    startActivity(intent);
                }else{//如果是管理者
                    String password = pwd_et.getText().toString();
                    //檢查密碼是否有錯 錯誤則跳出訊息
                    if (TextUtils.isEmpty(password)) {
                        Toast.makeText(SingInActivity.this,"密碼錯誤",Toast.LENGTH_SHORT).show();
                        return;
                    }
                    //登入檢查
                    FirebaseLogin();
                }
            }
        });
    }

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    void FirebaseLogin(){
        firebaseAuth = FirebaseAuth.getInstance();
        if(pwd_et.getText().toString().equals("admin")) {
            Intent intent = new Intent(SingInActivity.this, Download_Activity.class);
            startActivity(intent);
        }else{
            Toast.makeText(SingInActivity.this,"密碼錯誤",Toast.LENGTH_SHORT).show();
        }

//
//        //if(firebaseAuth != null )Log.e("TAMSUI",firebaseAuth.getCurrentUser().getUid());
//        firebaseAuth.signInWithEmailAndPassword(account,pwd).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                //登入成功
//                if(task.isSuccessful()){
//                    //進去新增修改選單
//                    Toast.makeText(SingInActivity.this,"新增修改選單",Toast.LENGTH_SHORT).show();
//                    Log.e("TAMSUI","SUCCESS");
//                }
//                else{
//                    Log.e("TAMSUI","Login Error "+task.toString());
//                    Toast.makeText(SingInActivity.this,"登入錯誤",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });

    }
}
