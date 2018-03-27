package com.example.android.mydoctor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ActivityAppointDoctor extends AppCompatActivity {
    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appoint_doctor);

        final ListView mListView = (ListView) findViewById(R.id.lv_DoctorList);
        Button bViewMore = (Button)findViewById(R.id.bViewMore);

        final ArrayList<Doctor> doctorArrayList = new ArrayList<>();

        final Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject getDoctorData = new JSONObject(response);
                    JSONArray getDoctorArray = getDoctorData.getJSONArray("result");

                    for(int x=0;x<getDoctorArray.length();x++)
                    {
                        JSONObject singleDoctor = getDoctorArray.getJSONObject(x);
                        String docname = singleDoctor.getString("docname");
                        String docage = singleDoctor.getString("docage");
                        String qualification = singleDoctor.getString("qualification");
                        String docrating = singleDoctor.getString("docrating");

                        Doctor doctor = new Doctor(docname,docage,qualification,docrating);
                        doctorArrayList.add(doctor);

                        DoctorListAdapter doctorListAdapter = new DoctorListAdapter(ActivityAppointDoctor.this,R.layout.doctorlist_view_layout,doctorArrayList);
                        mListView.setAdapter(doctorListAdapter);

                        View view = mListView.getChildAt(0);
                        int pos = (view == null ? 0 :  view.getBottom());
                        mListView.setSelectionFromTop(doctorListAdapter.getCount(),pos);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        };

        DoctorDataRequest doctorDataRequest = new DoctorDataRequest(count,listener);
        RequestQueue requestQueue = Volley.newRequestQueue(ActivityAppointDoctor.this);
        requestQueue.add(doctorDataRequest);

        bViewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count += 5;
                DoctorDataRequest doctorDataRequest = new DoctorDataRequest(count,listener);
                RequestQueue requestQueue = Volley.newRequestQueue(ActivityAppointDoctor.this);
                requestQueue.add(doctorDataRequest);
            }
        });


    }
}
