package com.bbxyard.dp.strategy;

/**
 * Created by bbxyard on 16-8-12.
 */
public class CashContext {
    public CashContext(String type) {
//        switch (type) {
//            case "rebate":  cs = new CashRebate(0.8f);  break;
//            case "return":  cs = new CashReturn(300, 100);  break;
//            case "normal":
//            default:
//                cs = new CashNormal();
//                break;
//        }
        if (type == "rebate") cs = new CashRebate(0.8f);
        else if (type == "return") cs = new CashReturn(300, 100);
        else cs = new CashNormal();
    }

    public float getResult(float money) {
        return cs.acceptCash(money);
    }

    private CashSuper cs;


    public static void main(String[] args) {
        float money = 20160812;
        CashContext cc;
        cc = new CashContext("rebate");
        System.out.printf("Strategy Rebate: %.0f --> %.0f\n", money, cc.getResult(money));
        cc = new CashContext("return");
        System.out.printf("Strategy Return: %.0f --> %.0f\n", money, cc.getResult(money));
        cc = new CashContext("normal");
        System.out.printf("Strategy Normal: %.0f --> %.0f\n", money, cc.getResult(money));
    }
}

interface CashSuper {
    float acceptCash(float money);
}

class CashNormal implements CashSuper {
    public float acceptCash(float money) {
        return money;
    }
}

class CashRebate implements CashSuper {
    public CashRebate(float rate) {
        this.rate = rate;
    }
    public float acceptCash(float money) {
        return this.rate * money;
    }
    private float rate;
}

class CashReturn implements CashSuper {
    public CashReturn(float threshold, float retval) {
        this.threshold = threshold;
        this.retval = retval;
    }

    public float acceptCash(float money) {
        if (money >= threshold)
            money -= retval;
        return money;
    }

    private float threshold;
    private float retval;
}