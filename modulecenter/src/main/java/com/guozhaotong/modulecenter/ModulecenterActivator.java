package com.guozhaotong.modulecenter;

import com.guozhaotong.moduleone.interfaces.HelloService;
import com.guozhaotong.moduletwo.interfaces.HelloService2;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceReference;

public class ModulecenterActivator implements BundleActivator {
    private ServiceReference helloServiceReference;
    private ServiceReference helloServiceReference2;
    private HelloService helloService;
    private HelloService2 helloService2;

    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("modulecenter started!");
        helloServiceReference = bundleContext.getServiceReference(HelloService.class.getName());
        helloService = (HelloService) bundleContext.getService(helloServiceReference);

        helloServiceReference2 = bundleContext.getServiceReference(HelloService2.class.getName());
        helloService2 = (HelloService2) bundleContext.getService(helloServiceReference2);
        System.out.println(helloService.output() + "success!");
        System.out.println(helloService2.output() + "success!");
    }

    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("modulecenter stop!");
    }
}
