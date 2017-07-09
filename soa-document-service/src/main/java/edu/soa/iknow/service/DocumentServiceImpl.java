package edu.soa.iknow.service;

import edu.soa.iknow.model.Document;
import edu.soa.iknow.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Popov on 07.7.2017.
 */
@Service
public class DocumentServiceImpl implements DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    @Override
    public Document saveDocument(Long userId, String content, String file, String cost, String code, boolean isPayed) {
        Document document = new Document();
        document.setContent(content);
        document.setDate(new Date());
        document.setFile(file);
        document.setCost(cost);
        document.setPayed(isPayed);
        document.setUserId(userId);
        document.setCode(code);
        return documentRepository.save(document);
    }

    @Override
    public Document getDocument(Long documentId) {
        return documentRepository.findOne(documentId);
    }

    @Override
    public List<Document> getDocumentsForUser(Long userId) {
        return documentRepository.findByUserId(userId);
    }

    @Override
    public Document payForDocument(Long documentId) {
        Document document = documentRepository.findOne(documentId);
        document.setPayed(true);
        documentRepository.delete(documentId);
        return documentRepository.save(document);
    }
}
