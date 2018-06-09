package com.apppath.developer.the.volleyapiexample.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.apppath.developer.the.volleyapiexample.R;
import com.apppath.developer.the.volleyapiexample.model.Users;

import java.util.ArrayList;

public class UserAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Users> usersArrayList;

    public UserAdapter(Context context, ArrayList<Users> usersArrayList) {
        this.context = context;
        this.usersArrayList = usersArrayList;
    }

    @Override
    public int getCount() {
        return usersArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return usersArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = View.inflate(context, R.layout.user_list_layout, null);
        }

        //ImageView icon_list = convertView.findViewById(R.id.image_icon_list);
        TextView full_name = convertView.findViewById(R.id.full_name_textview);
        TextView email_address = convertView.findViewById(R.id.email_address_textview);
        TextView address = convertView.findViewById(R.id.address_textview);

        Users users = usersArrayList.get(position);

        full_name.setText(users.getFullName());
        email_address.setText(users.getEmailAddress());
        address.setText(users.getAddress());

        return convertView;
    }
}
