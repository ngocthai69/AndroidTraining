package android.thaihn.androidadvance.tablayout;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.thaihn.androidadvance.BaseActivity;
import android.thaihn.androidadvance.R;
import android.view.MenuItem;

public class TabLayoutActivity extends BaseActivity {

    private Toolbar mToolbar;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private CircleIndicator mIndicator;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_tab_layout;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        mToolbar = findViewById(R.id.toolbar);
        mTabLayout = findViewById(R.id.tabs);
        mViewPager = findViewById(R.id.viewpager);
        mIndicator = findViewById(R.id.indicators);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        // setup toolbar
        setSupportActionBar(mToolbar);
        getSupportActionBar().setTitle("TabLayout ViewPager");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // setup tablayout
        setupViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
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
     * Setup ViewPager
     */
    private void setupViewPager() {
        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mIndicator.setViewPager(mViewPager);
        mViewPager.setCurrentItem(0);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);

    }
}
