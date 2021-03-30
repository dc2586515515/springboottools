package com.dc.spring.demo.quote;

import com.dc.spring.demo.vo.User;

import java.lang.ref.PhantomReference;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @Description
 * @Author DC
 * @Date 2021-03-30
 */
public class Quote {
    public static void main(String[] args) {
        // 1. 强引用  只要引用存在，垃圾回收器永远不会回收
        // Object obj = new Object();
        // User user = new User();
        // 可直接通过obj取得对应的对象 如 obj.equels(new Object()); 而这样 obj 对象对后面 new Object 的一个强
        // 引用，只有当 obj 这个引用被释放之后，对象才会被释放掉，这也是我们经常所用到的编码形式。

        // 2. 软引用 非必须引用，内存溢出之前进行回收，可以通过以下代码实现
        // Object obj = new Object();
        // SoftReference<Object> sf = new SoftReference<Object>(obj);
        // obj = null;
        // sf.get();//有时候会返回null
        // 这时候sf是对obj的一个软引用，通过sf.get()方法可以取到这个对象，当然，当这个对象被标记为需要回收的对象
        // 时，则返回null； 软引用主要用户实现类似缓存的功能，在内存足够的情况下直接通过软引用取值，无需从繁忙的
        // 真实来源查询数据，提升速度；当内存不足时，自动删除这部分缓存数据，从真正的来源查询这些数据。


        // 3. 弱引用 第二次垃圾回收时回收，可以通过如下代码实现
        // Object obj = new Object();
        // WeakReference<Object> wf = new WeakReference<Object>(obj);
        // obj = null;
        // wf.get();//有时候会返回null
        // wf.isEnqueued();//返回是否被垃圾回收器标记为即将回收的垃圾
        //弱引用是在第二次垃圾回收时回收，短时间内通过弱引用取对应的数据，可以取到，当执行过第二次垃圾回收时，
        // 将返回null。弱引用主要用于监控对象是否已经被垃圾回收器标记为即将回收的垃圾，可以通过弱引用的
        // isEnQueued 方法返回对象是否被垃圾回收器标记。
        // ThreadLocal 中有使用到弱引用，
     /*   public class ThreadLocal<T> {
            static class ThreadLocalMap {
                static class Entry extends WeakReference<ThreadLocal<?>> {
                    *//** The value associated with this ThreadLocal. *//*
                    Object value;

                    Entry(ThreadLocal<?> k, Object v) {
                        super(k);
                        value = v;
                    }
                } //....
            }//.....
        }*/


        // 4. 虚引用 垃圾回收时回收，无法通过引用取到对象值，可以通过如下代码实现、
        // Object obj = new Object();
        // PhantomReference<Object> pf = new PhantomReference<Object>(obj);
        // obj = null;
        // pf.get();//永远返回null
        // 虚引用是每次垃圾回收的时候都会被回收，通过虚引用的get方法永远获取到的数据为null，因此也被成为幽灵引
        // 用。虚引用主要用于检测对象是否已经从内存中删除。
    }
}
