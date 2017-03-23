package com.connective.links.quiz.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.connective.links.quiz.QuestionActivity;
import com.connective.links.quiz.R;
import com.connective.links.sidemenu.interfaces.ScreenShotable;

public class QuizList extends Fragment implements ScreenShotable {
    RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    String arrTitle[]={"Test1","Test2","Test3","Test4","Test5"};
    String arrMarks[]={"100","100","50","100","100"};
    String arrDuration[]={"90 mins","90 mins","60 mins","90 mins","90 mins"};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View fragment=inflater.inflate(R.layout.fragment_quiz_list, container, false);
        recyclerView=(RecyclerView)fragment.findViewById(R.id.quiz_recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new RecyclerAdapter(arrTitle);
        recyclerView.setAdapter(mAdapter);


        return fragment;
    }

    @Override
    public void takeScreenShot() {

    }

    @Override
    public Bitmap getBitmap() {
        return null;
    }

    public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{

        public RecyclerAdapter(String[] arrTitle) {
        }

        @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.listrow_quiz_list, parent, false);
            ViewHolder vh=new ViewHolder(v);
            return vh;
        }

        @Override
        public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, final int position) {
            holder.mTitle.setText(arrTitle[position]);
            holder.mMarks.setText(arrMarks[position]);
            holder.mDuration.setText(arrDuration[position]);

            holder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(getActivity(), ""+position, Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(getActivity(), QuestionActivity.class);
                    i.putExtra("duration",100000);
                    startActivity(i);
                }
            });
        }

        @Override
        public int getItemCount() {
            return arrTitle.length;
        }

        public  class ViewHolder extends RecyclerView.ViewHolder {
            // each data item is just a string in this case
            public TextView mTitle,mMarks,mDuration;
            LinearLayout layout;
            public ViewHolder(View v) {
                super(v);
                mTitle=(TextView)v.findViewById(R.id.tvLstTitle);
                mMarks=(TextView)v.findViewById(R.id.tvLstMarks);
                mDuration=(TextView)v.findViewById(R.id.tvLstDuaration);
                layout=(LinearLayout)v.findViewById(R.id.layout_quiz_list);
            }
        }
    }
}
