package com.example.android.mydoctor;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by DELL on 10/4/2017.
 */

public class DoctorListAdapter extends ArrayAdapter<Doctor> {
    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    static class ViewHolder{
        TextView docname;
        TextView docage;
        TextView qualification;
        TextView docrating;
    }

    public DoctorListAdapter(Context context, int resource, ArrayList<Doctor> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String docname = getItem(position).getDocName();
        String docage = getItem(position).getDocAge();
        String qualification = getItem(position).getQualification();
        String docrating = getItem(position).getDocRating();

        Doctor doctor = new Doctor(docname,docage,qualification,docrating);

        final View result;
        ViewHolder holder;

        if(convertView==null)
        {
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource,parent,false);

            holder = new ViewHolder();
            holder.docname = (TextView)convertView.findViewById(R.id.tv_DoctorName);
            holder.docage = (TextView)convertView.findViewById(R.id.tv_DoctorAge);
            holder.qualification = (TextView)convertView.findViewById(R.id.tv_DoctorQualification);
            holder.docrating = (TextView)convertView.findViewById(R.id.tv_DoctorRating);

            result = convertView;

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        Animation animation = AnimationUtils.loadAnimation(mContext,(position>lastPosition)?R.anim.layout_down_anim:R.anim.layout_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.docname.setText(doctor.getDocName());
        holder.docage.setText(doctor.getDocAge());
        holder.qualification.setText(doctor.getQualification());
        holder.docrating.setText(doctor.getDocRating());

        return convertView;
    }
}
