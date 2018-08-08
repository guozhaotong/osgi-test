package com.guozhaotong.moduleone;

import com.guozhaotong.Guo;
import com.guozhaotong.moduleone.interfaces.HelloService;

public class HelloServiceImpl implements HelloService {
    public String output() {
        Guo guo = new Guo();
        return guo.sayHello();
    }
}
