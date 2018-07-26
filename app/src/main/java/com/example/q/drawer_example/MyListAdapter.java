package com.example.q.drawer_example;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {

    Context context;
    ArrayList<list_item> list_itemArrayList;

    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return this.list_itemArrayList.get( i );
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    TextView title;
    TextView id;
    TextView dep;
    TextView time;
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view==null){
            view= LayoutInflater.from( context ).inflate( R.layout.item,null );
            title=view.findViewById( R.id.Title );
            id=view.findViewById( R.id.id );
            dep= view.findViewById( R.id.department );
            time=view.findViewById( R.id.textView5 );
        }
            title.setText( list_itemArrayList.get(i).getTitle() );
            id.setText( list_itemArrayList.get( i ).getId() );
            dep.setText( list_itemArrayList.get( i ).getDep() );
            time.setText( list_itemArrayList.get( i ).getTime() );

        return view;
    }

    public MyListAdapter(Context context, ArrayList<list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }
}
