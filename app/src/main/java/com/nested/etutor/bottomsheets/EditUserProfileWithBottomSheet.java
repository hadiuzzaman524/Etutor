package com.nested.etutor.bottomsheets;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nested.etutor.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;


public class EditUserProfileWithBottomSheet extends BottomSheetDialogFragment {

   private ButtomEditprofileListener buttomEditprofileListener;
   private   EditText name, qualifi,abt,cont,birth;
   private Button update;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {

      View view=  inflater.inflate(R.layout.bottom_edit_profile,container,false);

      name=view.findViewById(R.id.enameid);
       qualifi=view.findViewById(R.id.equalificationid);
       abt=view.findViewById(R.id.eaboutid);
       cont=view.findViewById(R.id.econtactid);
       birth=view.findViewById(R.id.ebirthdayid);
       update=view.findViewById(R.id.eupdateprofileid);

       update.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               String n=name.getText().toString().trim();
               String q=qualifi.getText().toString().trim();
               String ab=abt.getText().toString().trim();
               String c=cont.getText().toString().trim();
               String b=birth.getText().toString().trim();

               buttomEditprofileListener.ProfileOnclick(n,q,ab,c,b);
               dismiss();
           }
       });


        return view;
    }

    public interface ButtomEditprofileListener{
        void ProfileOnclick(String name,String education,String about,String contact,String birthday);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            buttomEditprofileListener= (ButtomEditprofileListener) context;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
