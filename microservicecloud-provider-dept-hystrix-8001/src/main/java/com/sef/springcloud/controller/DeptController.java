package com.sef.springcloud.controller;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sef.springcloud.entities.Dept;
import com.sef.springcloud.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {
    @Autowired
    private DeptService deptService;

    // import org.springframework.cloud.client.discovery.DiscoveryClient;
//    @Autowired
//    private DiscoveryClient client;

    @RequestMapping(value = "/dept/add", method = RequestMethod.POST)
    public boolean add(@RequestBody Dept dept) {
        return deptService.add(dept);
    }

    @RequestMapping(value = "/dept/get/{id}", method = RequestMethod.GET)
    @HystrixCommand(fallbackMethod = "processHystrix_Get")
    public Dept get(@PathVariable("id") Long id) {

        Dept dept = deptService.get(id);
        if(null == dept){
            throw new RuntimeException("该ID：" + id + "没有对应的信息");
        }

        return dept;
    }

    public Dept processHystrix_Get(@PathVariable("id") Long id){
        Dept errorObj = new Dept("该ID：" + id + "没有对应的信息， null -- @HystrixCommand");
        errorObj.setDeptno(id);
        errorObj.setDb_source("no this database in MySQL");
        return errorObj;
    }

    @RequestMapping(value = "/dept/list", method = RequestMethod.GET)
    public List<Dept> list() {
        return deptService.list();
    }

//    @RequestMapping(value = "/dept/discovery", method = RequestMethod.GET)
//    public Object discovery(){
//        List<String> list = client.getServices();
//        System.out.println("**********" + list);
//        List<ServiceInstance> srvList = client.getInstances("MICROSERVICECLOUD-DEPT");
//        for (ServiceInstance element : srvList) {
//            System.out.println(element.getServiceId() + "\t" + element.getHost() + "\t" + element.getPort() + "\t" + element.getUri());
//        }
//        return this.client;
//    }
//


}
