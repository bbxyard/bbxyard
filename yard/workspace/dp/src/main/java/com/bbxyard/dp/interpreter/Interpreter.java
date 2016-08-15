package com.bbxyard.dp.interpreter;

import java.util.Vector;

/**
 * Created by bbxyard on 16-8-16.
 */
public class Interpreter {
    public static void main(String[] args) {
        Client.run();
        System.out.println("Interpreter.main.done!!");
    }
}

class Client {
    public static void run() {
        Context context = new Context();
        context.setInText("How do you do");
        while (context.isFinished()) {
            String text = context.getCurrentInTextPart();
            // 决策解析器
            AbstructExpression exp = null;
            // 简易粗暴傻瓜模拟，奇偶法
            if (text.length() % 2 == 0) {
                exp = new TerminalExpression();
            } else {
                exp = new NonterminalExpression();
            }
            exp.interpret(context);
            context.moveNext();
        }
        System.out.println("Client.run | input is: " + context.getInText());
        System.out.println("Client.run | output is: \n" + context.getOutText());
        System.out.println("Client.run.done");
    }
}

class Context {
    public Context() {
        inText = "Guten Tag!! Brian";
        inCursor = 0;
        inTextParts = inText.split(" ");
        outTextStrBuffer = new StringBuffer();
        outTextStrBuffer.append("你好!! BoXu\n");
    }

    public String getInText() {
        return inText;
    }
    public void setInText(String inText) {
        this.inText = inText;
        this.inTextParts = this.inText.split(" ");
    }
    public String getOutText() {
        return outTextStrBuffer.toString();
    }
    public void appentOutText(String s) {
        outTextStrBuffer.append(s);
    }

    // 简易模拟分片解析操作
    public boolean isFinished() {
        return (inCursor != inTextParts.length);
    }
    public String getCurrentInTextPart() {
        return inTextParts[inCursor];
    }
    public void moveNext() {
        ++inCursor;
    }

    private String      inText;
    private String[]    inTextParts;
    private int         inCursor;
    private String       outText;
    private StringBuffer outTextStrBuffer;
}

interface AbstructExpression {
    void interpret(Context ctx);
}

class TerminalExpression implements AbstructExpression {
    public void interpret(Context ctx) {
        ctx.appentOutText("==> <Term>#" + ctx.getCurrentInTextPart() + "#\n");
        System.out.println("TerminalExpression.interpret.done");
    }
}

class NonterminalExpression implements AbstructExpression {
    public void interpret(Context ctx) {
        ctx.appentOutText("==> <NonT>[" + ctx.getCurrentInTextPart() + "]\n");
        System.out.println("NonterminalExpression.interpret.done");
    }
}