package com.example.mkai.pry;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ResultAdapter extends BaseAdapter {
    private Context context;
    private List<PersonDescriptor> items;

    public ResultAdapter(List<PersonDescriptor> items, Context context) {
        super();
        this.items = items;
        this.context =context;
    }

    @Override
    public int getCount() {
        return this.items.size();
    }

    @Override
    public PersonDescriptor getItem(int position) {
        return this.items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater mInflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = mInflater.inflate(R.layout.result_item, null);
        }

        final TextView tvName = (TextView) v
                .findViewById(R.id.name);
        final TextView tvBirthday = (TextView) v
                .findViewById(R.id.birthday);
        final TextView tvCity = (TextView) v
                .findViewById(R.id.city);

        final String name = items.get(position).getName();
        final String birthday = items.get(position).getBirthday();
        final String city = items.get(position).getCity();

        tvName.setText(name);
        tvBirthday.setText(birthday);

        tvCity.setText(city);
        return v;
    }
}
