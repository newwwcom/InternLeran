package com.learn.zsh.internetlearn.pageview;

/**
 * Created by ZhouShaohua on 2018/6/21.
 */
public interface InternEnvironment {
    <T> T get(Class<T> clazz);
}
