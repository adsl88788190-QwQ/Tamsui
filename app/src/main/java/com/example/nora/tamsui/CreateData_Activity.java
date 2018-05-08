package com.example.nora.tamsui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Scene;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.nikhilpanju.recyclerviewenhanced.OnActivityTouchListener;
import com.nikhilpanju.recyclerviewenhanced.RecyclerTouchListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CreateData_Activity extends AppCompatActivity implements RecyclerTouchListener.RecyclerTouchListenerHelper {

    RecyclerView mRecyclerView;
    MainAdapter mAdapter;
    String[] dialogItems;
    List<Integer> unclickableRows, unswipeableRows;
    private RecyclerTouchListener onTouchListener;
    private int openOptionsPosition;
    private OnActivityTouchListener touchListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createdata);

        if (getSupportActionBar() != null)
            getSupportActionBar().setTitle("RecyclerViewEnhanced");

        unclickableRows = new ArrayList<>();
        unswipeableRows = new ArrayList<>();
        dialogItems = new String[25];
        for (int i = 0; i < 25; i++) {
            dialogItems[i] = String.valueOf(i + 1);
        }
        getData();
        mRecyclerView = (RecyclerView) findViewById(R.id.DataRecycleView);
        RecycleViewSetting();
    }

    public void RecycleViewSetting() {
        mAdapter = new MainAdapter(this, list);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        onTouchListener = new RecyclerTouchListener(this, mRecyclerView);
        onTouchListener
                .setIndependentViews(R.id.rowButton)
                .setViewsToFade(R.id.rowButton)
                .setClickable(new RecyclerTouchListener.OnRowClickListener() {
                    @Override
                    public void onRowClicked(int position) {
                        //GoToEdit(position);
                    }

                    @Override
                    public void onIndependentViewClicked(int independentViewID, int position) {
                        GoToEdit(position);

                    }
                })
                .setLongClickable(true, new RecyclerTouchListener.OnRowLongClickListener() {
                    @Override
                    public void onRowLongClicked(int position) {

                        Toast.makeText(getApplicationContext(), "Row " + (position + 1) + " long clicked!", Toast.LENGTH_SHORT).show();

                    }
                })
                .setSwipeOptionViews(R.id.add, R.id.edit, R.id.change)
                .setSwipeable(R.id.rowFG, R.id.rowBG, new RecyclerTouchListener.OnSwipeOptionsClickListener() {
                    @Override
                    public void onSwipeOptionClicked(int viewID, int position) {
                        String message = "";
                        if (viewID == R.id.add) {
                            message += "信仰不夠........請充值";
                        } else if (viewID == R.id.edit) {
                            GoToEdit(position);
                            return;
                        } else if (viewID == R.id.change) {
                            message += "不要給妳這個功能阿";
                        }
                        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mRecyclerView.addOnItemTouchListener(onTouchListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mRecyclerView.removeOnItemTouchListener(onTouchListener);
    }

    List<SceneData> list;

    private List<SceneData> getData() {
        list = getIntent().getParcelableArrayListExtra("Data");
        for (SceneData temp : list) {
            Log.e("getData", temp.getName());
        }
        return list;
    }


    private void showSingleSelectDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Open Swipe Options for row: ")
                .setSingleChoiceItems(dialogItems, 0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openOptionsPosition = which;
                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        onTouchListener.openSwipeOptions(openOptionsPosition);
                    }
                });
        builder.create().show();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (touchListener != null) touchListener.getTouchCoordinates(ev);
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void setOnActivityTouchListener(OnActivityTouchListener listener) {
        this.touchListener = listener;
    }

    private class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
        LayoutInflater inflater;
        List<SceneData> dataList;

        public MainAdapter(Context context, List<SceneData> list) {
            inflater = LayoutInflater.from(context);
            dataList = new ArrayList<>(list);
        }

        @Override
        public MainViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.recycler_row, parent, false);
            return new MainViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MainViewHolder holder, int position) {
            holder.bindData(dataList.get(position));
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }

        class MainViewHolder extends RecyclerView.ViewHolder {

            TextView Name, Description, Address;

            public MainViewHolder(View itemView) {
                super(itemView);
                Name = (TextView) itemView.findViewById(R.id.mainText);
                Description = (TextView) itemView.findViewById(R.id.subText);
            }

            public void bindData(SceneData sceneData) {
                Name.setText(sceneData.getName());
                Description.setText(sceneData.getDescription().substring(0, 15) + ".....\n Address :" + sceneData.getAddress());
            }
        }
    }

    private void GoToEdit(int position){
        Intent intent = new Intent(CreateData_Activity.this, Notified_Data_Activity.class);
        intent.putExtra("Data", list.get(position));
        startActivity(intent);
    }
}
