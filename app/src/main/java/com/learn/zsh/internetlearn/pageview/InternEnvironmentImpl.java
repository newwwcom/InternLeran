package com.learn.zsh.internetlearn.pageview;

import com.learn.zsh.internetlearn.utils.NetLogs;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ZhouShaohua on 2018/6/21.
 */
public class InternEnvironmentImpl implements InternEnvironment {
    private static final String TAG = NetLogs.NETLOG + "FragmentUIExtension";
    public Map<Class, Object> env = new HashMap<>();

    public <T> void bind(Class<T> clazz, T instance){
        env.put(clazz, instance);
    }

    @Override
    public <T> T get(Class<T> clazz) {
        if(env.containsKey(clazz)){
            return (T) env.get(clazz);
        } else {
            NetLogs.e(TAG, "Binding not found exception, need get class is : " + clazz.getSimpleName());
            return null;
        }
    }
}
