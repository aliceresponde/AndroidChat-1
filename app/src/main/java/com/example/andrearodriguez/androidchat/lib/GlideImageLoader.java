package com.example.andrearodriguez.androidchat.lib;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;

/**
 * Created by alice on 6/17/16.
 * Uso glide para settear una imagen desde url sobre un imageView
 */

public class GlideImageLoader implements ImageLoader {
    private RequestManager glideRequestManager;

    public GlideImageLoader(Context context) {
        glideRequestManager = Glide.with(context);
    }

    @Override
    public void load(ImageView imgAvatar, String url) {
        glideRequestManager.load(url).into(imgAvatar);
    }
}
