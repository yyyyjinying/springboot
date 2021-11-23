package com.cloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PayHystrixService {
    public String paymentInfo_ok(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "  paymentInfo_ok,id: " + id + "\t" + "hahahaha~~~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_timeoutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_timeout(Integer id) {
        try {
            Thread.sleep(3000); // 隔一秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        int age = 10/0;
        return "线程池" + Thread.currentThread().getName() + "  paymentInfo_timeout,id: " + id + "\t" + "hahahaha~~~";
    }

    /**
     * 服务降级
     *
     * @param id
     * @return
     */
    public String paymentInfo_timeoutHandler(Integer id) {
        return "线程池" + Thread.currentThread().getName() + "  系统繁忙或者运行报错，请稍后再试,id: " + id + "\t" + "aiaiai~~~";
    }


    @HystrixCommand(fallbackMethod = "paymentCicrcuitBreaker_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),// 是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), // 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") // 失败率达到多少后跳闸
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("******id 不能负数");
        }
        String serialNumber = IdUtil.simpleUUID();

        return Thread.currentThread().getName() + "\t" + "调用成功，流水号： " + serialNumber;
    }

    public String paymentCicrcuitBreaker_fallback(Integer id) {
        return "id 不能负数，请稍等后再试， id: " + id;
    }
}
