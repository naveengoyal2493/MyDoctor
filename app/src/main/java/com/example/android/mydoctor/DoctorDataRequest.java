package com.example.android.mydoctor;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 10/4/2017.
 */

public class DoctorDataRequest extends StringRequest{

    public static final String GET_DOCTORDATA_URL = "https://tailless-nests.000webhostapp.com/getdoctordata.php";
    Map<String,String> params;

    public DoctorDataRequest(int count, Response.Listener<String> listener){
        super(Method.POST,GET_DOCTORDATA_URL,listener,null);
        params= new HashMap<>();
        params.put("count",count+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
