package com.example.project1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.ArrayList;

//An Adapter object acts as a bridge between an AdapterView and the underlying data for that view.
public class MyAdapter extends BaseAdapter {
    //Creating context, array
    Context context;
    ArrayList<QuizGame> arrayList;

    //function used to pass array
    public MyAdapter(Context context, ArrayList<QuizGame> arrayList) {
        this.context=context;
        this.arrayList = arrayList;
    }

    ////Get the row id associated with the specified position in the list.
    @Override
    public long getItemId(int position) {
        return position;
    }

    //Get the data item associated with the specified position in the data set.
    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    /*
     * Get a View that displays the data at the specified position in the data set. You can either create a
     * View manually or inflate it from an XML layout file. When the View is inflated, the parent View (GridView, ListView...)
     * will apply default layout parameters unless you use LayoutInflater.inflate(int, android.view.ViewGroup, boolean)
     * to specify a root view and to prevent attachment to the root.
     * TL:DR builds our list
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent){

        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.mycustomlistview, null);

        //Create TextViews Buttons and associate to txtViews
        TextView t1_score = (TextView)convertView.findViewById(R.id.score_txt);
        TextView t3_date = (TextView)convertView.findViewById(R.id.date_txt);

        QuizGame qG = arrayList.get(position);

        //assign values to our list
        t1_score.setText(String.valueOf(qG.getScore()));
        t3_date.setText(qG.getDate());

        return convertView;
    }

    //Determines how many items are in the data set represented by this Adapter.
    @Override
    public int getCount() {
        return this.arrayList.size();
    }
}
