package com.example.mkai.pry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class SubscriptionActivity extends AppCompatActivity {

    final String LOG_TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String[] myDataset = getDataSet();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ListView subsListView = (ListView) findViewById(R.id.subsListView);
        // обрабатывает нажатие на пункт списка
        subsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d(LOG_TAG, subsListView.getItemAtPosition(position).toString() + " itemClick: position = " + position + ", id = "
                        + id);
                Intent intent = new Intent(SubscriptionActivity.this, ResultActivity.class);
                startActivity(intent);
            }
        });

        //обрабатывает выделение пунктов списка (
        subsListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                Log.d(LOG_TAG, "itemSelect: position = " + position + ", id = "
                  + id);
            }

            public void onNothingSelected(AdapterView<?> parent) {
                Log.d(LOG_TAG, "itemSelect: nothing");
            }
        });
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.sub_item, myDataset);
        subsListView.setAdapter(adapter);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
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