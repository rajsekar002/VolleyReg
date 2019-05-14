package com.androindian.volleyreg;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.androindian.volleyreg.databinding.ActivityMainBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    RequestQueue requestQueue;
    String url="http://androindian.com/apps/example_app/api.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(MainActivity.this,
                R.layout.activity_main);

        requestQueue= Volley.newRequestQueue(MainActivity.this);

        binding.Reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JSONObject jsonObject=new JSONObject();
                try {
                    jsonObject.put("name",binding.name.getText().toString().trim());
                    jsonObject.put("mobile",binding.mobile.getText().toString().trim());
                    jsonObject.put("email",binding.email.getText().toString().trim());
                    jsonObject.put("pswrd",binding.pass.getText().toString().trim());
                    jsonObject.put("baction","register_user");

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(
                        Request.Method.POST, url, jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {

                                try {
                                    String res1=response.getString("response");

                                    if(res1.equalsIgnoreCase("failed")){

                                        String res2=response.getString("user");
                                        Toast.makeText(MainActivity.this, ""+res2, Toast.LENGTH_SHORT).show();

                                    }else if(res1.equalsIgnoreCase("success")){
                                        String res2=response.getString("user");
                                        Toast.makeText(MainActivity.this, ""+res2, Toast.LENGTH_SHORT).show();

                                    }else {
                                        String res2=response.getString("user");
                                        Toast.makeText(MainActivity.this, ""+res2, Toast.LENGTH_SHORT).show();

                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(MainActivity.this, ""+error.toString(), Toast.LENGTH_SHORT).show();
                                Log.i("error",error.toString());

                            }
                        }

                );
                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}
