package com.apppath.developer.the.volleyapiexample.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.apppath.developer.the.volleyapiexample.R;
import com.apppath.developer.the.volleyapiexample.adapter.UserAdapter;
import com.apppath.developer.the.volleyapiexample.config.Constants;
import com.apppath.developer.the.volleyapiexample.config.MySingleton;
import com.apppath.developer.the.volleyapiexample.model.Users;
import com.valdesekamdem.library.mdtoast.MDToast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public MDToast mdToast;
    private Users users;
    private UserAdapter userAdapter;
    private ArrayList<Users> usersArrayList;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.my_list_view);
        getUserList();
    }


    private void getUserList() {

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, Constants.BASE_USER_LIST_URL, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            usersArrayList = new ArrayList<>();
                            JSONArray jsonArray = response.getJSONArray(Constants.KEY_JSON_ARRAY_ROOT_ITEM);

                            for (int i = 0; i < jsonArray.length(); i++) {

                                Users users = new Users();

                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                users.setFullName(jsonObject.getString(Constants.KEY_FULL_NAME));
                                users.setEmailAddress(jsonObject.getString(Constants.KEY_EMAIL_ADDRESS));
                                users.setAddress(jsonObject.getString(Constants.KEY_ADDRESS));
                                usersArrayList.add(users);

                            }

                            userAdapter = new UserAdapter(getApplicationContext(), usersArrayList);
                            listView.setAdapter(userAdapter);
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();

                    }
                });

              MySingleton.getInstance(MainActivity.this).addToRequestQueue(jsonObjectRequest);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.menu_setting:
                mdToast = MDToast.makeText(MainActivity.this, "Setting Menu Clicked", MDToast.LENGTH_SHORT, MDToast.TYPE_INFO);
                mdToast.show();
                break;

            case R.id.menu_insert:
                startActivity(new Intent(MainActivity.this, RegisterActivity.class));
                break;

        }

        return super.onOptionsItemSelected(item);
    }
}
