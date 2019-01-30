package ru.cft.starterkit.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.starterkit.data.PercentData;
import ru.cft.starterkit.entity.Offer;
import ru.cft.starterkit.exception.IncorrectSumException;
import ru.cft.starterkit.exception.InvestorNotFoundException;
import ru.cft.starterkit.service.LogicService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.ArrayList;

@RestController
@RequestMapping("/investor")
public class InvestorController {

    private static final Logger log = LoggerFactory.getLogger(InvestorController.class);

    private static  String TEST_LOGIN = "user1  ";


    private final LogicService logicService;

    @Autowired
    public InvestorController (LogicService logicService){
        this.logicService = logicService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/offer",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public Offer addOffer (
            @RequestParam(name = "sum") Double sum,
            @RequestParam(name = "id") int id) throws InvestorNotFoundException, IOException, IncorrectSumException {
        switch(id){
            case 0:
                TEST_LOGIN = "user0"; break;
            case 1:
                TEST_LOGIN = "user1"; break;
            case 2:
                TEST_LOGIN = "user2"; break;
            case 3:
                TEST_LOGIN = "user3"; break;
            case 4:
                TEST_LOGIN = "user4"; break;
            case 5:
                TEST_LOGIN = "user5"; break;
            case 6:
                TEST_LOGIN = "user6"; break;
        }
        return logicService.createOffer(sum, logicService.getInvestor(TEST_LOGIN));
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
