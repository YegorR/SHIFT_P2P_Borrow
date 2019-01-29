package ru.cft.starterkit.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/ask",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public String ask(
            @RequestParam(name = "sum") Double sum,
            @RequestParam(name = "term") Integer term){
        return "You ask money";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/ok",
            produces = "application/json"
    )
    public String consent (
            @RequestParam(name = "server_offer_id") Long server_offer_id) {
        return "Get your money";
    }
}