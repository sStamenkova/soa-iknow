package edu.soa.iknow.service;

import edu.soa.iknow.model.Document;

import java.io.File;
import java.util.List;

/**
 * Created by Popov on 07.7.2017.
 */
public interface DocumentService {
    Document saveDocument(Long userId,String content, String file,String cost,boolean isPayed);
    Document getDocument(Long documentId);
    List<Document> getDocumentsForUser(Long userId);
    Document payForDocument(Long documentId);
}
