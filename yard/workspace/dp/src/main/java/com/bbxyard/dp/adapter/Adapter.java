package com.bbxyard.dp.adapter;

/**
 * Created by bbxyard on 16-8-16.
 */
public class Adapter {
    public static void main(String[] args) {
        Adaptee adaptee = new Adaptee();
        Target  target1 = new AdapterByObject(adaptee);
        Target  target2 = new AdapterByClass();

        // 请求，注意this指针
        target1.request();
        target2.request();

        System.out.println("Adaptee.specificRequest.done!!");
    }
}


/**
 * 被适配者
 */
class Adaptee {
    public void specificRequest() {
        System.out.println("Adaptee.specificRequest|THIS-this=" + this.toString());
    }
}

/**
 * 目标接口
 */
interface Target {
    void request();
}


class AdapterByClass extends Adaptee implements Target {
    public void request() {
        System.out.println("AdapterByClass. request|SAME-this=" + this.toString());
        super.specificRequest();    // 调用基类接口
    }
}

class AdapterByObject implements Target {
    public AdapterByObject(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    public void request() {
        System.out.println("AdapterByObject.request|DIFF-this=" + this.toString());
        adaptee.specificRequest();  // 调用对象方法
    }
    private Adaptee adaptee;
}