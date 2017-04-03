package cn.nandem.qugui.module.account;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import cn.nandem.qugui.R;
import cn.nandem.qugui.module.base.BaseBackFragment;

/**
 * @author Nandem on 2017-04-02.
 */

public class LoginFragment extends BaseBackFragment
{
    private Toolbar mToolbar;
    private TextView login_fragment_registerLabel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.account_fragment_login, container, false);

        initView(view);

        return view;
    }

    private void initView(View view)
    {
        mToolbar = (Toolbar) view.findViewById(R.id.toolbar);
        mToolbar.setTitle("登录");
        initToolbarNav(mToolbar);

        login_fragment_registerLabel = (TextView) view.findViewById(R.id.login_fragment_registerLabel);
        login_fragment_registerLabel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                start(RegisterFragment.newInstance());
            }
        });
    }

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }
}
