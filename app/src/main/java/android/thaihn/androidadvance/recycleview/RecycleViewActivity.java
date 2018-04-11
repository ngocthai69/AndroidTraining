package android.thaihn.androidadvance.recycleview;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.thaihn.androidadvance.BaseActivity;
import android.thaihn.androidadvance.R;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class RecycleViewActivity extends BaseActivity implements ContactAdapter.IClickListener, ILoadMore {

    public static final String TAG = RecycleViewActivity.class.getSimpleName();

    private ContactAdapter mContactAdapter;
    protected Handler handler;

    private SwipeRefreshLayout mSwiperRefesh;
    private RecyclerView mRecycle;
    private Toolbar mToolbar;

    public static int currentItem = 20;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_recycle_view;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        mRecycle = findViewById(R.id.recycle_contact);
        mSwiperRefesh = findViewById(R.id.swipe);
        mToolbar = findViewById(R.id.toolbar);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        if (mToolbar != null) {
            setSupportActionBar(mToolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Recycle View");
        }

        handler = new Handler();

        mSwiperRefesh.setColorSchemeResources(
                R.color.md_red_700,
                R.color.md_purple_700,
                R.color.md_teal_700,
                R.color.md_lime_700);
        mSwiperRefesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwiperRefesh.setRefreshing(true);
                mContactAdapter.setListContact(makeListContact(30));
                mContactAdapter.notifyDataSetChanged();
                Toast.makeText(RecycleViewActivity.this, "Loaded", Toast.LENGTH_SHORT).show();
                mSwiperRefesh.setRefreshing(false);
            }
        });

        createRecycleView(this);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
                break;
            }
        }
        return super.onOptionsItemSelected(item);

    }

    /**
     * Create RecycleView
     *
     * @param context
     */
    private void createRecycleView(Context context) {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                context,
                LinearLayoutManager.VERTICAL,
                false
        );
        mRecycle.setHasFixedSize(true);
        mRecycle.setLayoutManager(linearLayoutManager);
        mRecycle.setItemAnimator(new DefaultItemAnimator());
        // create adapter
        mContactAdapter = new ContactAdapter(context, makeListContact(currentItem), mRecycle);
        mContactAdapter.setListener(this);
        mContactAdapter.setLoadMore(this);
        mRecycle.setAdapter(mContactAdapter);
        mContactAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(ContactObject contactObject) {
        // click on item
    }

    @Override
    public void onLoadMore() {

        Log.i(TAG, "onLoadMore: ");
        mContactAdapter.getListContact().add(null);
        mContactAdapter.notifyItemInserted(mContactAdapter.getItemCount() - 1);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //   remove progress item
                mContactAdapter.getListContact().remove(mContactAdapter.getItemCount() - 1);
                mContactAdapter.notifyItemRemoved(mContactAdapter.getItemCount());

                int start = currentItem;
                currentItem += 20;
                int end = currentItem;
                for (int i = start; i <= end; i++) {
                    mContactAdapter.getListContact().add(
                            new ContactObject("Thaihn " + i, "Ngocthaihn2@gmail.com " + i));
                    mContactAdapter.notifyItemInserted(mContactAdapter.getListContact().size());
                }
                mContactAdapter.setLoaded();
                //or you can add all at once but do not forget to call mAdapter.notifyDataSetChanged();
            }
        }, 2000);
    }

    /**
     * Create default value
     *
     * @param i
     * @return
     */
    private ArrayList<ContactObject> makeListContact(int i) {
        ArrayList<ContactObject> ls = new ArrayList<>();
        for (int j = 0; j <= i; j++) {
            ls.add(new ContactObject("Thaihn " + j, "Ngocthaihn2@gmail.com " + j));
        }
        return ls;
    }
}
