package cl.inacap.pnk.io;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton instanciaVollley;
    private RequestQueue request;
    private ImageLoader imageLoader;
    private static Context context;

    private VolleySingleton(Context c) {
        context=c;
        request= getRequestQueue();

        imageLoader = new ImageLoader(request, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap>
                cache = new LruCache<String, Bitmap>(20);

            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });
    }

    public static synchronized VolleySingleton getInstance(Context context){
        if(instanciaVollley==null){
            instanciaVollley=new VolleySingleton(context);
        }
        return instanciaVollley;
    }

    public RequestQueue getRequestQueue() {
        if (request==null) {
            request= Volley.newRequestQueue(context.getApplicationContext());
        }
        return request;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }



}
