package com.pioneer.aaron.lruimagecache.AsyncImageLoader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.ref.SoftReference;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Created by Aaron on 6/9/15.
 */
public class AsyncImageLoader {
    public Map<String, SoftReference<Drawable>> imageCache = new HashMap<String, SoftReference<Drawable>>();
    private ExecutorService executorService = Executors.newFixedThreadPool(5/*poolSize*/);
    private Handler mHandler = new Handler();
    //    get SD card path
    private final String path = Environment.getExternalStorageDirectory().getPath() + "/theCache";

    public void loadImage(String imageUrl, final ImageView imageView) {
//        set default image res while loading
        if (null == imageView.getMatrix()) {
//            imageView.setImageResource(R.drawable.loading);
        }
        Drawable cacheImage = loadDrawable(imageUrl,
                new ImageCallback() {
                    @Override
                    public void imageLoaded(Drawable imageDrawable) {
                        imageView.setImageDrawable(imageDrawable);
                    }
                });
        if (null != cacheImage) {
            imageView.setImageDrawable(cacheImage);
        }
    }

    /**
     * @param imageUrl
     * @param callback call back interface
     * @return return the image res cached in RAM*/
    public Drawable loadDrawable(final String imageUrl, final ImageCallback callback) {
        if (imageCache.containsKey(imageUrl)) {
            SoftReference<Drawable> softReference = imageCache.get(imageUrl);
            if (null != softReference.get()) {
                return softReference.get();
            }
        } else if (null != useTheImage(imageUrl)) {
            return useTheImage(imageUrl);
        }
        executorService.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.length());
                    final Drawable drawable = Drawable.createFromStream(new URL(imageUrl).openStream(), imageName);
                    imageCache.put(imageUrl, new SoftReference<Drawable>(drawable));
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            callback.imageLoaded(drawable);
                        }
                    });
                    saveFile2SD(drawable, imageUrl);
                } catch (Exception e) {
                    throw new RuntimeException();
                }

            }
        });
        return null;
    }

    /**
     * Load image from web*/
    public Drawable loadImageViaHTTP(String imageUrl) {
        try {
            return Drawable.createFromStream(new URL(imageUrl).openStream(),
                    imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.length()));
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }


    /**
     * save image file to SD card
     * */
    private void saveFile2SD(Drawable drawable, String imageUrl) {
        try {
//            transcode Drawable to Bitmap
            BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
            Bitmap bitmap = bitmapDrawable.getBitmap();
//            get image name
            String imageName = imageUrl.substring(imageUrl.lastIndexOf("/") + 1,
                    imageUrl.length());
            File file = new File(path + "/image/" + imageName);
//            create image cache file
            boolean SDCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
            if (SDCardExist) {
                File theCache = new File(path);
                File imgCache = new File(path + "/image");
                Log.d("EXTERNAL_STORAGE", path);
//                check whether global cache file exists, if not, make directory
                if (!theCache.exists()) {
                    theCache.mkdir();
                    Log.d("DEBUG", "theCache dir created");
//                check whether image cache file exists, if not, make directory
                } else if (!imgCache.exists()) {
                    imgCache.mkdir();
                    Log.d("DEBUG", "imgCache created");
                }
            }
//            check whether the image file exists
            if (!file.exists()) {
                file.createNewFile();
                Log.d("DEBUG", "image file created");
            }

            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bufferedOutputStream);
            bufferedOutputStream.flush();
            bufferedOutputStream.close();
        } catch (Exception e) {
//            TODO: handle exception
        }
    }

    public interface ImageCallback {
        void imageLoaded(Drawable imageDrawable);
    }

    /**
     * use image on SD card
     * */
    public Drawable useTheImage(String imageUrl) {
        Bitmap bitmap = null;
//        get image path on SD card
        String imgSDCardPath = path + "/image/"
                + imageUrl.substring(imageUrl.lastIndexOf("/") + 1, imageUrl.length());
        File file = new File(imgSDCardPath);
//        check whether the image file exists or not
        if (!file.exists()) {
            return null;
        } else {
            bitmap = BitmapFactory.decodeFile(imgSDCardPath, null);
            if (null != bitmap || bitmap.toString().length() > 3) {
                Drawable drawable = new BitmapDrawable(bitmap);
                return drawable;
            } else {
                return null;
            }
        }
    }
}
