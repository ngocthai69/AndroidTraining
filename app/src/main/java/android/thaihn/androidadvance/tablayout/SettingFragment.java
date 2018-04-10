package android.thaihn.androidadvance.tablayout;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.thaihn.androidadvance.BaseFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.thaihn.androidadvance.R;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends BaseFragment {

    private int mPage;
    private String mTitle;
    private TextView mText;

    public SettingFragment() {
        // Required empty public constructor
    }

    /**
     * Create only one instance of fragment
     *
     * @param page
     * @param title
     * @return
     */
    public static SettingFragment newInstance(int page, String title) {
        SettingFragment settingFragment = new SettingFragment();
        Bundle args = new Bundle();
        args.putInt("page", page);
        args.putString("title", title);
        settingFragment.setArguments(args);
        return settingFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt("page", 0);
        mTitle = getArguments().getString("title");
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void initVariables(Bundle saveInstanceState, View rootView) {
        mText = (TextView) rootView.findViewById(R.id.text);
    }

    @Override
    protected void initData(Bundle saveInstanceState) {
        mText.setText("Page " + mPage + " ----- Title" + mTitle);
    }

}
