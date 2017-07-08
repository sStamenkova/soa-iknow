package soa.iknow.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Random;

/**
 * Created by SimonaS on 08/07/2017.
 */

@RestController
@RequestMapping("/document")
public class DocumentController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

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
