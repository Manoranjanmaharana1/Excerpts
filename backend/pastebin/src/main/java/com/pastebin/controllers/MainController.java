package com.pastebin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pastebin.Dao.DataBaseAccessor;
import com.pastebin.enums.Response;
import com.pastebin.pojo.Paste;
import com.pastebin.pojo.PasteResponse;

import io.micrometer.common.util.StringUtils;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("excerpts/v1")
@Log4j2
public class MainController {

    @Autowired
    private DataBaseAccessor firebaseDataAccessor;

    @PostMapping("/pastes")
    public Response createPaste(@RequestBody final Paste paste) {
        log.info("Received request for Uid: {}", paste.getUniqueId());
        if (!validateData(paste)) {
            return Response.INVALID_REQUEST;
        }
        return firebaseDataAccessor.createPaste(paste);
    }

    @GetMapping("/pastes/{id}")
    public PasteResponse getPaste(@PathVariable("id") String id) {
        return firebaseDataAccessor.getPaste(id);
    }

    private boolean validateData(Paste paste) {
        if (StringUtils.isBlank(paste.getUniqueId()) 
            || StringUtils.isBlank(paste.getContent())) {
                return false;
            }
        return true;
    }

    @GetMapping("/get")
    public String sayhello() {
        log.info("Accessed!");
        return "Hello World!!";
    }
    
}
