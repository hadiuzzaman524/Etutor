package com.nested.etutor;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BottomPhoto extends BottomSheetDialogFragment {

  private   Button save,cancel;
  private   PhotoChooseListener photoChooseListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.bottomphoto,container,false);
        save=view.findViewById(R.id.savephotoid);
        cancel=view.findViewById(R.id.canclephotoid);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoChooseListener.photoChoose(true);
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                photoChooseListener.photoChoose(false);
                dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            photoChooseListener= (PhotoChooseListener) context;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public interface PhotoChooseListener
    {
        void photoChoose(boolean flag);
    }
}
