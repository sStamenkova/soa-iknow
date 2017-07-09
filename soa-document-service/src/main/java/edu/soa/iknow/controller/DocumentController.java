package edu.soa.iknow.controller;

import edu.soa.iknow.model.Document;
import edu.soa.iknow.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class DocumentController {

    @Autowired
    DocumentService documentService;

    @RequestMapping(value = "newDocument", method = RequestMethod.POST)
    public Document save(@RequestParam("userId") long userId, @RequestParam("content") String content,
                         @RequestParam("name") String name, @RequestParam("code") String code, @RequestParam("cost") String cost ) {
        Document newDocument = documentService.saveDocument(userId,content,name,cost, code, false);
        return newDocument;
    }

    @RequestMapping(value = "document/{id}")
    public Document getDocumentById(@PathVariable("id") long id) {
        return documentService.getDocument(id);
    }

    @RequestMapping(value = "documentsForUser")
    public List<Document> getDocumentsforUser(@PathVariable("userId") long userId) {
        return documentService.getDocumentsForUser(userId);
    }
    @RequestMapping(value = "payForDocument", method = RequestMethod.POST)
    public Document payForDocument(@RequestParam("id") long id){
        return documentService.payForDocument(id);
    }

}
