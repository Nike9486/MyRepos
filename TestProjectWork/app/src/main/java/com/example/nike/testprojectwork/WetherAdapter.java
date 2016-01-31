package com.example.nike.testprojectwork;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Nike on 27.01.2016.
 */
public class WetherAdapter extends BaseAdapter{
Context ctx;
LayoutInflater layoutInflater;
List<StackWether> wethers;
    SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy \n время: H");


    public WetherAdapter(Context context,List<StackWether> wether){
            ctx=context ;
            wethers=wether;
                    layoutInflater=(LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
       }

    @Override
    public int getCount() {
        return wethers.size();
    }

    @Override
    public Object getItem(int position) {
        return wethers.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(view==null){
            view=layoutInflater.inflate(R.layout.row_wether,parent,false);
        }
        StackWether stack=getWether(position);
        ((TextView)view.findViewById(R.id.data)).setText(sdf.format(stack.getDate().getTime()
        ));
        ((TextView)view.findViewById(R.id.temper)).setText(stack.getMinHeat()+"..."+stack.getMaxHeat());
        return view;
    }
    StackWether getWether (int position){
        return ((StackWether)getItem(position));
    }
}
