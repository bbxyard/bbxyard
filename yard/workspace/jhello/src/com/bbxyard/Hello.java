package com.bbxyard.hello;

import java.awt.Color;


class Singleton {
	public static Singleton getInstance() {
		if (null == inst) {
			inst = new Singleton();
		}
		return inst;
	}

	void show() {
		System.out.println("this is a singleton!");
	}

	private Singleton() {

	}
	private static Singleton inst = null;
}

class HanoiTower {
    public static void moveDish(int level, char from, char inter, char to) {
        if (level == 1) {// 如果只有一个盘子就退出迭代
            System.out.println("从 " + from + " 移动盘子 1 号到 " + to);
        } else {// 如果有大于一个盘子就继续迭代
            moveDish(level - 1, from, to, inter);
            System.out.println("从 " + from + " 移动盘子 " + level + " 号到 " + to);
            moveDish(level - 1, inter, from, to);
        }
    }

    public static void main(String[] args) {
        int nDisks = 4;// 设置汉诺塔为3阶
        moveDish(nDisks, 'A', 'B', 'C');// 实现移动算法
    }
}

/**
 * @author bbxyard
 * @class  Cat
 * @category overide
 *
 */
class Cat {
    private String name; // 表示猫咪的名字
    private int age; // 表示猫咪的年龄
    private double weight; // 表示猫咪的重量
    private Color color; // 表示猫咪的颜色

    public Cat(String name, int age, double weight, Color color) {// 初始化猫咪的属性
        this.name = name;
        this.age = age;
        this.weight = weight;
        this.color = color;
    }

    @Override
    public boolean equals(Object obj) {// 利用属性来判断猫咪是否相同
        if (this == obj) {// 如果两个猫咪是同一个对象则相同
            return true;
        }
        if (obj == null) {// 如果两个猫咪有一个为null则不同
            return false;
        }
        if (getClass() != obj.getClass()) {// 如果两个猫咪的类型不同则不同
            return false;
        }
        Cat cat = (Cat) obj;
        return name.equals(cat.name) && (age == cat.age)
                && (weight == cat.weight) && (color.equals(cat.color));// 比较猫咪的属性
    }

    @Override
    public int hashCode() {// 重写hashCode()方法
        return 7 * name.hashCode() + 11 * new Integer(age).hashCode() + 13
                * new Double(weight).hashCode() + 17 * color.hashCode();
    }
}

public class Hello {
	public static void main(String[] args) {
		System.out.println("Just do it! mac");
		Singleton sinst = Singleton.getInstance();
		sinst.show();
		HanoiTower.main(args);
	}
}
