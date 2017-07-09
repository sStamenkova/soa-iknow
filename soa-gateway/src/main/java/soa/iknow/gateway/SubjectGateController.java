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
@RequestMapping("/sub")
public class SubjectGateController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/subject/{id}")
    public Object getSubject(@PathVariable(value = "id") Long id) {
        Random rnd = new Random();

        List<ServiceInstance> services1 = discoveryClient.getInstances("soa-zuul");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();

        Object response =  this.restTemplate.getForObject("http://" + ip + ":8001/subject-service/subject/" + id, Object.class);
        System.out.println(response);
        return response;
    }

    @RequestMapping(value = "subject", method = RequestMethod.POST)
    public Object save(@RequestParam("code") String code, @RequestParam("name") String name,
                       @RequestParam("description") String description, @RequestParam("professor") String professor,
                       @RequestParam("semesterID") Long semesterID) {
        Object subject = null;

        Object semester = restTemplate.getForObject("http://localhost:8000/dossier/semester/" + semesterID, Object.class);

        if(semester != null) {
            Random rnd = new Random();
            MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
            parameters.add("code", code);
            parameters.add("name", name);
            parameters.add("description", description);
            parameters.add("professor", professor);
            parameters.add("semesterID", semesterID);

            List<ServiceInstance> services1 = discoveryClient.getInstances("soa-zuul");
            EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
            String ip = service1.getInstanceInfo().getIPAddr();

            subject =  this.restTemplate.postForObject("http://" + ip + ":8001/subject-service/subject/", parameters, Object.class);
            System.out.println(subject);
        }

        return subject;
    }

    @RequestMapping(value = "subject")
    public Object getSubjects(){
        Random rnd = new Random();

        List<ServiceInstance> services1 = discoveryClient.getInstances("soa-zuul");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();

        Object response =  this.restTemplate.getForObject("http://" + ip + ":8001/subject-service/subject/", Object.class);
        System.out.println(response);
        return response;
    }

    @RequestMapping(value = "subjectsBySemester")
    public Object getSubjectsBySemester(@RequestParam("semester") Long id){
        Random rnd = new Random();

        List<ServiceInstance> services1 = discoveryClient.getInstances("soa-zuul");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();

        Object response =  this.restTemplate.getForObject("http://" + ip + ":8001/subject-service/subjectsBySemester?semester=" + id, Object.class);
        System.out.println(response);
        return response;
    }
}
