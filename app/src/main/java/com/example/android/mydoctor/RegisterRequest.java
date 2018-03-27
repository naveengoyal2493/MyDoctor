package com.example.android.mydoctor;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by DELL on 9/13/2017.
 */

public class RegisterRequest extends StringRequest {

    private static final String REGISTER_REQUEST_URL = "https://tailless-nests.000webhostapp.com/RegisterMyDoctor.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String mobilenumber, String email, String password, Response.Listener<String> listener){
        super(Method.POST,REGISTER_REQUEST_URL,listener,null);

        params = new HashMap<>();
        params.put("name",name);
        params.put("mobilenumber",mobilenumber);
        params.put("email",email);
        params.put("password",password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
