package com.learn.zsh.platformservice;

import com.learn.zsh.internetlearn.pageview.InternEnvironment;
import com.learn.zsh.internetlearn.pageview.InternEnvironmentImpl;

/**
 * Created by ZhouShaohua on 2018/6/21.
 */
public class ServerFactory extends InternEnvironmentImpl{
    private InternEnvironmentImpl environment;
    private IPlatformServiceManager platformService;
    public ServerFactory(){
        platformService = new PlatformServiceManagerImpl();
        bind(IPlatformServiceManager.class, platformService);
    }

    @Override
    public <T> void bind(Class<T> clazz, T instance) {
        super.bind(clazz, instance);
    }
}
