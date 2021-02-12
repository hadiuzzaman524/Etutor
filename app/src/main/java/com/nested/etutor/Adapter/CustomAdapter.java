package com.nested.etutor.Adapter;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nested.etutor.R;
import com.nested.etutor.models.UploadStudentPostInformation;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private List<UploadStudentPostInformation> uploadList;
    //
    private OnItemClickListener listener;

    public CustomAdapter(Context context, List<UploadStudentPostInformation> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }


    @NonNull
    @Override
    public CustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.student_showlist_formate, parent, false);

        return new CustomAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.MyViewHolder holder, int position) {

        UploadStudentPostInformation upload = uploadList.get(position);
        holder.name.setText(upload.getName());
        holder.address.setText(""+upload.getAddress());
        holder.institute.setText(""+upload.getInstitute());
        holder.cclass.setText(""+upload.get_class());
        holder.subject.setText(""+upload.getSubject());
        holder.salary.setText(""+upload.getSalary()+" BDT");
        holder.mobile.setText(""+upload.getMobile());
        holder.header.setText(""+upload.getImagelink());

        holder.cdate.setText(""+upload.getDate());
    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        private TextView name, address,institute, cclass, subject, salary, mobile,cdate,header;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.set_student_name);
            address = itemView.findViewById(R.id.set_student_address);
            institute=itemView.findViewById(R.id.set_student_institute);
            cclass = itemView.findViewById(R.id.set_student_class);
            subject = itemView.findViewById(R.id.set_student_subject);
            salary = itemView.findViewById(R.id.set_student_salary);
            mobile = itemView.findViewById(R.id.set_student_mobile);
            header=itemView.findViewById(R.id.headerid);
            cdate=itemView.findViewById(R.id.set_student_date);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);


        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {

            if (listener!=null)
            {
                int position=getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION)
                {

                    if(item.getItemId()==1)
                    {
                        listener.callHim(position);
                        return  true;
                    }
                }
            }
            return false;
        }

        @Override
        public void onClick(View v) {

            if (listener!=null)
            {
                int position=getAdapterPosition();
                if (position!=RecyclerView.NO_POSITION)
                {
                    listener.onItemClick(position);
                }
            }

        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem call=menu.add(Menu.NONE,1,1,"Call Now");
            call.setOnMenuItemClickListener(this);
        }
    }

//new
    public interface OnItemClickListener{
        void onItemClick(int position);
        void callHim(int position);

    }
//new
    public  void setOnItemClickListener(OnItemClickListener listener){
        this.listener=listener;

    }
}
