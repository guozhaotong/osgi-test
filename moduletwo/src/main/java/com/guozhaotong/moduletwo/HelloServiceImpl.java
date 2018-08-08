package com.guozhaotong.moduletwo;

import com.guozhaotong.Guo;
import com.guozhaotong.moduletwo.interfaces.HelloService2;

public class HelloServiceImpl implements HelloService2 {
    public String output() {
        Guo guo = new Guo();
        return guo.sayNum() + "";
    }
}
