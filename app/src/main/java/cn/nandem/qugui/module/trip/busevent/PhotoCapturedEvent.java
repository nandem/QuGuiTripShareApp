package cn.nandem.qugui.module.trip.busevent;

import android.net.Uri;

/**
 * @author Nandem on 2017-04-09.
 */

public class PhotoCapturedEvent
{
    private Uri photoUri;
    private int uriIndex;

    public int getUriIndex()
    {
        return uriIndex;
    }

    public PhotoCapturedEvent(Uri photoUri, int uriIndex)
    {
        this.photoUri = photoUri;
        this.uriIndex = uriIndex;
    }

    public Uri getPhotoUri()
    {
        return photoUri;
    }
}
