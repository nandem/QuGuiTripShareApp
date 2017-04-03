package cn.nandem.qugui.module.account;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import cn.nandem.qugui.R;
import cn.nandem.qugui.module.base.BaseBackFragment;

/**
 * @author Nandem on 2017-04-02.
 */

public class RegisterFragment extends BaseBackFragment
{
    private Toolbar mToolbar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.account_fragment_register, container, false);

        initView(view);

        return view;
    }

    private void initView(View view)
    {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("注册");
        initToolbarNav(mToolbar);
    }

    public static RegisterFragment newInstance() {
        return new RegisterFragment();
    }
}
