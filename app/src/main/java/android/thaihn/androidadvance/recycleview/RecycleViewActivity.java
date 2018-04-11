package android.thaihn.androidadvance.recycleview;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.thaihn.androidadvance.BaseActivity;
import android.thaihn.androidadvance.R;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;

public class RecycleViewActivity extends BaseActivity {

    private ContactAdapter mContactAdapter;

    private SwipeRefreshLayout mSwiperRefesh;
    private RecyclerView mRecycle;
    private Toolbar mToolbar;

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
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Recycle View");


        mSwiperRefesh.setColorSchemeResources(
                R.color.md_red_700,
                R.color.md_purple_700,
                R.color.md_teal_700,
                R.color.md_lime_700);
        mSwiperRefesh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwiperRefesh.setRefreshing(true);
                mContactAdapter.setmListContact(makeListContact(30));
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
        mContactAdapter = new ContactAdapter(context, makeListContact(20));
        mRecycle.setAdapter(mContactAdapter);
        mContactAdapter.notifyDataSetChanged();

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
