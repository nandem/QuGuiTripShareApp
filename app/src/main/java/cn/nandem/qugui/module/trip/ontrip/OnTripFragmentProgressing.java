package cn.nandem.qugui.module.trip.ontrip;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindViews;
import butterknife.ButterKnife;
import cn.nandem.qugui.R;
import cn.nandem.qugui.module.base.BaseMainFragment;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

import java.util.List;

/**
 * @author Nandem on 2017-04-04.
 */

public class OnTripFragmentProgressing extends BaseMainFragment
{
    @BindViews({R.id.trip_on_trip_btn_1st_phase, R.id.trip_on_trip_btn_2nd_phase, R.id.trip_on_trip_btn_3rd_phase, R.id.trip_on_trip_btn_4th_phase, R.id.trip_on_trip_btn_5th_phase, R.id.trip_on_trip_btn_6th_phase})
    List<Button> buttonList;
//    @BindView(R.id.trip_on_trip_btn_1st_phase)
//    Button firstPhase;
    private View globalView;

    public static OnTripFragmentProgressing newInstance()
    {
        return new OnTripFragmentProgressing();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.trip_fragment_ontrip_progressing, container, false);

        ButterKnife.bind(this, view);
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
        for(int i = 0; i < 1; i++)
        {
            buttonList.get(i).setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {

                    /*/
                    Snackbar.make(v, "yeah", Snackbar.LENGTH_LONG)
                            .setActionTextColor(Color.YELLOW)
                            .setAction("去看看", new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    Toast.makeText(_mActivity, "这有点 吊啊", Toast.LENGTH_LONG).show();
                                }
                            }).show();

                    //*/
                    start(OnTripFragmentProgressingExplicit.newInstance(1));
                }
            });
        }
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data)
    {
        if (requestCode == 0 && resultCode == 1 && data != null) {
            Toast.makeText(_mActivity, "修改标题成功!", Toast.LENGTH_SHORT).show();
        }
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