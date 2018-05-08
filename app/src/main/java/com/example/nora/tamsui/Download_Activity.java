package com.example.nora.tamsui;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Map;

public class Download_Activity extends AppCompatActivity {
    ProgressDialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_in);
        dialog = ProgressDialog.show(Download_Activity.this,
                "讀取中", "請等待...",true);
        getDataFromFirebase();
    }

    static int ScenenIndex = 1;
    String TAG = "CreateData";
    ArrayList<SceneData> data = new ArrayList<>();
    void getDataFromFirebase() {
        //從資料庫下載資料
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        String collection = "Tamsui";
        String document = "Scene" + ScenenIndex;
        DocumentReference docRef = db.collection(collection).document(document);
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        for (Map.Entry<String, Object> temp : document.getData().entrySet()) {
                            try {
                                SceneData sceneData = new SceneData((Map<String, String>) temp.getValue());
                                data.add(sceneData);
                                //Log.e(TAG, sceneData.toString());
                            } catch (Exception e) {
                                Log.e(TAG, "Exception");
                            }
                        }
                        if (ScenenIndex < 4) {
                            Log.e(TAG,"Index"+ScenenIndex);
                            ScenenIndex++;
                            getDataFromFirebase();

                        } else {
                            dialog.dismiss();
                            Log.e(TAG,"Index"+data.size());
                            Intent intent = new Intent(Download_Activity.this,CreateData_Activity.class);
                            intent.putParcelableArrayListExtra("Data",data);
                            startActivity(intent);
                            Log.e(TAG,"mAdapter.notifyDataSetChanged()");
                        }
                    } else {
                        Log.d(TAG, "No such document");
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });
    }
}
