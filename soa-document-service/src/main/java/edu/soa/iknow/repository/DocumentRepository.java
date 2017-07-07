package edu.soa.iknow.repository;

import edu.soa.iknow.model.Document;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Popov on 07.7.2017.
 */
public interface DocumentRepository extends CrudRepository<Document, Long> {

}
