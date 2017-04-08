package cn.nandem.qugui.module.settting;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.nandem.qugui.R;
import cn.nandem.qugui.model.Progress;
import cn.nandem.qugui.module.base.BaseMainFragment;
import cn.nandem.qugui.module.trip.ontrip.OnTripFragmentProgressingExplicit;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Nandem on 2017-04-03.
 */

public class SettingFragment extends BaseMainFragment
{
    private Toolbar mToolbar;
    @BindView(R.id.button2)
    Button button;

    public static SettingFragment newInstance()
    {
        return new SettingFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.settting_fragment, container, false);

        ButterKnife.bind(this, view);

        initView(view);

        return view;
    }

    public void test(View view)
    {

    }

    @Override
    protected FragmentAnimator onCreateFragmentAnimator()
    {
        return new DefaultNoAnimator();
    }

    private void initView(View view)
    {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);

        mToolbar.setTitle("设置");
        initToolbarNav(mToolbar, true);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                start(OnTripFragmentProgressingExplicit.newInstance(1));
            }
        });
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