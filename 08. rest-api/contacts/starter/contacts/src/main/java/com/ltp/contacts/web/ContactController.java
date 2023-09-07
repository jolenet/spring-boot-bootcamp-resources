package com.ltp.contacts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ltp.contacts.pojo.Contact;
import com.ltp.contacts.service.ContactService;

@Controller
public class ContactController {
    
    @Autowired
    private ContactService contactService;

    @GetMapping("/contact/{id}")
    @ResponseBody   //serialise the data into JSON 
    public Contact getContact(@PathVariable String id){
        return new Contact("123", "Jon Snow", "41235122");
    }

}
