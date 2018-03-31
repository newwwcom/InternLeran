package com.learn.zsh.mylibrary.devkit;

/**
 * Created by zhoushaohua on 2018/3/29.
 */
public class Bus {
    private com.squareup.otto.Bus mBus;

    public Bus(com.squareup.otto.Bus bus){
        this.mBus = bus;
    }

    public void register(Object object){
        try {
            mBus.register(object);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void unRegister(Object object){
        try {
            mBus.unregister(object);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public void post(Object event){
        try {
            mBus.post(event);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
