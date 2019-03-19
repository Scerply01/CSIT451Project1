package com.example.project1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {
    //Creating obj
    DatabaseHelper_QuizGame mDatabaseHelper_QuizGame;
    MyAdapter myAdapter;
    //Creating list
    ListView list;
    //Creating array
    ArrayList<QuizGame> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        //initializing lists, objs, array
        list = (ListView)findViewById(R.id.list1);
        mDatabaseHelper_QuizGame = new DatabaseHelper_QuizGame(this);
        arrayList = new ArrayList<>();

        loadDataInListView();
    }

    //Filling array by using getAllData, Ini adapter & pass array
    public void loadDataInListView(){
        arrayList = mDatabaseHelper_QuizGame.getAllData();
        myAdapter = new MyAdapter(this,arrayList);
        //In order to display items in the list, call setAdapter(android.widget.ListAdapter)
        // to associate an adapter with the list. https://developer.android.com/reference/android/widget/ListView
        list.setAdapter(myAdapter);
        //Use notify everytime list is updated https://stackoverflow.com/questions/3669325/notifydatasetchanged-example
        myAdapter.notifyDataSetChanged();
    }
}