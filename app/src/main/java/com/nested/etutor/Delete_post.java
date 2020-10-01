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

public class Delete_post extends BottomSheetDialogFragment {

    private Button done,cancelp;
    private DeletepostListener deletepostListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v=inflater.inflate(R.layout.bottom_update_post,container,false);

        done=v.findViewById(R.id.deletepostid);
        cancelp=v.findViewById(R.id.cancelpostid);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deletepostListener.deleteListener(true);
                dismiss();
            }
        });
        cancelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            deletepostListener= (DeletepostListener) context;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    public interface DeletepostListener{
        void deleteListener(boolean flag);
    }
}
