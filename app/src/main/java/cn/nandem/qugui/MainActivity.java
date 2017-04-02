package cn.nandem.qugui;

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
import cn.nandem.qugui.module.home.HomeFragment;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;

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
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
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
//                        Toast.makeText(MainActivity.this, "看好了啊，准备登录了！", Toast.LENGTH_LONG).show();
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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if(id == R.id.nav_camera)
        {
            Toast.makeText(this, "nav_camera", Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.nav_gallery)
        {
            Toast.makeText(this, "nav_gallery", Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.nav_slideshow)
        {
            Toast.makeText(this, "nav_slideshow", Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.nav_manage)
        {
            Toast.makeText(this, "nav_manage", Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.nav_trips)
        {
            Toast.makeText(this, "nav_send", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
