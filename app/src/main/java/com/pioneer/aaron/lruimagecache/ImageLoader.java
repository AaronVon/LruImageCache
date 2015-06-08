package com.pioneer.aaron.lruimagecache;

import android.app.Activity;
import android.app.Application;
import android.graphics.Bitmap;
import android.os.Environment;
import android.util.LruCache;
import android.widget.ImageView;

import java.util.concurrent.ExecutionException;

/**
 * Created by Aaron on 6/7/15.
 */
public class ImageLoader extends Activity{
    private LruImageCache lruImageCache = new LruImageCache();
    private ImageFileCache imageFileCache = new ImageFileCache();
    private getImageFromHTTP imageFromHTTP = new getImageFromHTTP();

    public void displayImage(String imgUrl, ImageView imageView) {
        Bitmap bitmap = null;
        bitmap = lruImageCache.getBitmapFromCache(imgUrl);
        if (null == bitmap) {
            bitmap = imageFileCache.getImage(imgUrl);
            if (null == bitmap) {
                try {
                    bitmap = (Bitmap) imageFromHTTP.execute(imgUrl).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
                if (null != bitmap) {
                    imageFileCache.saveBitmap2SD(imgUrl, bitmap);
                    lruImageCache.addBitmap2Cache(imgUrl, bitmap);
                }
            } else {
                lruImageCache.addBitmap2Cache(imgUrl, bitmap);
            }
        }

        imageView.setImageBitmap(bitmap);
    }

}
