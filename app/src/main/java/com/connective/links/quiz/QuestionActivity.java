package com.connective.links.quiz;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private MyViewPagerAdapter myViewPagerAdapter;
    private LinearLayout dotsLayout;
    private TextView[] dots;
    HorizontalScrollView hsvQuestion;
    TextView timerText;
    String arrAns[]={"1972-73","1984-85","1991-92","All of the above"};
    int duration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        duration=getIntent().getIntExtra("duration",0);
        setContentView(R.layout.activity_question);
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        dotsLayout = (LinearLayout) findViewById(R.id.layoutIndicator);
        hsvQuestion=(HorizontalScrollView)findViewById(R.id.hsvQuestion);
        myViewPagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(myViewPagerAdapter);
        addBottomIndicators(0);
        viewPager.addOnPageChangeListener(viewPagerPageChangeListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.timer, menu);

        MenuItem timerItem = menu.findItem(R.id.break_timer);
        timerText = (TextView) MenuItemCompat.getActionView(timerItem);

        timerText.setPadding(10, 0, 10, 0); //Or something like that...

        startTimer(duration, 1000); //One tick every second for 30 seconds
        return true;
    }
    private void startTimer(long duration, long interval) {

        CountDownTimer timer = new CountDownTimer(duration, interval) {

            @Override
            public void onFinish() {
                //TODO Whatever's meant to happen when it finishes
                Toast.makeText(QuestionActivity.this, "Your Time is Over", Toast.LENGTH_SHORT).show();
                Intent i=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(i);
            }

            @Override
            public void onTick(long millisecondsLeft) {
                int secondsLeft = (int) Math.round((millisecondsLeft / (double) 1000));
                timerText.setText(secondsToString(secondsLeft));
            }
        };

        timer.start();
    }
    private String secondsToString(int improperSeconds) {

        //Seconds must be fewer than are in a day

        Time secConverter = new Time();

        secConverter.hour = 0;
        secConverter.minute = 0;
        secConverter.second = 0;

        secConverter.second = improperSeconds;
        secConverter.normalize(true);

        String hours = String.valueOf(secConverter.hour);
        String minutes = String.valueOf(secConverter.minute);
        String seconds = String.valueOf(secConverter.second);

        if (seconds.length() < 2) {
            seconds = "0" + seconds;
        }
        if (minutes.length() < 2) {
            minutes = "0" + minutes;
        }
        if (hours.length() < 2) {
            hours = "0" + hours;
        }

        String timeString = hours + ":" + minutes + ":" + seconds;
        return timeString;
    }
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    private void addBottomIndicators(int currentPage) {
        dots = new TextView[30];
        dotsLayout.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(QuestionActivity.this);
            if (i<10){
                dots[i].setText("0"+i);
            }else {
                dots[i].setText("" + i);
            }
            dots[i].setTextSize(35);
            dots[i].setTextColor(Color.WHITE);
            dots[i].setPadding(10,10,10,10);
            dots[i].setBackground(getResources().getDrawable(R.drawable.circle_red));
            final int finalI = i;
            dots[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(QuestionActivity.this, ""+dots[finalI].getText().toString(), Toast.LENGTH_SHORT).show();
                    viewPager.setCurrentItem(finalI);
                }
            });
            dotsLayout.addView(dots[i]);
        }

    }

    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
        @Override
        public void onPageSelected(int position) {
            addBottomIndicators(position);
            dots[position].setBackground(getResources().getDrawable(R.drawable.circle_orange));
            hsvQuestion.scrollTo((position*100)-position,0);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

    public class MyViewPagerAdapter extends PagerAdapter {
        private LayoutInflater layoutInflater;

        public MyViewPagerAdapter() {
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = layoutInflater.inflate(R.layout.viewpager_layout_ques, container, false);
            TextView tv=(TextView)view.findViewById(R.id.tvLstQNo);
            ListView lv=(ListView)view.findViewById(R.id.lvQA);
            ArrayAdapter adapter=new ArrayAdapter(getApplicationContext(),R.layout.listrow_answer,R.id.textRow,arrAns);
            lv.setAdapter(adapter);
            tv.setText("Q"+position+".");
            container.addView(view);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    viewPager.setCurrentItem(viewPager.getCurrentItem()+1);
                }
            });

            return view;
        }

        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View view = (View) object;
            container.removeView(view);
        }
    }
}
