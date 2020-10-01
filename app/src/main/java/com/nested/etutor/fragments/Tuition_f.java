package com.nested.etutor.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nested.etutor.FindTeacher_layout;
import com.nested.etutor.FindTuition_layout;
import com.nested.etutor.Homepage_activity;
import com.nested.etutor.PostForTution;
import com.nested.etutor.PostTution_layout;
import com.nested.etutor.R;


public class Tuition_f extends Fragment implements View.OnClickListener {

    //as student
    private CardView findT, postT;
    //as teacher
    private CardView findS, postS;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tuition_f, container, false);

        //student option
        findT = view.findViewById(R.id.s_findteacherid);
        postT = view.findViewById(R.id.s_posttutionid);

        findT.setOnClickListener(this);
        postT.setOnClickListener(this);


        //teacher option
        findS = view.findViewById(R.id.Te_posttutionid);
        postS = view.findViewById(R.id.Te_findstudentid);
        findS.setOnClickListener(this);
        postS.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {



        //student option

        if (v.getId() == R.id.s_findteacherid) {
            Intent intent = new Intent(getContext(), FindTeacher_layout.class);
            startActivity(intent);
        } else if (v.getId() == R.id.s_posttutionid) {
            Intent intent = new Intent(getContext(), PostForTution.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.Te_posttutionid) {
            Intent intent = new Intent(getContext(), PostTution_layout.class);
            startActivity(intent);

        }
        if (v.getId() == R.id.Te_findstudentid) {
            Intent intent = new Intent(getContext(), FindTuition_layout.class);
            startActivity(intent);
        }

    }
}
