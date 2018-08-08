package com.guozhaotong.moduletwo;

import com.guozhaotong.moduletwo.interfaces.HelloService2;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

public class ModuletwoActivator implements BundleActivator {
    private ServiceRegistration serviceRegistration;
    public void start(BundleContext bundleContext) throws Exception {
        HelloService2 helloService = new HelloServiceImpl();
        System.out.println(helloService.output());
        serviceRegistration = bundleContext.registerService(HelloService2.class.getName(), helloService, null);
        System.out.println("module two start!");
    }

    public void stop(BundleContext bundleContext) throws Exception {
        serviceRegistration.unregister();
        System.out.println("module two stop!");
    }
}
