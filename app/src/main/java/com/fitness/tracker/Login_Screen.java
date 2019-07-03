package com.fitness.tracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Login_Screen extends AppCompatActivity {
    private LinearLayout mdots_layout;
    private TextView[] dots;
    ViewPager mViewPager;
    ImageAdapter adapterView;
    int currentPage = 0;
    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    final long PERIOD_MS = 2000; // time in milliseconds between successive task executions.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);
        Button bsignup = findViewById(R.id.bSignup);


        TextView textView;
        textView = findViewById(R.id.btnlogin);
        textView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(Login_Screen.this, Login_account.class);
                startActivity(intent);
            }
        });

        bsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login_Screen.this,Personal_info.class);
                startActivity(intent);
            }
        });

        mdots_layout = (LinearLayout) findViewById(R.id.dots);
        mViewPager = findViewById(R.id.viewPage);
         adapterView = new ImageAdapter(this);
        mViewPager.setAdapter(adapterView);
        mViewPager.addOnPageChangeListener(viewListener);
        setTimerTask();
        add_dotindicator(0);
    }
     public void add_dotindicator(int position){

        dots = new TextView[4];
        mdots_layout.removeAllViews();
        for(int i=0; i<dots.length; i++){
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
            mdots_layout.addView(dots[i]);
        }
        if(dots.length>0){
            dots[position].setTextColor(getResources().getColor(R.color.colorPrimary));
        }
     }
     ViewPager.OnPageChangeListener viewListener = new ViewPager.OnPageChangeListener(){

         @Override
         public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

         }

         @Override
         public void onPageSelected(int position) {
             add_dotindicator(position);

         }

         @Override
         public void onPageScrollStateChanged(int state) {

         }
     };

    private void setTimerTask() {
        /*After setting the adapter use the timer */
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == adapterView.getCount()) {
                    currentPage = 0;
                }
                mViewPager.setCurrentItem(currentPage++, true);
            }
        };

        timer = new Timer(); // This will create a new Thread
        timer.schedule(new TimerTask() { // task to be scheduled
            @Override
            public void run() {
                handler.post(Update);
            }
        }, DELAY_MS, PERIOD_MS);
    }

    @Override
    protected void onStop() {
        super.onStop();
        timer.cancel();
    }
}
