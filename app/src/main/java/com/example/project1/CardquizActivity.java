package com.example.project1;

import android.icu.text.DateFormat;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

public class CardquizActivity extends AppCompatActivity {
    //Creating Var
    public static int score=0;
    public static String g_email;
    //creating obj
    DatabaseHelper_QuizGame mDatabaseHelper;

    //Console Tag
    private static final String TAG = "Card Quiz Activity";

    //Fragment obj
    private FragmentStatePagerSupport mFragmentStatePagerSupport;
    //Container obj
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_list_container); //this should load your container
        Log.d(TAG, "onCreate: Started");

        //Ini vars
        mDatabaseHelper= new DatabaseHelper_QuizGame(this);
        String email = getIntent().getStringExtra("USERNAME");
        g_email = email;

        //Allows toolbar title to display
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        //ini obj
        mFragmentStatePagerSupport = new FragmentStatePagerSupport(getSupportFragmentManager());
        //ini container obj, activity_master_list_container id container
        mViewPager = (ViewPager) findViewById(R.id.container);

        //set up the pager
        setupViewPager(mViewPager);
    }

    //Feed Fragments to viewPager
    private void setupViewPager(ViewPager viewPager){
        FragmentStatePagerSupport adapter = new FragmentStatePagerSupport(getSupportFragmentManager());
        adapter.addFragment(new Fragment1(), "Question List"); //by default 1st fragment will be open
        adapter.addFragment(new Fragment2(), "Question 1");
        adapter.addFragment(new Fragment3(), "Question 2");
        adapter.addFragment(new Fragment4(), "Question 3");
        adapter.addFragment(new Fragment5(), "Question 4");
        adapter.addFragment(new Fragment6(), "Question 5");
        adapter.addFragment(new Fragment7(), "Result");
        viewPager.setAdapter(adapter);
    }

    //Will redirect to fragment
    public void setViewPager (int fragmentNumber){
        mViewPager.setCurrentItem(fragmentNumber);
    }

    //COPY score value then RESET score back to 0
    public int returnScore(){
        int retain = score;
        score = 0;
        return retain;
    }

    //Everytime a user answers correctly score in incremented
    public void addScore (){
        score++;
        String str1 = Integer.toString(score);
        Log.d("CurrentScore", str1);
    }

    //Here we pass our values to our DBHelper_QuizGame
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void addData(){
        String msg1 = "Score was saved";
        String msg2 = "Score was not saved";

        DateFormat df = new SimpleDateFormat("d MMM yyyy, HH:mm");
        String date = df.format(Calendar.getInstance().getTime());

        Log.d("Values being stored:", g_email +" "+ score +" "+ date);

        boolean insertData = mDatabaseHelper.addData(g_email, score, date);

        if(insertData){
            toastMessage(msg1);
        }else{
            toastMessage(msg2);
        }
    }

    //Generic Toastmaker
    private void toastMessage (String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

    //Function to pass email to DBHelper_QuizGame
    public static String passEmail(){
        return g_email;
    }
}