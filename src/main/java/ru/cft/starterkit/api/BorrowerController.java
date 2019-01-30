package ru.cft.starterkit.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.starterkit.entity.ServerOffer;
import ru.cft.starterkit.service.LogicService;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    private static String TEST_LOGIN = "b1";

    private final LogicService logicService;

    @Autowired
    public BorrowerController(LogicService logicService){
        this.logicService = logicService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/ask",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public ServerOffer ask(
            @RequestParam(name = "sum") Double sum,
            @RequestParam(name = "term") Integer term,
            @RequestParam(name = "id") int id){
        return logicService.createServerOffer(sum, term);
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