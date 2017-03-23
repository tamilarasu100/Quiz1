package com.connective.links.quiz.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.connective.links.quiz.R;
import com.connective.links.sidemenu.interfaces.ScreenShotable;

import java.util.ArrayList;
import java.util.Random;

public class ProfileFrag extends Fragment implements ScreenShotable {
    private ArrayList<String> stringArrayList;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private Toolbar supportActionBar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment=inflater.inflate(R.layout.fragment_profile, container, false);
        supportActionBar = (Toolbar)fragment. findViewById(R.id.toolbar);
        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.setSupportActionBar(supportActionBar);
        activity.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout)fragment. findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle("title");

        recyclerView = (RecyclerView)fragment. findViewById(R.id.recycler);
        recyclerView.setHasFixedSize(true);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
//        recyclerView.setLayoutManager(layoutManager);
//
//        setData(); //adding data to array list
//        adapter = new RecyclerAdapter(stringArrayList);
//        recyclerView.setAdapter(adapter);
        return fragment;
    }
    private void setData() {
        stringArrayList = new ArrayList<>();

        for (int i = 0; i < 0; i++) {
            stringArrayList.add("Item " + (i + 1));
        }
    }
    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

        public RecyclerAdapter(ArrayList<String> arrTitle) {
        }

        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.listrow_quiz_result, parent, false);
            ViewHolder vh=new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, final int position) {
            holder.mTitle.setText(stringArrayList.get(position));

            int[] androidColors = getResources().getIntArray(R.array.androidcolors);
            int randomAndroidColor = androidColors[new Random().nextInt(androidColors.length)];
            holder.layout.setBackgroundColor(randomAndroidColor);
            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }

        @Override
        public int getItemCount() {
            return stringArrayList.size();
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTitle,mMarks,mDuration;
            LinearLayout layout;
            public ViewHolder(View v) {
                super(v);
                mTitle=(TextView)v.findViewById(R.id.tvLstTitle);
                mMarks=(TextView)v.findViewById(R.id.tvLstMarks);
                layout=(LinearLayout)v.findViewById(R.id.layout_row_marks);
            }
        }
    }
}
