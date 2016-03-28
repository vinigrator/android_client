package com.example.mkai.pry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;


public class SubscriptionActivity extends Activity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] myDataset = getDataSet();
        Button add_photo;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new SubscriptionAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);
    }


    private String[] getDataSet() {

        String[] mDataSet = new String[100];
        for (int i = 0; i < 100; i++) {
            mDataSet[i] = "item" + i;
        }
        return mDataSet;
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_photo:
                Intent intent = new Intent(SubscriptionActivity.this, SubscriptionActivity.class);
                startActivity(intent);
            default:
                break;
        }
    }
}