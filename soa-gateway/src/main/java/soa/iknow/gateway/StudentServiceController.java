package soa.iknow.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * Created by SimonaS on 08/07/2017.
 */

@RestController
public class StudentServiceController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @RequestMapping(value = "newAnnouncement", method = RequestMethod.POST)
    public Object save(@RequestParam("title") String title, @RequestParam("content") String content) {
        Random rnd = new Random();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("title", title);
        parameters.add("content", content);

        List<ServiceInstance> services1 = discoveryClient.getInstances("studentservice-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();

        Object response =  this.restTemplate.postForObject("http://" + ip + ":8082/newAnnouncement/", parameters, Object.class);
        System.out.println(response);
        return response;
    }

    @RequestMapping(value = "announcements")
    public Object getAnnouncements() {
        Random rnd = new Random();
        List<ServiceInstance> services1 = discoveryClient.getInstances("studentservice-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();
        Object response =  this.restTemplate.getForObject("http://" + ip + ":8082/announcements/", Object.class);
        System.out.println(response);
        return response;
    }
}
