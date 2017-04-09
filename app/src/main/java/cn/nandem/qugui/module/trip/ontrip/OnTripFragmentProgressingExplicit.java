package cn.nandem.qugui.module.trip.ontrip;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import cn.nandem.qugui.R;
import cn.nandem.qugui.module.base.BaseBackFragment;
import cn.nandem.qugui.module.trip.busevent.PhotoCapturedEvent;
import org.greenrobot.eventbus.EventBus;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Nandem on 2017-04-07.
 */
public class OnTripFragmentProgressingExplicit extends BaseBackFragment implements View.OnClickListener
{
    private Toolbar mToolbar;
    private int titleId;

    @BindView(R.id.trip_on_trip_btn_take_photo)
    Button takePhoto;
    @BindView(R.id.trip_on_trip_btn_select_photo)
    Button selectPhoto;
    @BindView(R.id.trip_on_trip_iv_preview)
    ImageView preview;

    private final int REQUEST_CROP_PHOTO = 1;
    private final int REQUEST_SELECT_PHOTO = 2;
    private final int REQUEST_TAKE_PHOTO = 3;
    private String mCurrentPhotoPath;
    private File mSavePhotoFile;

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
        takePhoto.setOnClickListener(this);
        selectPhoto.setOnClickListener(this);
    }

    @Override
    protected void onEnterAnimationEnd(Bundle savedInstanceState)
    {
        initDelayView();
    }

    private void initDelayView()
    {
    }

    @Override
    public void onClick(View v)
    {
        int btnId = v.getId();
        switch(btnId)
        {
            case R.id.trip_on_trip_btn_take_photo:
                mSavePhotoFile = createImageFile();
                startCamera();
                break;
            case R.id.trip_on_trip_btn_select_photo:
                mSavePhotoFile = createImageFile();
                selectPhoto();
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK)
        {
            switch(requestCode)
            {
                case REQUEST_TAKE_PHOTO://拍照
                    preview.setImageURI(Uri.fromFile(mSavePhotoFile));
                    Toast.makeText(_mActivity, "1:" + titleId, Toast.LENGTH_LONG).show();
                    EventBus.getDefault().post(new PhotoCapturedEvent(Uri.fromFile(mSavePhotoFile), titleId));
                    galleryAddPic();
                    break;
                case REQUEST_SELECT_PHOTO://选择图片
                    preview.setImageURI(data.getData());
                    EventBus.getDefault().post(new PhotoCapturedEvent(data.getData(), titleId));
                    galleryAddPic();
                    break;
                default:
                    break;
            }
        }
    }

    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");

    /**
     * 创建保存得到的图片的文件
     *
     * @return 若sd卡存在则返回文件引用，否则返回null
     */
    private File createImageFile()
    {
        if(checkSDCardAvailable())
        {
            File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "QuGui");
            if(!mediaStorageDir.exists())
            {
                if(!mediaStorageDir.mkdirs())
                {
                    return null;
                }
            }
            String timeStamp = dateFormat.format(new Date());
            String imageFileName = "QuGui_" + getContext().getString(titleId) + "_" + timeStamp;
            String suffix = ".jpg";
            File image = new File(mediaStorageDir + File.separator + imageFileName + suffix);
            mCurrentPhotoPath = image.getAbsolutePath();
            return image;
        }
        return null;
    }

    private boolean checkSDCardAvailable()
    {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 启动拍照
     */
    private void startCamera()
    {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(takePictureIntent.resolveActivity(_mActivity.getPackageManager()) != null)
        {
            if(mSavePhotoFile != null)
            {
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mSavePhotoFile));//设置文件保存的URI
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
            }
        }
    }

    /**
     * 从图库选择照片
     */
    private void selectPhoto()
    {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, REQUEST_SELECT_PHOTO);
    }

    /**
     * 触发系统的media scanner来把图片加入Media Provider's database
     */
    private void galleryAddPic()
    {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(mCurrentPhotoPath);//
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);  //设置URI
        _mActivity.sendBroadcast(mediaScanIntent);  //发送广播
    }
}
