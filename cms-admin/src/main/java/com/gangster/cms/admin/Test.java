package com.gangster.cms.admin;

/**
 * @author Yoke
 * @date 6/9/18 3:11 PM
 */
public class Test {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> System.out.println("test"));
        thread.start();
        thread.stop();
        Thread thread1 = new Thread(() -> System.out.println("test1"));
        thread1.start();
        thread1.destroy();
    }
}
