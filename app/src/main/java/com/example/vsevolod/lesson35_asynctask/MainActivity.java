package com.example.vsevolod.lesson35_asynctask;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tvTaskResult;
    TextView tvTimeElapsed;
    Button bStartTask;
    ProgressBar mProgressBar;
    long mStartTime;
    long mEndTime;
    long mTimeGone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTimeElapsed = (TextView) findViewById(R.id.tvTimeElapsed);
        tvTaskResult = (TextView) findViewById(R.id.tvTaskResult);
        bStartTask = (Button) findViewById(R.id.bStartAsyncTask);
        mProgressBar = (ProgressBar) findViewById(R.id.progressBar);

        bStartTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyTask task = new MyTask();
                mStartTime = System.currentTimeMillis();
                task.execute();
            }
        });



    }

    public class MyTask extends AsyncTask<Integer,Integer,Long> {

        public MyTask() {
            super();
        }

        @Override
        protected void onPreExecute() {
            //[comment] Method that run in non-UI thread?? Because we cann't to set text
            //[comment] We can call from this method any UI
            tvTaskResult.setText("Начало"); // doesn't work
            super.onPreExecute();

            for (int i=0; i<1000001; i++) {

                for (int j=0; j<1000; j++){

                }
            }
        }

        @Override
        protected Long doInBackground(Integer [] params) {
            //[comment] Method that run in non-UI thread
            //[comment]We can not call from this method any UI elements
            int i;
            long tempResult;
            for (i=0; i<1000001; i++) {

                for (int j=0; j<1000; j++){

                }

                if (i%1000 == 0){
                    publishProgress(i);
                }
            }
            tempResult = i;
            return tempResult;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            //[comment] Method that run in UI thread
            //[comment] We can call from this method any UI

            super.onProgressUpdate(values);
            tvTaskResult.setText(values[0].toString());
            mProgressBar.setProgress(values[0]/10000);
        }

        @Override
        protected void onPostExecute(Long aMillisec) {
            //[comment] Method that run in UI thread
            //[comment] We can call from this method any UI
            //aMillisec it is result from doInBackGround;

            super.onPostExecute(aMillisec);
            mEndTime = System.currentTimeMillis();
            mTimeGone = mEndTime - mStartTime;

            //tvTaskResult.setText(Long.toString(mStartTime));
            tvTaskResult.setText (Long.toString(aMillisec));
            tvTimeElapsed.setText(Long.toString(mTimeGone));

        }

    }
}
