package android.thaihn.androidadvance;

import android.content.Intent;
import android.os.Bundle;
import android.thaihn.androidadvance.tablayout.TabLayoutActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Button mButtonTabLayout;

    @Override
    protected int getLayoutResources() {
        return R.layout.activity_main;
    }

    @Override
    protected void initVariables(Bundle savedInstanceState) {
        mButtonTabLayout = findViewById(R.id.button_tablayout);
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        mButtonTabLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_tablayout: {
                Intent intent = new Intent(MainActivity.this, TabLayoutActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
