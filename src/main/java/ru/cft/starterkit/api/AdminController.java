package ru.cft.starterkit.api;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @RequestMapping(
            method = RequestMethod.POST,
            path = "/add/borrower",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public Boolean addOffer (
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password){
        return true;
    }
}
