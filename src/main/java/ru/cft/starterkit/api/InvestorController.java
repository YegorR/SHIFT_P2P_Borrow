package ru.cft.starterkit.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.starterkit.data.PercentData;
import ru.cft.starterkit.service.LogicService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/investor")
public class InvestorController {

    private static final Logger log = LoggerFactory.getLogger(InvestorController.class);


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

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/percent_list",
            produces = "application/json"
    )
    public ArrayList<PercentData.Row> getPercentList() throws IOException{
        try{
            PercentData pd = PercentData.getInstance();
            return pd.getRows();
        }
        catch (IOException e){
            log.error("Failed to read percent data: {}", e.getMessage());
            throw e;
        }
    }
}
