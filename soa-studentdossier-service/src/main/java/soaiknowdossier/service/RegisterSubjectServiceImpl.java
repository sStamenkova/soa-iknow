package soaiknowdossier.service;

import org.springframework.beans.factory.annotation.Autowired;
import soaiknowdossier.model.RegisterSubject;
import soaiknowdossier.repository.RegisterSubjectRepository;

import java.util.List;

/**
 * Created by SimonaS on 02/05/2017.
 */
public class RegisterSubjectServiceImpl implements RegisterSubjectService {

    @Autowired
    RegisterSubjectRepository registerSubjectRepository;

    @Override
    public List<RegisterSubject> findByUserId(long id) {
        return registerSubjectRepository.findByUserId(id);
    }
}
