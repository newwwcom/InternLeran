package com.learn.zsh.mylibrary.devkit;

import com.squareup.otto.ThreadEnforcer;

/**
 * Created by zhoushaohua on 2018/3/29.
 */

public class ProcessorFactory {
    private Bus bus = new Bus(new com.squareup.otto.Bus(ThreadEnforcer.ANY));

    public ProcessorFactory(){

    }

    public Bus getBus() {
        return bus;
    }
}
