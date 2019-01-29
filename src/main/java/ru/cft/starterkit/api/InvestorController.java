package ru.cft.starterkit.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.starterkit.service.LogicService;

@RestController
@RequestMapping("/investor")
public class InvestorController {

    /*private final LogicService logicService;

    @Autowired
    public InverstorController (LogicService logicService){
        this.logicService = logicService;
    }*/

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/offer",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public String addOffer (
            @RequestParam(name = "sum") Double sum){
        return "OK";
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/balance",
            produces = "application/json"
    )
    public String getBalance(){
        return "Balance";
    }
}
