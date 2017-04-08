package cn.nandem.qugui.module.trip.ontrip;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.nandem.qugui.R;
import cn.nandem.qugui.module.base.BaseBackFragment;
import cn.nandem.qugui.module.trip.busevent.PhotoCapturedEvent;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.File;

/**
 * @author Nandem on 2017-04-07.
 */
public class OnTripFragmentProgressingExplicit extends BaseBackFragment implements View.OnClickListener
{
    private Toolbar mToolbar;
    private TextView mTvContent;
    private FloatingActionButton mFab;
    private int titleId;

    @BindView(R.id.trip_on_trip_btn_take_photo)
    Button takePhoto;
    @BindView(R.id.trip_on_trip_btn_select_photo)
    Button selectPhoto;
    @BindView(R.id.trip_on_trip_iv_preview)
    ImageView preview;

    public static OnTripFragmentProgressingExplicit newInstance(int titleId)
    {
        OnTripFragmentProgressingExplicit fragment = new OnTripFragmentProgressingExplicit();
        Bundle bundle = new Bundle();
        bundle.putInt("titleId", titleId);
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
            this.titleId = bundle.getInt("titleId");
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
        mToolbar.setTitle(getContext().getString(titleId));
        initToolbarNav(mToolbar);
        ButterKnife.bind(this, view);
        EventBus.getDefault().register(this);

        takePhoto.setOnClickListener(this);
        selectPhoto.setOnClickListener(this);
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

    @Subscribe
    public void preview(PhotoCapturedEvent photoCapturedEvent
    )
    {
        preview.setImageURI(Uri.fromFile(fileName));
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data)
    {
        super.onFragmentResult(requestCode, resultCode, data);
        Toast.makeText(getContext(), "ok", Toast.LENGTH_LONG).show();
        if(requestCode == 007 && resultCode == RESULT_OK)
        {
            preview.setImageURI(Uri.fromFile(fileName));
        }
    }

    /*
   * Request the media scanner to scan a file and add it to the media database.
    */
    private void scanFile() {
        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(Uri.fromFile(fileName));
        _mActivity.sendBroadcast(intent);
    }

    File fileName;
    @Override
    public void onClick(View v)
    {
        int btnId = v.getId();
        switch(btnId)
        {
            case R.id.trip_on_trip_btn_take_photo:
                //1、调用相机
                File filePath = new File(Environment.getExternalStorageDirectory(), "QuGui");
                if(!filePath.exists())
                {
                    filePath.mkdirs();
                }
                fileName = new File(filePath, System.currentTimeMillis() + ".jpg");
                try
                {
                    if(!fileName.exists())
                    {
                        fileName.createNewFile();
                    }
                }
                catch(Exception e)
                {
                    e.printStackTrace();
                }

                // intent用来启动系统自带的Camera
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                // 将系统Camera的拍摄结果写入到文件
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(fileName));
                // 启动intent对应的Activity，返回默认消息
                startActivityForResult(intent, 007);
                scanFile();

                break;
            case R.id.trip_on_trip_btn_select_photo:
                break;
            default:
                break;
        }
    }
}
