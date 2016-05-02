package com.devsoft.contacts.controller;

import com.devsoft.contacts.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by Sergey on 30.04.2016.
 */
@Controller
@RequestMapping(value = "/hello", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ContactController {
    @Autowired
    private ContactService contactService;

    @RequestMapping(value = "/contacts", produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody void getContacts(Writer responseWriter,
                                          @RequestParam(value = "nameFilter") String nameFilter) throws IOException {
        contactService.getContacts(responseWriter, nameFilter);
    }
}
