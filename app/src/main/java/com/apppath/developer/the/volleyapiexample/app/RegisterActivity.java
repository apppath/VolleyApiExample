package com.apppath.developer.the.volleyapiexample.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.apppath.developer.the.volleyapiexample.R;
import com.apppath.developer.the.volleyapiexample.config.Constants;
import com.apppath.developer.the.volleyapiexample.config.MySingleton;
import com.valdesekamdem.library.mdtoast.MDToast;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText full_name, email_address, password, address;
    private Button registerButton;
    private MDToast mdToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        full_name = findViewById(R.id.full_name_edittext);
        email_address = findViewById(R.id.email_address_edittext);
        password = findViewById(R.id.password_edittext);
        address = findViewById(R.id.address_edittext);

        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(this);
    }

    private void registerUsers() {

        final String fullName = full_name.getText().toString().trim();
        final String emailAddress = email_address.getText().toString().trim();
        final String userPassword = password.getText().toString().trim();
        final String userAddress = address.getText().toString().trim();

        if (fullName.isEmpty()) {
            mdToast = MDToast.makeText(RegisterActivity.this, "Please Enter Name Here", MDToast.LENGTH_SHORT, MDToast.TYPE_INFO);
            mdToast.show();
            return;
        }

        if (emailAddress.isEmpty()) {
            mdToast = MDToast.makeText(RegisterActivity.this, "Please Enter Email Address Here", MDToast.LENGTH_SHORT, MDToast.TYPE_INFO);
            mdToast.show();
            return;
        }

        if (userPassword.isEmpty()) {
            mdToast = MDToast.makeText(RegisterActivity.this, "Please Enter Password Here", MDToast.LENGTH_SHORT, MDToast.TYPE_INFO);
            mdToast.show();
            return;
        }

        if (userAddress.isEmpty()) {
            mdToast = MDToast.makeText(RegisterActivity.this, "Please Enter Address Here", MDToast.LENGTH_SHORT, MDToast.TYPE_INFO);
            mdToast.show();
            return;
        }


        StringRequest stringRequest = new StringRequest(Request.Method.POST, Constants.BASE_REGISTER_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        mdToast = MDToast.makeText(RegisterActivity.this, "Successfully Register User", MDToast.LENGTH_SHORT, MDToast.TYPE_SUCCESS);
                        mdToast.show();
                        startActivity(new Intent(RegisterActivity.this , MainActivity.class));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {

                Map<String, String> params = new HashMap<>();

                params.put(Constants.KEY_FULL_NAME, fullName);
                params.put(Constants.KEY_EMAIL_ADDRESS, emailAddress);
                params.put(Constants.KEY_PASSWORD, userPassword);
                params.put(Constants.KEY_ADDRESS, userAddress);

                return params;
            }
        };

        MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);

        full_name.setText("");
        email_address.setText("");
        password.setText("");
        address.setText("");


    }

    @Override
    public void onClick(View v) {
        registerUsers();
    }


}
