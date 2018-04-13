package android.thaihn.androidadvance.tablayout;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.thaihn.androidadvance.BaseFragment;
import android.thaihn.androidadvance.R;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment implements View.OnClickListener {

    public static final String TAG = HomeFragment.class.getSimpleName();

    private int mPage;
    private String mTitle;
    private Bitmap mBitmap;

    private Button mButtonLoadImage;
    private ImageView mImagePreview;
    private ProgressBar mProgressbar;

    public HomeFragment() {
        // Required empty public constructor
    }

    /**
     * Create only one instance of fragment
     *
     * @param page
     * @param title
     * @return
     */
    public static HomeFragment newInstance(int page, String title) {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt("page", 0);
        mTitle = getArguments().getString("title");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState, View rootView) {
        mButtonLoadImage = rootView.findViewById(R.id.button_load_image);
        mImagePreview = rootView.findViewById(R.id.image_home);
        mProgressbar = rootView.findViewById(R.id.progressbar_load_image);

        if (saveInstanceState != null) {
            boolean load = saveInstanceState.getBoolean("load");
            Log.i(TAG, "onViewStateRestored: load " + load);

            if (load) {
                new LoadImage().execute(
                        "http://statis.infogame.vn/images/upload/2016/06/04/71_luffy.jpg?w=680");
            }
        }
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        mButtonLoadImage.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_load_image: {
                new LoadImage().execute(
                        "http://statis.infogame.vn/images/upload/2016/06/04/71_luffy.jpg?w=680");
                break;
            }
        }
    }

    /**
     * Load image from url to image view
     */
    private class LoadImage extends AsyncTask<String, String, Bitmap> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected Bitmap doInBackground(String... strings) {
            return getBitmapFromURL(strings[0]);
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            mProgressbar.setVisibility(View.GONE);
            // show error if async task stop
        }


        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            mProgressbar.setVisibility(View.GONE);
            mImagePreview.setImageBitmap(bitmap);
            mBitmap = bitmap;
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        boolean isLoad = false;
        Log.i(TAG, "onSaveInstanceState: bitmap " + mBitmap);
        if (mBitmap == null) {
            isLoad = false;
        } else {
            isLoad = true;
        }

        if (outState != null) {
            outState.putBoolean("load", isLoad);
        }


    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            boolean load = savedInstanceState.getBoolean("load");
            Log.i(TAG, "onViewStateRestored: load " + load);

            if (load) {
                new LoadImage().execute(
                        "http://statis.infogame.vn/images/upload/2016/06/04/71_luffy.jpg?w=680");
            }
        }
    }

    /**
     * Load image from url
     *
     * @param src
     * @return
     */
    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            // Log exception
            return null;
        }
    }

}
