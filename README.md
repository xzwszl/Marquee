# Marquee
-----
An efficient implement of marquee, more efficient than marquee of textview,from the test, the usage of cpu is almost half of the latter.

Chinese:一种比TextView跑马灯更加高效的实现，从测试数据看，cpu使用只占TextView跑马灯一半左右～
Usage
-----
```xml
 <FrameLayout
        android:layout_width="200dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="50dp"
        android:layout_centerHorizontal="true"
        android:background="@android:color/holo_green_dark">

        <com.xzl.marquee.library.MarqueeView
            android:id="@+id/marquee"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:text_color="@android:color/holo_red_dark"
            app:text_size="21sp"
            app:speed="60dp"/>
    </FrameLayout>
```
About Me
-----
Email Address: xzwszl@163.com

Wechat: fry_ss

Love Android, Love Life!
