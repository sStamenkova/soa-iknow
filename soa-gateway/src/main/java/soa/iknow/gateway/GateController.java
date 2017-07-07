package soa.iknow.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;



@RestController
public class GateController {
    @Bean
    RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/clients")
    public List<ServiceInstance> clients(@RequestParam(value="name") String name) {
        return this.discoveryClient.getInstances(name);
    }

    @RequestMapping("/user/{id}")
    public Object getUser(@PathVariable(value = "id") Long id){
        Random rnd = new Random();

        List<ServiceInstance> services1 = discoveryClient.getInstances("auth-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();

        Object response =  this.restTemplate.getForObject("http://" + ip + ":8080/user/" + id, Object.class);
        System.out.println(response);
        return response;
    }

    @RequestMapping("/semester/{id}")
    public Object getSemester(@PathVariable(value = "id") Long id){
        Random rnd = new Random();

        List<ServiceInstance> services1 = discoveryClient.getInstances("studentdossier-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();

        Object response =  this.restTemplate.getForObject("http://" + ip + ":8088/semester/" + id, Object.class);
        System.out.println(response);
        return response;
    }

    @RequestMapping("/subject/{id}")
    public Object getSubject(@PathVariable(value = "id") Long id) {
        Random rnd = new Random();

        List<ServiceInstance> services1 = discoveryClient.getInstances("subject-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();

        Object response =  this.restTemplate.getForObject("http://" + ip + ":8083/subject/" + id, Object.class);
        System.out.println(response);
        return response;
    }
    @RequestMapping("/announcement/{id}")
    public Object getAnnouncement(@PathVariable(value = "id") Long id) {
        Random rnd = new Random();
        List<ServiceInstance> services1 = discoveryClient.getInstances("studentservice-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();
        Object response =  this.restTemplate.getForObject("http://" + ip + ":8082/announcement/" + id, Object.class);
        System.out.println(response);
        return response;
    }
    @RequestMapping("/document/{id}")
    public Object getDocument(@PathVariable(value = "id") Long id) {
        Random rnd = new Random();
        List<ServiceInstance> services1 = discoveryClient.getInstances("document-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();
        Object response =  this.restTemplate.getForObject("http://" + ip + ":8024/document/" + id, Object.class);
        System.out.println(response);
        return response;
    }
}
