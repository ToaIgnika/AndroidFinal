package com.example.toa.finalexam;

import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

import static java.util.Collections.shuffle;

public class MainActivity extends AppCompatActivity implements
        TextToSpeech.OnInitListener{

    private TextToSpeech tts;
    private Button btnSpeak;
    private RequestQueue sRequestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tts = new TextToSpeech(this, this);
        btnSpeak = (Button) findViewById(R.id.btn_speak);

        btnSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speakOut();
            }
        });


        sRequestQueue = Volley.newRequestQueue(this);


        readUrl();
    }

    @Override
    public void onDestroy() {
        // Don't forget to shutdown tts!
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }

    @Override
    public void onInit(int status) {

        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.JAPAN);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
            } else {
               // speakOut();
            }

        } else {
        }

    }

    private void speakOut() {
        String text = "three zero zero";
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
        Toast.makeText(this, "hey", Toast.LENGTH_SHORT).show();
    }

    private void readUrl() {
        String url ="https://firebasestorage.googleapis.com/v0/b/landmarks-c3f28.appspot.com/o/urls.json?alt=media";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                //System.out.println("HHH" + jsonArray);
                                JSONObject obj = response.getJSONObject(i);
                                String name = obj.getString("name");
                                System.out.println("HHH" + name);
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
        sRequestQueue.add(request);
    }
}
