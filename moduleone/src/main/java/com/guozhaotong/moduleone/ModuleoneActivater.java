package com.guozhaotong.moduleone;

import com.guozhaotong.moduleone.interfaces.HelloService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ModuleoneActivater implements BundleActivator {
    private ServiceRegistration serviceRegistration;

    public void start(BundleContext bundleContext) throws Exception {
        HelloService helloService = new HelloServiceImpl();
        System.out.println(helloService.output());
        serviceRegistration = bundleContext.registerService(HelloService.class.getName(), helloService, null);
        System.out.println("module one start!");
    }

    public void stop(BundleContext bundleContext) throws Exception {
        serviceRegistration.unregister();
        System.out.println("module one stop!");
    }
}
