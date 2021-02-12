package com.nested.etutor.bottomsheets;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.nested.etutor.screens.PostForTution;
import com.nested.etutor.screens.PostTution_layout;
import com.nested.etutor.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BottomSheet_Post extends BottomSheetDialogFragment {

  private Button button1,button2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.bottom_sheet_post,container,false);

        button1=view.findViewById(R.id.bottomasstudent);
        button2=view.findViewById(R.id.bottomasteacher);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), PostForTution.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                dismiss();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getContext(), PostTution_layout.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                dismiss();
            }
        });
        return view;
    }

}
