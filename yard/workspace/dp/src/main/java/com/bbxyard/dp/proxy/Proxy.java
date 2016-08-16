package com.bbxyard.dp.proxy;

/**
 * Created by bbxyard on 16-8-16.
 */
public class Proxy {
    public static void main(String[] args) {
        // 用户只需要认识干活代理即可，无从所知幕后工作者
        Subject subject = new ProxySubject();
        subject.request();
        System.out.println("Proxy.main.done!!");
    }
}


interface Subject {
    void request();
}


class ProxySubject implements Subject {
    public ProxySubject() {
        this.realSubject = new RealSubject(); // 让客户端不知道，谁是干活的.
    }
    public void request() {
        System.out.println("ProxySubject.request | 吾不做事，worker出马");
        realSubject.request();
        System.out.println("ProxySubject.request | worker代劳完毕!");
    }

    // 隐藏细节，直接为内部私有
    private class RealSubject implements Subject {
        public void request() {
            System.out.println("==> RealSubject.request | begin");
            System.out.println("==> RealSubject.request | do sth...");
            System.out.println("==> RealSubject.request | done");
        }
    }

    private Subject realSubject;
}
