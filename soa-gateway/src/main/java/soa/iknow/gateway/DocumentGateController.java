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
@RequestMapping("/docs")
public class DocumentGateController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/document/{id}")
    public Object getDocument(@PathVariable(value = "id") Long id) {
        Random rnd = new Random();
        List<ServiceInstance> services1 = discoveryClient.getInstances("soa-zuul");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance)
                services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();
        Object response =  this.restTemplate.getForObject("http://" + ip + ":8001/document-service/document/" + id, Object.class);
        System.out.println(response);
        return response;
    }

    @RequestMapping(value = "/documentsForUser")
    public Object getDocumentsforUser(@PathVariable("userId") long userId) {
        Random rnd = new Random();
        List<ServiceInstance> services1 = discoveryClient.getInstances("soa-zuul");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance)
                services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();
        Object response =  this.restTemplate.getForObject("http://" + ip + ":8001/document-service/documentsForUser/" + userId, Object.class);
        System.out.println(response);
        return response;
    }

    @RequestMapping(value = "newDocument", method = RequestMethod.POST)
    public Object save(@RequestParam("userId") long userId, @RequestParam("content") String content,
                       @RequestParam("name") String name, @RequestParam("code") String code, @RequestParam("cost") String cost) {
        Random rnd = new Random();
        Object document = null;
        Object user = restTemplate.getForObject("http://localhost:8000/auth/user/" + userId, Object.class);

        if(user != null) {
            MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
            parameters.add("content", content);
            parameters.add("name", name);
            parameters.add("cost", cost);
            parameters.add("userId",userId);
            parameters.add("code", code);
            List<ServiceInstance> services1 = discoveryClient.getInstances("soa-zuul");
            EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance)
                    services1.get(rnd.nextInt(services1.size()));
            String ip = service1.getInstanceInfo().getIPAddr();

            document = this.restTemplate.postForObject("http://" + ip + ":8001/document-service/newDocument/", parameters, Object.class);
            System.out.println(document);
        }
        return document;
    }
    @RequestMapping(value = "payForDocument", method = RequestMethod.POST)
    public Object payForDocument(@RequestParam("id") long id){
        Random rnd = new Random();
        Object document = null;
        MultiValueMap<String, Long> parameters = new LinkedMultiValueMap<>();
        parameters.add("id", id);
        List<ServiceInstance> services1 = discoveryClient.getInstances("soa-zuul");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance)
                services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();

        document = this.restTemplate.postForObject("http://" + ip + ":8001/document-service/payForDocument/", parameters, Object.class);
        System.out.println(document);
        return document;
    }
}
