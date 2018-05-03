package com.example.nora.tamsui;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Random;

public  class SceneData {
    private  ArrayList<ArrayList<String>> Address;
    private String TAG = "ScenceData";

    SceneData() {
        Address = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("TamsuiPlace")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> map = document.getData();
                                ArrayList<String> temp = new ArrayList<>();
                                for (Map.Entry<String, Object> entry : map.entrySet()) {
                                    temp.add(entry.getValue().toString());
                                }
                                Address.add((ArrayList<String>) temp.clone());
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    //new struct
    ArrayList<Map<String, Object>> data;

    SceneData(int i) {
        Address = new ArrayList<>();
        data = new ArrayList<Map<String, Object>>();
        final FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("TamsuiPlace")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.e("ScenceData","isSuccessful()"+document.getData().toString());
                                Map<String, Object> map = document.getData();
                                data.add(map);

                            }
                        } else {
                            Log.w(TAG, "Error getting documents.", task.getException());
                        }
                    }
                });
    }

    private ArrayList<String> RandomData;
    public ArrayList<String> getData() {
        if(RandomData == null) shuffle();
        return RandomData;
    }
    //
    public void shuffle(){
        RandomData = new ArrayList<>();
        for(int i = 0 ;i<data.size();i++){
            Map<String,Object> temp = data.get(i);
            int index = (int) (Math.random() * temp.size());
            RandomData.add(temp.get(index+"").toString());
        }
    }
    public ArrayList<ArrayList<String>> getAllData(){
        return (ArrayList<ArrayList<String>>)Address.clone();
    }
}
