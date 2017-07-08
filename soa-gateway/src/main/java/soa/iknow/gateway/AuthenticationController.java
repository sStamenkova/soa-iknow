package soa.iknow.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Created by SimonaS on 08/07/2017.
 */

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @RequestMapping(value = "register", method = RequestMethod.POST)
    public Object registration(@RequestParam("name") String name, @RequestParam("lastName") String lastName,
                               @RequestParam("username") String username, @RequestParam("email") String email,
                               @RequestParam("embg") String embg, @RequestParam("password") String password,
                               @RequestParam("passwordConfirm") String passwordConfirm,
                               @RequestParam("role") String role) {

        Random rnd = new Random();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("name", name);
        parameters.add("lastName", lastName);
        parameters.add("username", username);
        parameters.add("email", email);
        parameters.add("embg", embg);
        parameters.add("password", password);
        parameters.add("passwordConfirm", passwordConfirm);
        parameters.add("role", role);

        List<ServiceInstance> services1 = discoveryClient.getInstances("auth-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();

        Object response =  this.restTemplate.postForObject("http://" + ip + ":8080/register/", parameters, Object.class);
        System.out.println(response);
        return response;
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public Object login(@RequestParam("username") String username, @RequestParam("password") String password) {
        Random rnd = new Random();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("username", username);
        parameters.add("password", password);

        List<ServiceInstance> services1 = discoveryClient.getInstances("auth-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();

        Object response =  this.restTemplate.postForObject("http://" + ip + ":8080/login/", parameters, Object.class);
        System.out.println(response);
        return response;
    }
}
