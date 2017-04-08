package cn.nandem.qugui.module.trip.ontrip;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import cn.nandem.qugui.R;
import cn.nandem.qugui.module.base.BaseMainFragment;
import cn.nandem.qugui.module.trip.adapter.MyFragmentPagerAdapter;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nandem on 2017-04-03.
 */

public class OnTripFragment extends BaseMainFragment
{
    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private LinearLayout mTarget;
    private Toolbar mToolbar;


    private List<Fragment> mFragments = new ArrayList<>();//fragment集合
    private List<String> mTitles = new ArrayList<>();//页卡视图集合

    public static OnTripFragment newInstance()
    {
        return new OnTripFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.trip_fragment_ontrip, container, false);

        initView(view);

        return view;
    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator()
    {
        return new DefaultNoAnimator();
    }

    private void initView(View view)
    {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
//        mToolbar.setTitle("当前旅行");
        initToolbarNav(mToolbar, true);

        mTabLayout = (TabLayout) view.findViewById(R.id.toolbar_tab);
        mViewPager = (ViewPager) view.findViewById(R.id.trip_viewpager_on_trip);
        mTarget = (LinearLayout) view.findViewById(R.id.trip_linear_layout_on_trip);

        initTabTitle();
        initFragments();
        mViewPager.setAdapter(new MyFragmentPagerAdapter(getChildFragmentManager(), mTitles, mFragments));

    }

    /**
     * 初始化tab标签
     */
    public void initTabTitle() {
        mTitles.add("1");
        mTitles.add("2");
        mTitles.add("3");
        for (int i = 0; i < mTitles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(mTitles.get(i)));
        }

        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void initFragments() {
        mFragments.add(OnTripFragmentProgressing.newInstance());
        mFragments.add(OnTripFragmentRecord.newInstance());
        mFragments.add(OnTripFragmentDetail.newInstance());
    }

    /**
     * 类似于 Activity的 onNewIntent()
     */
    @Override
    protected void onNewBundle(Bundle args)
    {
        super.onNewBundle(args);

        Toast.makeText(_mActivity, args.getString("from"), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onDestroyView()
    {
        super.onDestroyView();
    }
}
