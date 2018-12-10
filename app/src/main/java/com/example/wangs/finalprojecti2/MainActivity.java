package com.example.wangs.finalprojecti2;

import android.app.VoiceInteractor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.awt.font.TextAttribute;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView mTextViewResult;
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.text_view_result);
        Button buttonParse = findViewById(R.id.button_parse);
        mQueue = Volley.newRequestQueue(this);

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });

        //
        Button inventories = findViewById(R.id.hello);

        inventories.setOnClickListener(this);
    }

    private void jsonParse() {
        String url = "https://api.myjson.com/bins/lck5y";
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Financial Report 2017");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject shortTerm = jsonArray.getJSONObject(i);
                                String oneThree = shortTerm.getString("Cash and cash equivalents");
                                String oneFour = shortTerm.getString("Short-term investments");
                                String oneFive = shortTerm.getString("Total cash");
                                String oneSix = shortTerm.getString("Receivables");
                                String oneSeven = shortTerm.getString("Inventories");


                                mTextViewResult.append("Financial Report 2017" + "\n\nCash and cash equivalents: " + oneThree + "\nShort-term investments: " + oneFour + "\nTotal cash: " + oneFive + "\nReceivables: " + oneSix + "\nInventories: " + oneSeven + "\n\n");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }

    //
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.hello:
                Toast.makeText(this, "Thanks for clicking me, please try the one next to me", Toast.LENGTH_SHORT).show();
                break;

        }
    }
}

