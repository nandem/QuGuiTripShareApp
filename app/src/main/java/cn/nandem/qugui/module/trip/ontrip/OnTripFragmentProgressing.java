package cn.nandem.qugui.module.trip.ontrip;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;
import butterknife.BindViews;
import butterknife.ButterKnife;
import cn.nandem.qugui.R;
import cn.nandem.qugui.module.base.BaseMainFragment;
import cn.nandem.qugui.module.trip.busevent.ExplicitProgressingEvent;
import me.yokeyword.fragmentation.anim.DefaultNoAnimator;
import me.yokeyword.fragmentation.anim.FragmentAnimator;
import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * @author Nandem on 2017-04-04.
 */

public class OnTripFragmentProgressing extends BaseMainFragment implements View.OnClickListener
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
        for(int i = 0; i < buttonList.size(); i++)
        {
            buttonList.get(i).setOnClickListener(this);
        }
    }

    @Override
    protected void onFragmentResult(int requestCode, int resultCode, Bundle data)
    {
        if (requestCode == 0 && resultCode == 1 && data != null)
        {
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

    private int[] explictiProgressTitlesId = new int[]{R.string.trip_on_trip_explicit_1st, R.string.trip_on_trip_explicit_2nd, R.string.trip_on_trip_explicit_3rd, R.string.trip_on_trip_explicit_4th, R.string.trip_on_trip_explicit_5th, R.string.trip_on_trip_explicit_6th};
    @Override
    public void onClick(View v)
    {

        int btnId = v.getId();
        switch(btnId)
        {
            case R.id.trip_on_trip_btn_1st_phase:
                EventBus.getDefault().post(new ExplicitProgressingEvent(OnTripFragmentProgressingExplicit.newInstance(explictiProgressTitlesId[0])));
                break;
            case R.id.trip_on_trip_btn_2nd_phase:
                EventBus.getDefault().post(new ExplicitProgressingEvent(OnTripFragmentProgressingExplicit.newInstance(explictiProgressTitlesId[1])));
                break;
            case R.id.trip_on_trip_btn_3rd_phase:
                EventBus.getDefault().post(new ExplicitProgressingEvent(OnTripFragmentProgressingExplicit.newInstance(explictiProgressTitlesId[2])));
                break;
            case R.id.trip_on_trip_btn_4th_phase:
                EventBus.getDefault().post(new ExplicitProgressingEvent(OnTripFragmentProgressingExplicit.newInstance(explictiProgressTitlesId[3])));
                break;
            case R.id.trip_on_trip_btn_5th_phase:
                EventBus.getDefault().post(new ExplicitProgressingEvent(OnTripFragmentProgressingExplicit.newInstance(explictiProgressTitlesId[4])));
                break;
            case R.id.trip_on_trip_btn_6th_phase:
                EventBus.getDefault().post(new ExplicitProgressingEvent(OnTripFragmentProgressingExplicit.newInstance(explictiProgressTitlesId[5])));
                break;
            default:
                break;
        }
    }
}