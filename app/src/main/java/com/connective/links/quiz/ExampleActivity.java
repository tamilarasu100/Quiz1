package com.connective.links.quiz;

import android.graphics.Matrix;
import android.support.v4.view.ScaleGestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.widget.ImageView;

import uk.co.senab.photoview.PhotoViewAttacher;

public class ExampleActivity extends AppCompatActivity {

    ScaleGestureDetector sgd;
    ImageView imageView;
    Matrix matrix;
    private PhotoViewAttacher mAttacher;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_example);
        imageView=(ImageView)findViewById(R.id.imageView);
        mAttacher = new PhotoViewAttacher(imageView);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sgd.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private  class ScaleListener extends  ScaleGestureDetector.SimpleOnScaleGestureListener{
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float SF=detector.getScaleFactor();
            SF=Math.max(0.1f,Math.min(SF,0.5f));
            matrix.setScale(SF,SF);
            imageView.setImageMatrix(matrix);
            return true;

        }
    }
}
