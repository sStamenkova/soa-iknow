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

@RestController
@RequestMapping("/prof")
public class ProfessorGateContoller {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/professorInfo/{id}")
    public Object getProfessorInfo(@PathVariable(value = "id") Long id) {
        Random rnd = new Random();
        List<ServiceInstance> services1 = discoveryClient.getInstances("professor-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance)
                services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();
        Object response = this.restTemplate.getForObject("http://" + ip + ":8023/professorInfo/" + id, Object.class);
        System.out.println(response);
        return response;
    }

    @RequestMapping(value = "createProfessorInfo", method = RequestMethod.POST)
    public Object createProfessorInfo(@RequestParam("userId") long userId,
                                      @RequestParam("fieldOfLessons") String fieldOfLessons,
                                      @RequestParam("status") String status,
                                      @RequestParam("numberOfSubjects") int numberOfSubjects,
                                      @RequestParam("years") int years,
                                      @RequestParam("fulltime") boolean isFull
    ) {
        Random rnd = new Random();
        Object profInfo = null;
        Object user = restTemplate.getForObject("http://localhost:8000/auth/user/" + userId, Object.class);

        if (user != null) {
            MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
            parameters.add("userId", userId);
            parameters.add("fieldOfLessons", fieldOfLessons);
            parameters.add("status", status);
            parameters.add("numberOfOSubjects", numberOfSubjects);
            parameters.add("years", years);
            parameters.add("fulltime", isFull);
            List<ServiceInstance> services1 = discoveryClient.getInstances("professor-service");
            EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance)
                    services1.get(rnd.nextInt(services1.size()));
            String ip = service1.getInstanceInfo().getIPAddr();
            profInfo = this.restTemplate.postForObject("http://" + ip + ":8083/createProfessorInfo/", parameters, Object.class);
        }
        System.out.println(profInfo);
        return profInfo;
    }

    @RequestMapping(value = "changeProfessorWorkExperience", method = RequestMethod.POST)
    public void changeProfessorWorkExperience(@RequestParam("id") long id,
                                              @RequestParam("years") int years) {
        Random rnd = new Random();
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("id", id);
        parameters.add("years", years);
        List<ServiceInstance> services1 = discoveryClient.getInstances("professor-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance)
                services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();
        this.restTemplate.postForObject("http://" + ip + ":8083/changeProfessorWorkExperience/", parameters, Object.class);

    }

    @RequestMapping(value = "changeProfessorStatus", method = RequestMethod.POST)
    public void changeProfessorStatus(@RequestParam("id") long id,
                                      @RequestParam("status") String status) {
        Random rnd = new Random();
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("id", id);
        parameters.add("status", status);
        List<ServiceInstance> services1 = discoveryClient.getInstances("professor-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance)
                services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();
        this.restTemplate.postForObject("http://" + ip + ":8083/changeProfessorStatus/", parameters, Object.class);
    }

    @RequestMapping(value = "changeProfessorWorkTime", method = RequestMethod.POST)
    public void changeProfessorWorkTime(@RequestParam("id") long id,
                                        @RequestParam("fulltime") boolean isFull) {
        Random rnd = new Random();
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("id", id);
        parameters.add("fulltime", isFull);
        List<ServiceInstance> services1 = discoveryClient.getInstances("professor-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance)
                services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();
        this.restTemplate.postForObject("http://" + ip + ":8083/changeProfessorWorkTime/", parameters, Object.class);
    }

    @RequestMapping(value = "changeProfessorNumLessons", method = RequestMethod.POST)
    public void changeProfessorWorkTime(@RequestParam("id") long id,
                                        @RequestParam("numberOfLessons") int numLessons) {
        Random rnd = new Random();
        MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
        parameters.add("id", id);
        parameters.add("numberOfLessons", numLessons);
        List<ServiceInstance> services1 = discoveryClient.getInstances("professor-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance)
                services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();
        this.restTemplate.postForObject("http://" + ip + ":8083/changeProfessorNumLessons/", parameters, Object.class);
    }

}
