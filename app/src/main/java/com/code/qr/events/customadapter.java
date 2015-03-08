package com.code.qr.events;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.code.qr.events.Fields.Directories;
import com.code.qr.events.process.Folders;
import com.code.qr.events.process.SubString;

import java.io.File;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by shak on 18/1/2015.
 */
public class customadapter extends BaseAdapter implements ListAdapter {



    private Context context;
    private String sevent;
    private String sdate;

    public customadapter( Context ctx){
        if(Folders.list!=null) {
            Set se = new HashSet(Folders.list);
            Folders.list.clear();
            Folders.list.addAll(se);
            Collections.sort(Folders.list, new CustomComparator());
        }
        this.context =ctx;
    }
    public class CustomComparator implements Comparator<String[]> {
        @Override
        public int compare(String[] o1, String[] o2) {
            return o1[1].compareTo(o2[1]);
        }
    }

    @Override
    public int getCount() {
        return Folders.list.size();
    }

    @Override
    public Object getItem(int position) {
        return Folders.list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
String[]arr;
    int i=0;

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=null ;
arr =new String[2];
        if (convertView == null){
            try {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.adapter, parent, false);
                arr = Folders.list.get(position);

               ((TextView) view.findViewById(R.id.event)).setText(arr[0]);
             ((TextView) view.findViewById(R.id.eventdate)).setText(arr[1]);


                try {
                    if (SubString.dateDiff(arr[1]) >=1)
                        ((TextView) view.findViewById(R.id.ended)).setVisibility(View.VISIBLE);
                } catch (Exception c) {
                }
                 Button deleteBtn = (Button)view.findViewById(R.id.delete);



                deleteBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {


                sevent = Folders.list.get(position)[0];


                    sdate = Folders.list.get(position)[1];

                Folders.list.remove(position); //or some other task
                viewlist.lView.removeAllViews();
                viewlist.setView();
                 notifyDataSetChanged();
                new Thread(new Runnable() {

                    @Override
                    public void run() {
//delete file
//
                        new File(Folders.dir,sevent+"-"+sdate+ Directories.ext).delete();
                  }
                }).start();

            }
        });
            }catch(Exception c){}

        }else{
            view =convertView;
        }
        return view;
    }
}
