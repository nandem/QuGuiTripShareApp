package cn.nandem.qugui.module.trip.ontrip;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.nandem.qugui.R;
import cn.nandem.qugui.module.base.BaseBackFragment;

/**
 * @author Nandem on 2017-04-07.
 */
public class OnTripFragmentProgressingExplicit extends BaseBackFragment
{


    private Toolbar mToolbar;
    private TextView mTvContent;
    private FloatingActionButton mFab;
    private String[] mTitles = new String[]{"一", "二", "三", "四", "五", "六"};
    private int index;

    public static OnTripFragmentProgressingExplicit newInstance(int index)
    {
        OnTripFragmentProgressingExplicit fragment = new OnTripFragmentProgressingExplicit();
        Bundle bundle = new Bundle();
        bundle.putInt("index", index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        if(bundle != null)
        {
            this.index = bundle.getInt("index");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.trip_fragment_ontrip_progressing_explicit, container, false);
        initView(view);
        return view;
    }

    private void initView(View view)
    {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle(mTitles[this.index]);

        initToolbarNav(mToolbar);
    }

    /**
     * 这里演示:
     * 比较复杂的Fragment页面会在第一次start时,导致动画卡顿
     * Fragmentation提供了onEnterAnimationEnd()方法,该方法会在 入栈动画 结束时回调
     * 所以在onCreateView进行一些简单的View初始化(比如 toolbar设置标题,返回按钮; 显示加载数据的进度条等),
     * 然后在onEnterAnimationEnd()方法里进行 复杂的耗时的初始化 (比如FragmentPagerAdapter的初始化 加载数据等)
     */
    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState)
    {
        initDelayView();
    }

    private void initDelayView()
    {
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data)
    {
        super.onFragmentResult(requestCode, resultCode, data);
    }
}
