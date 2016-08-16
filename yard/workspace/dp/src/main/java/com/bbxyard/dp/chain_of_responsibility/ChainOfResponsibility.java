package com.bbxyard.dp.chain_of_responsibility;

/**
 * Created by bbxyard on 16-8-15.
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {
        int[] requests = {7, 18, 8, 15, 24, 85};
        Handler h1 = new ConcreteHandler1();
        Handler h2 = new ConcreteHandler2();
        Handler h3 = new ConcreteHandler3();
        h1.setSuccessor(h2);
        h2.setSuccessor(h3);
        for (int request: requests) {
            h1.handleRequest(request);
        }
        System.out.println("ChainOfResponsibility.main.done!!");
    }
}

interface Handler {
    void handleRequest(int request);
    void setSuccessor(Handler successor);
}

/**
 * 改进实现，采用职责链+模板方法模式
 */
class HandlerImpl implements Handler {
    public void handleRequest(int request) {
        // 如果当前对象不能处理，且后继有人，则移交下一级
        if (!canHandleRequest(request)) {
            if (successor != null) {
                successor.handleRequest(request);
            } else { // 否则为无法处理的请求.
                System.out.printf("HandlerImpl.handleRequest | 遗弃之! [WAN]该请求[%d]未被处理!!\n", request);
            }
        }
    }
    public void setSuccessor(Handler successor) {
        this.successor = successor;
    }
    public Handler getSuccessor() {
        return this.successor;
    }
    protected boolean canHandleRequest(int request) {
        return false;
    }
    private Handler successor;
}

/**
 * 基于模板方法实现
 */
class ConcreteHandler1 extends HandlerImpl {
    @Override
    protected boolean canHandleRequest(int request) {
        boolean bret = (request >= 0 && request < 10);
        if (bret) {
            System.out.printf("ConcreteHandler1.handleRequest | Request=[%d] done\n", request);
        }
        return bret;
    }
}

/**
 * 基于模板方法实现
 */
class ConcreteHandler2 extends HandlerImpl {
    @Override
    protected boolean canHandleRequest(int request) {
        boolean bret = (request >= 10 && request < 20);
        if (bret) {
            System.out.printf("ConcreteHandler2.handleRequest | Request=[%d] done\n", request);
        }
        return bret;
    }
}

/**
 * 终规终矩实现，从头实现handleRequest处理
 */
class ConcreteHandler3 extends HandlerImpl {
    @Override
    public void handleRequest(int request) {
        if (request >= 20 && request < 30) {
            System.out.printf("ConcreteHandler3.handleRequest | Request=[%d] done\n", request);
        } else if (getSuccessor() != null) {
            getSuccessor().handleRequest(request);
        } else {
            // 否则为无法处理的请求.
            System.out.printf("ConcreteHandler3.handleRequest | 最终遗弃! [WAN]该请求[%d]未被处理!!\n", request);
        }
    }
}