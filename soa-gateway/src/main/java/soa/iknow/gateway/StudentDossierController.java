package soa.iknow.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by SimonaS on 08/07/2017.
 */

@RestController
@RequestMapping("/dossier")
public class StudentDossierController {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

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

    @RequestMapping(value = "semester", method = RequestMethod.POST)
    public Object addSemester(@RequestParam("name") String name,
                                @RequestParam("field") String field,
                                @RequestParam("quota") String quota,
                                @RequestParam("price") double price,
                                @RequestParam("userID") long userID) {

        Object semester = null;
        Object user = restTemplate.getForObject("http://localhost:8000/auth/user/" + userID, Object.class);

        if(user != null) {
            Random rnd = new Random();
            MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
            parameters.add("name", name);
            parameters.add("field", field);
            parameters.add("quota", quota);
            parameters.add("price", price);
            parameters.add("userID", userID);

            List<ServiceInstance> services1 = discoveryClient.getInstances("studentdossier-service");
            EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
            String ip = service1.getInstanceInfo().getIPAddr();

            semester =  this.restTemplate.postForObject("http://" + ip + ":8088/semester/", parameters, Object.class);
            System.out.println(semester);
        }

        return semester;
    }

    @RequestMapping(value = "registerSubject", method = RequestMethod.POST)
    public Object registerSubject(@RequestParam("serialNumber") long serialNumber,
                                           @RequestParam("isConfirmed") boolean isConfirmed,
                                           @RequestParam("date") Date date,
                                           @RequestParam("areTaxesPaid") boolean areTaxesPaid,
                                           @RequestParam("examSession") String examSession,
                                           @RequestParam("subjectID") long subjectID,
                                           @RequestParam("userID") long userID){

        Object registerSubject = null;

        Object user = restTemplate.getForObject("http://localhost:8000/auth/user/" + userID, Object.class);
        Object subject = restTemplate.getForObject("http://localhost:8000/subjects/subject/" + subjectID, Object.class);

        if(user != null && subject != null) {
            Random rnd = new Random();
            MultiValueMap<String, Object> parameters = new LinkedMultiValueMap<>();
            parameters.add("serialNumber", serialNumber);
            parameters.add("isConfirmed", isConfirmed);
            parameters.add("date", date);
            parameters.add("areTaxesPaid", areTaxesPaid);
            parameters.add("examSession", examSession);
            parameters.add("subjectID", subjectID);
            parameters.add("userID", userID);

            List<ServiceInstance> services1 = discoveryClient.getInstances("studentdossier-service");
            EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
            String ip = service1.getInstanceInfo().getIPAddr();

            registerSubject =  this.restTemplate.postForObject("http://" + ip + ":8088/registerSubject/", parameters, Object.class);
            System.out.println(registerSubject);

        }

        return registerSubject;
    }

    @RequestMapping(value = "semesters")
    public Object getUserSemesters(@RequestParam(value = "student") long id){
        Random rnd = new Random();

        List<ServiceInstance> services1 = discoveryClient.getInstances("studentdossier-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();

        Object response =  this.restTemplate.getForObject("http://" + ip + ":8088/semesters?student=" + id, Object.class);
        System.out.println(response);
        return response;
    }

    @RequestMapping(value = "studentRegistry")
    public Object listStudentRegisters(@RequestParam(value = "student") long id){
        Random rnd = new Random();

        List<ServiceInstance> services1 = discoveryClient.getInstances("studentdossier-service");
        EurekaDiscoveryClient.EurekaServiceInstance service1 = (EurekaDiscoveryClient.EurekaServiceInstance) services1.get(rnd.nextInt(services1.size()));
        String ip = service1.getInstanceInfo().getIPAddr();

        Object response =  this.restTemplate.getForObject("http://" + ip + ":8088/studentRegistry?student=" + id, Object.class);
        System.out.println(response);
        return response;
    }
}
