package cl.inacap.pnk.io;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.HashMap;
import java.util.Map;

import androidx.core.app.NotificationCompat;
import cl.inacap.pnk.R;
import cl.inacap.pnk.ui.Inicio;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "Msg";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData()!=null){
            enviarNotificacion(remoteMessage);
        }
        if(remoteMessage.getNotification()!=null){
            Log.d(TAG,"Body notificacion: "+remoteMessage.getNotification().getBody());
            enviarNotificacion(remoteMessage);
        }
    }

    private void enviarNotificacion(RemoteMessage remoteMessage) {
        Map<String,String> data= remoteMessage.getData();
        String title= data.get("title");
        String body = data.get("body");

        NotificationManager manager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID= "pnk";

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.O){
            //Solo para android Oreo o superior
            @SuppressLint("WrongConstant") NotificationChannel channel=new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    "Mi notificacion",NotificationManager.IMPORTANCE_MAX);

            //Configuracion del canal de notificacion
            channel.setDescription("pnk channel para app");
            channel.enableLights(true);
            channel.setLightColor(Color.BLUE);
            channel.setVibrationPattern(new long[]{0,1000,500,1000});

            manager.createNotificationChannel(channel);
        }

        Intent intent=new Intent(this, Inicio.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent= PendingIntent.getActivity(this,0,intent,0);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);

        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_stat_name)
                .setTicker("Hearty465")
                .setContentTitle(title)
                .setContentText(body)
                .setVibrate(new long[]{0,1000,500,1000})
                .setContentIntent(pendingIntent)
                .setContentInfo("info");

        manager.notify(1,builder.build());
    }



    @Override
    public void onNewToken(String token) {
        Log.d(TAG,"Refresh Token "+token);
        SharedPreferences preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putString("token",token);
        editor.commit();
        FirebaseMessaging.getInstance().subscribeToTopic("sesion");
    }

}
