package cn.nandem.qugui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import me.yokeyword.fragmentation.SupportActivity;

public class MainActivity extends SupportActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
