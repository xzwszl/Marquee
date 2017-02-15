# Marquee
-----
An efficient implement of marquee, more efficient than marquee of textview,from the test, the usage of cpu is almost half of the latter.

Chinese:一种比TextView跑马灯更加高效的实现，从测试数据看，cpu使用只占TextView跑马灯一半左右～
Usage
-----
```xml
 <FrameLayout
        android:layout_width="match_parent"
        android:layout_height="180dp"
        android:layout_alignParentTop="true"
        android:layout_centerHorizontal="true">

        <com.xzl.marquee.library.MarqueeView
            android:id="@+id/marquee"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:text_color="@android:color/white"
            app:txt_spacing="15dp"
            app:text_shadowColor="@android:color/black"
            app:txt_dx="1.0"
            app:txt_dy="1.0"
            app:txt_radius="1.0"
            app:text_size="25sp"
            app:speed="40dp"
            android:layout_gravity="center_vertical"/>
    </FrameLayout>
```
Effect
-----
![Marquee](https://github.com/xzwszl/Marquee/tree/master/app/src/main/pic/demo.gif)

About Me
-----
Email Address: xzwszl@163.com

Wechat Name: 兮乐

Love Android, Love Life!
