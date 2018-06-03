package com.learn.zsh.platformservice;

import android.support.v4.util.ArrayMap;
import com.learn.zsh.internetlearn.utils.NetLogs;
/**
 * Created by zhoushaohua on 2018/6/3.
 */

public class PlatformServiceManagerImpl implements IPlatformServiceManager {
    private static final String TAG = NetLogs.NETLOG + "PlatformServiceManagerImpl";
    private ArrayMap<String, Object> services = new ArrayMap<>();
    public PlatformServiceManagerImpl(){

    }

    @Override
    public synchronized <T> void bindService(Class<T> clazz, T service) {
        if(clazz == null || service == null){
            return;
        }
        String name = clazz.getName();
        services.put(name, service);
    }

    @Override
    public synchronized void unbindService(Class clazz) {
        if(clazz == null){
            return;
        }
        String name = clazz.getName();
        services.remove(name);
    }

    @Override
    public synchronized <T> T getService(Class<T> clazz) {
        if(clazz == null){
            return null;
        }
        String name = clazz.getName();
        if(services.containsKey(name)){
            return (T) services.get(name);
        }else{
            NetLogs.e(TAG, "service can't get in services, please check you request.");
            return null;
        }
    }
}
