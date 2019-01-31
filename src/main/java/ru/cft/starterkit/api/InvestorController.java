package ru.cft.starterkit.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.entity.Offer;
import ru.cft.starterkit.exception.IncorrectSumException;
import ru.cft.starterkit.exception.InvestorNotFoundException;
import ru.cft.starterkit.service.AuthenticationService;
import ru.cft.starterkit.service.LogicService;


import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/investor")
public class InvestorController {

    private final LogicService logicService;
    private final AuthenticationService authenticationService;

    @Autowired
    public InvestorController (LogicService logicService, AuthenticationService authenticationService){
        this.logicService = logicService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/offer",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public Offer addOffer (
            @RequestParam(name = "sum") Double sum,
            @RequestParam(name = "id") UUID id) throws IOException, IncorrectSumException {
        try{
            Investor investor = authenticationService.getInvestor(id);
            return logicService.createOffer(sum, investor);
        }
        catch(InvestorNotFoundException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/balance",
            produces = "application/json"
    )
    public double getBalance(
            @RequestParam(name = "id") UUID id
    ){

        try{
            Investor investor = authenticationService.getInvestor(id);
            return investor.getBalance();
        }
        catch(InvestorNotFoundException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }
    }

    /*@RequestMapping(
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
    }*/
}
