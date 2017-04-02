package cn.nandem.qugui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import cn.nandem.qugui.module.base.BaseMainFragment;
import cn.nandem.qugui.module.home.HomeFragment;
import me.yokeyword.fragmentation.SupportActivity;
import me.yokeyword.fragmentation.SupportFragment;
import me.yokeyword.fragmentation.helper.FragmentLifecycleCallbacks;

public class MainActivity extends SupportActivity implements NavigationView.OnNavigationItemSelectedListener, BaseMainFragment.OnFragmentOpenDrawerListener
{

    private DrawerLayout mDrawer;

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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            loadRootFragment(R.id.fl_container, HomeFragment.newInstance());
        }


        registerFragmentLifecycleCallbacks(new FragmentLifecycleCallbacks() {
            // 可以监听该Activity下的所有Fragment的18个 生命周期方法

            @Override
            public void onFragmentCreated(SupportFragment fragment, Bundle savedInstanceState) {
                Log.i("MainActivity", "onFragmentCreated--->" + fragment.getClass().getSimpleName());
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
            super.onBackPressed();
        }
    }

    @Override
    public void onOpenDrawer()
    {
        if (!mDrawer.isDrawerOpen(GravityCompat.START)) {
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
        else if(id == R.id.nav_share)
        {
            Toast.makeText(this, "nav_share", Toast.LENGTH_LONG).show();
        }
        else if(id == R.id.nav_send)
        {
            Toast.makeText(this, "nav_send", Toast.LENGTH_LONG).show();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
