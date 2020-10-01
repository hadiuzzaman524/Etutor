package com.nested.etutor.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nested.etutor.R;
import com.nested.etutor.Result;

public class Result_f extends Fragment implements View.OnClickListener {
private CardView psc,jsc,masters,honers,eleven,polit,degree,nu;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view= inflater.inflate(R.layout.fragment_result_f, container, false);

        psc=view.findViewById(R.id.pscresultid);
        jsc=view.findViewById(R.id.jscresultid);
        masters=view.findViewById(R.id.mastersadid);
        honers=view.findViewById(R.id.honersid);
        eleven=view.findViewById(R.id.elevenclassad);
        polit=view.findViewById(R.id.politacnicid);
        degree=view.findViewById(R.id.degreeid);
        nu=view.findViewById(R.id.nuid);

        psc.setOnClickListener(this);
        jsc.setOnClickListener(this);
        masters.setOnClickListener(this);
        honers.setOnClickListener(this);
        eleven.setOnClickListener(this);
        polit.setOnClickListener(this);
        degree.setOnClickListener(this);
        nu.setOnClickListener(this);
    return view;
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.pscresultid)
        {
            Intent intent=new Intent(getContext(), Result.class);
            intent.putExtra("result","psc");
            startActivity(intent);
        }

        if (v.getId()==R.id.jscresultid)
        {
            Intent intent=new Intent(getContext(),Result.class);
            intent.putExtra("result","jsc");
            startActivity(intent);
            //  Toast.makeText(getApplicationContext(),"jsc",Toast.LENGTH_SHORT).show();
        }

        if (v.getId()==R.id.elevenclassad)
        {
            Intent intent=new Intent(getContext(),Result.class);
            intent.putExtra("result","eleven");
            startActivity(intent);

        }

        if (v.getId()==R.id.politacnicid)
        {

            Intent intent=new Intent(getContext(),Result.class);
            intent.putExtra("result","poli");
            startActivity(intent);
        }

        if (v.getId()==R.id.nuid)
        {
            Intent intent=new Intent(getContext(),Result.class);
            intent.putExtra("result","nu");
            startActivity(intent);
        }

        if (v.getId()==R.id.mastersadid)
        {
            Intent intent=new Intent(getContext(),Result.class);
            intent.putExtra("result","masters");
            startActivity(intent);
        }

        if (v.getId()==R.id.degreeid)
        {
            Intent intent=new Intent(getContext(),Result.class);
            intent.putExtra("result","degree");
            startActivity(intent);
        }

        if (v.getId()==R.id.honersid)
        {
            Intent intent=new Intent(getContext(),Result.class);
            intent.putExtra("result","honers");
            startActivity(intent);
        }
    }
}
