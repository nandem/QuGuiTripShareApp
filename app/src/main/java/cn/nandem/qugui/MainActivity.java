package cn.nandem.qugui;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.nandem.qugui.module.account.LoginFragment;
import cn.nandem.qugui.module.base.BaseMainFragment;
import cn.nandem.qugui.module.friends.FriendsFragment;
import cn.nandem.qugui.module.home.HomeFragment;
import cn.nandem.qugui.module.recommend.RecommendFragment;
import cn.nandem.qugui.module.settting.SettingFragment;
import cn.nandem.qugui.module.trip.MyTripsFragment;
import cn.nandem.qugui.module.trip.ontrip.OnTripFragment;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;
import org.greenrobot.eventbus.EventBus;

public class MainActivity extends SupportActivity implements NavigationView.OnNavigationItemSelectedListener, BaseMainFragment.OnFragmentOpenDrawerListener
{
    // 再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;
    private TextView mUserName;   // NavigationView上的名字
    private TextView mUserSlogan;   // NavigationView上的名字
    private ImageView mUserHeadIcon;  // NavigationView上的头像

    private DrawerLayout mDrawer;
    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        toggle.syncState();

        mNavigationView = (NavigationView) findViewById(R.id.nav_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        if(savedInstanceState == null)
        {
            loadRootFragment(R.id.fl_container, OnTripFragment.newInstance());
//            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }


        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks()
        {
            // 可以监听该Activity下的所有Fragment的18个 生命周期方法

            @Override
            public void onFragmentCreated(SupportFragment fragment, Bundle savedInstanceState)
            {
                Log.i("MainActivity", "onFragmentCreated--->" + fragment.getClass().getSimpleName());
            }
        });

        LinearLayout llNavHeader = (LinearLayout) mNavigationView.getHeaderView(0);
        mUserName = (TextView) llNavHeader.findViewById(R.id.nav_TextView_userName);
        mUserSlogan = (TextView) llNavHeader.findViewById(R.id.nav_TextView_userSlogan);
        mUserHeadIcon = (ImageView) llNavHeader.findViewById(R.id.nav_ImageView_userHeadIcon);
        llNavHeader.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                mDrawer.closeDrawer(GravityCompat.START);

                mDrawer.postDelayed(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        start(LoginFragment.newInstance());
                    }
                }, 250);
            }
        });
    }

    @Override
    public void onBackPressedSupport()
    {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if(drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            Fragment topFragment = getTopFragment();

            // 主页的Fragment
            if(topFragment instanceof BaseMainFragment)
            {
                mNavigationView.setCheckedItem(R.id.nav_view);
            }

            if(getSupportFragmentManager().getBackStackEntryCount() > 1)
            {
                pop();
            }
            else
            {
                if(System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME)
                {
                    finish();
                }
                else
                {
                    TOUCH_TIME = System.currentTimeMillis();
                    Toast.makeText(this, R.string.press_again_exit, Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    @Override
    public void onOpenDrawer()
    {
        if(!mDrawer.isDrawerOpen(GravityCompat.START))
        {
            mDrawer.openDrawer(GravityCompat.START);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        if(id == R.id.nav_current_trip)
        {
            replaceLoadRootFragment(R.id.fl_container, OnTripFragment.newInstance(), false);
        }
        else if(id == R.id.nav_home)
        {
            replaceLoadRootFragment(R.id.fl_container, HomeFragment.newInstance(), false);
        }
        else if(id == R.id.nav_recommend)
        {
            replaceLoadRootFragment(R.id.fl_container, RecommendFragment.newInstance(), false);
        }
        else if(id == R.id.nav_contact)
        {
            replaceLoadRootFragment(R.id.fl_container, FriendsFragment.newInstance(), false);
        }
        else if(id == R.id.nav_trips)
        {
            replaceLoadRootFragment(R.id.fl_container, MyTripsFragment.newInstance(), false);
        }
        else if(id == R.id.nav_setting)
        {
            replaceLoadRootFragment(R.id.fl_container, SettingFragment.newInstance(), false);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
