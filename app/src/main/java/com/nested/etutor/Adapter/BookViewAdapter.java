package com.nested.etutor.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nested.etutor.R;

public class BookViewAdapter extends BaseAdapter {

    Context context;
    String[] bookName;
    int[] flags;
    LayoutInflater inflater;

    public BookViewAdapter(Context context, String[] bookName, int[] flags) {
        this.context = context;
        this.bookName = bookName;
        this.flags = flags;
    }

    @Override
    public int getCount() {
        return bookName.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

      try {

          if (convertView == null) {
              inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              convertView = inflater.inflate(R.layout.books_view, parent, false);
          }
          ImageView imageView = convertView.findViewById(R.id.booklistid);
          TextView textView = convertView.findViewById(R.id.booknameid);

          imageView.setImageResource(flags[position]);
          textView.setText(bookName[position]);

      }
      catch (Exception e)
      {

      }
        return convertView;
    }
}
