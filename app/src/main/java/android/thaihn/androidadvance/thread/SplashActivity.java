package android.thaihn.androidadvance.thread;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.thaihn.androidadvance.BaseActivity;
import android.thaihn.androidadvance.MainActivity;
import android.thaihn.androidadvance.R;

public class SplashActivity extends BaseActivity {

    private int mTimeOut = 3000;
    private Handler mHandler;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        // 1. new Thread
//        splashThread.start();

        // 2. Create by AsyncTask
        new SplashAsync().execute();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mHandler = new Handler();
//        splash();

    }

    /**
     * Create screen with wait 3s and then goto MainActivity
     */
    private void splash() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // start new activity if wait 3s done
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                // finish activity
                finish();
            }
        }, mTimeOut);
    }


    /**
     * Create new thread when sleep system 3s
     */
    private Thread splashThread = new Thread() {
        public void run() {
            try {
                sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    /**
     * Create AsyncTask by download another data, etc...
     */
    private class SplashAsync extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        mHandler.removeCallbacksAndMessages(null);
    }

}
