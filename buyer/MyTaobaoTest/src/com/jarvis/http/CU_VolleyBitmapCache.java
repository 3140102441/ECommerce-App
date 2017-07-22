package com.jarvis.http;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;

import com.android.volley.toolbox.ImageLoader.ImageCache;

@SuppressLint("NewApi")
public class CU_VolleyBitmapCache implements ImageCache {
	private android.support.v4.util.LruCache<String, Bitmap> mCache;

	public CU_VolleyBitmapCache() {
		int maxSize = 10 * 1024 * 1024;
		mCache = new android.support.v4.util.LruCache<String, Bitmap>(maxSize) {
			protected int sizeOf(String key, Bitmap value) {
				return value.getRowBytes() * value.getHeight();
			}

		};
	}

	@Override
	public Bitmap getBitmap(String url) {
		return mCache.get(url);
	}

	@Override
	public void putBitmap(String url, Bitmap bitmap) {
		mCache.put(url, bitmap);
	}

}
