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
import com.nested.etutor.models.UploadTeacherInformation;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdepter extends RecyclerView.Adapter<MyAdepter.MyViewHolder> {

    private Context context;
    private List<UploadTeacherInformation> uploadList;
    private OnItemClickListener listener;

    public MyAdepter(Context context, List<UploadTeacherInformation> uploadList) {
        this.context = context;
        this.uploadList = uploadList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.teacherlist_show_formate, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        UploadTeacherInformation upload = uploadList.get(position);
        holder.name.setText(upload.getTname());

        holder.address.setText(""+upload.getTaddress());
        holder.education.setText(""+upload.getTeducation());
        holder.cclass.setText(""+upload.getT_class());
        holder.subject.setText(""+upload.getTsubject());
        holder.salary.setText(""+upload.getTsalary()+" BDT");
        holder.mobile.setText(""+upload.getTmobile());
        holder.descrip.setText(""+upload.getTimagelink());

      //  Picasso.with(context).load(upload.getTimagelink()).placeholder(R.mipmap.ic_launcher_round).fit().centerCrop().into(holder.imageView);
        holder.cdate.setText(""+upload.getDate());

    }

    @Override
    public int getItemCount() {
        return uploadList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        private TextView name, address, education, cclass, subject, salary, mobile,cdate,descrip;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.set_teacher_name);
            address = itemView.findViewById(R.id.set_teacher_address);
            education = itemView.findViewById(R.id.set_teacher_education);
            cclass = itemView.findViewById(R.id.set_teacher_class);
            subject = itemView.findViewById(R.id.set_teacher_subject);
            salary = itemView.findViewById(R.id.set_teacher_salary);
            mobile = itemView.findViewById(R.id.set_teacher_mobile);
            descrip=itemView.findViewById(R.id.teacherheaderid);
            cdate=itemView.findViewById(R.id.set_teacher_date);
            //new
            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);

        }
//new
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
//new
        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

            MenuItem call=menu.add(Menu.NONE,1,1,"Call Now");
            call.setOnMenuItemClickListener(this);
        }
//new
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
