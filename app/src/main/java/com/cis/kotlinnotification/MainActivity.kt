package com.cis.kotlinnotification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.NotificationCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener { view ->
            var builder = getNotificationBuilder("channel1", "첫 번째 채널")
            builder.setTicker("Ticker") // 4.0 이하만 나온다.
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
            var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder.setLargeIcon(bitmap)
            builder.setNumber(100) // 4.0 이하만 나온다.
            builder.setAutoCancel(true) // true : 사용자가 메시지를 터치하면 메시지가 사라진다.
            builder.setContentTitle("content title")
            builder.setContentText("content text")

            var notification = builder.build()

            var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mng.notify(10, notification) // id : 알림 메시지를 구분하기 위한 id. id 값이 달라야 따로 따로 뜬다.
        }

        btn2.setOnClickListener { view ->
            var builder = getNotificationBuilder("channel1", "첫 번째 채널")
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
            var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder.setLargeIcon(bitmap)
            builder.setAutoCancel(true)
            builder.setContentTitle("content title 2")
            builder.setContentText("content text 2")

            var notification = builder.build()

            var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mng.notify(20, notification)
        }

        btn3.setOnClickListener { view ->
            var builder = getNotificationBuilder("channel2", "두 번째 채널")
            builder.setSmallIcon(android.R.drawable.ic_dialog_info)
            var bitmap = BitmapFactory.decodeResource(resources, R.mipmap.ic_launcher)
            builder.setLargeIcon(bitmap)
            builder.setAutoCancel(true)
            builder.setContentTitle("content title 3")
            builder.setContentText("content text 3")

            var notification = builder.build()

            var mng = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            mng.notify(30, notification)
        }
    }

    fun getNotificationBuilder(id:String, name:String) : NotificationCompat.Builder {
        var builder: NotificationCompat.Builder? = null

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){ // 8.0 오레오버전 이상인 경우 채널을 지정해주도록 한다.
            var manager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            var channel = NotificationChannel(id, name, NotificationManager.IMPORTANCE_HIGH)
            channel.enableLights(true)
            channel.lightColor = Color.RED
            channel.enableVibration(true)
            manager.createNotificationChannel(channel)

            builder = NotificationCompat.Builder(this, id)
        } else {
            builder = NotificationCompat.Builder(this)
        }

        return builder
    }
}
