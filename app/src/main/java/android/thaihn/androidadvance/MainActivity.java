package android.thaihn.androidadvance;

import android.content.Intent;
import android.os.Bundle;
import android.thaihn.androidadvance.recycleview.RecycleViewActivity;
import android.thaihn.androidadvance.tablayout.TabLayoutActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mButtonTabLayout, mButtonRecycleView;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        mButtonTabLayout = findViewById(R.id.button_tablayout);
        mButtonRecycleView = findViewById(R.id.button_recycleview);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mButtonTabLayout.setOnClickListener(this);
        mButtonRecycleView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_tablayout: {
                Intent intent = new Intent(MainActivity.this, TabLayoutActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.button_recycleview: {
                Intent intent = new Intent(MainActivity.this, RecycleViewActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
