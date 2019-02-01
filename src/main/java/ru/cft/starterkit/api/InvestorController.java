package ru.cft.starterkit.api;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.cft.starterkit.entity.Investor;
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

    private static final Logger log = LoggerFactory.getLogger(InvestorController.class);

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
    public Response addOffer (
            @RequestParam(name = "sum") Double sum,
            @RequestParam(name = "id") UUID id) throws IOException, IncorrectSumException {
        Response response;
        try{
            log.info("Запрос: POST investor/offer sum={}, id={}", sum, id);
            Investor investor = authenticationService.getInvestor(id);

            response = new Response(true,  logicService.createOffer(sum, investor), 200, "OK");
        }
        catch(InvestorNotFoundException e){
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
            response = new Response(false, null, 403, "Forbidden");
        }
        return response;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/balance",
            produces = "application/json"
    )
    public Response getBalance(
            @RequestParam(name = "id") UUID id
    ){
        Response response;
        try{
            log.info("Запрос: GET investor/balance id={}", id);
            Investor investor = authenticationService.getInvestor(id);
            response = new Response(true, investor.getBalance(), 200, "OK");
        }
        catch(InvestorNotFoundException e){
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
            response = new Response(false, null, 403, "Forbidden");
        }
        return response;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/pay",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public Response pay (
            @RequestParam(name = "sum") double sum,
            @RequestParam(name = "id") UUID id){
        Response response;
        try {
            log.info("Запрос: POST borrower/pay sum={},  id={}", sum, id);
            Investor investor = authenticationService.getInvestor(id);
            logicService.payByInvestor(sum, investor);
            response =  new Response(true, investor.getBalance(), 200, "OK");
        }
        catch(InvestorNotFoundException e){
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
            response = new Response(false, null, 403, "Forbidden");
        }
        return response;
    }
}
