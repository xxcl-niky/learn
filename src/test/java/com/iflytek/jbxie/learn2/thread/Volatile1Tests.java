package com.iflytek.jbxie.learn2.thread;

/**
 * volatile测试
 *
 * @author jbxie
 * @create 2021/01/15 14:59
 */

public class Volatile1Tests {
    public static void main(String[] args){
        RunnableDemo td = new RunnableDemo();
        new Thread(td).start();
        while(true){
            System.out.println("********");
            if(td.isFlag()){
                System.out.println("########");
                break;
            }
        }
    }
}

class RunnableDemo implements Runnable {
    private boolean flag = false;
    public void run(){
        try{
            // 该线程 sleep(200), 导致了程序无法执行成功
            Thread.sleep(2000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        flag = true;
        System.out.println("flag="+isFlag());
    }
    public boolean isFlag(){
        return flag;
    }
    public void setFlag(boolean flag){
        this.flag = flag;
    }
}
