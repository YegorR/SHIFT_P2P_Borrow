package ru.cft.starterkit.api;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.ServerOffer;
import ru.cft.starterkit.exception.BorrowerNotFoundException;
import ru.cft.starterkit.exception.ServerOfferNotFoundException;
import ru.cft.starterkit.service.AuthenticationService;
import ru.cft.starterkit.service.LogicService;

import java.util.UUID;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    private final LogicService logicService;
    private final AuthenticationService authenticationService;

    private static final Logger log = LoggerFactory.getLogger(BorrowerController.class);


    @Autowired
    public BorrowerController(LogicService logicService, AuthenticationService authenticationService){
        this.logicService = logicService;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/ask",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public Response ask(
            @RequestParam(name = "sum") Double sum,
            @RequestParam(name = "term") Integer term,
            @RequestParam(name = "id") UUID id){
        Response response;
        try {
            log.info("Запрос: POST borrower/ask sum={}, term={}, id={}", sum, term, id);
            authenticationService.getBorrower(id);
        }
        catch(BorrowerNotFoundException e){
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
            return new Response(false, null, 403, "Forbidden");
        }

        ServerOffer serverOffer = logicService.createServerOffer(sum, term);
        if (serverOffer == null) response = new Response(true, null, 200, "OK");
        else response = new Response(true, logicService.createServerOffer(sum, term).getServerOfferForUser(), 200, "OK");
        return response;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/ok",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public Response consent (
            @RequestParam(name = "server_offer_id") UUID server_offer_id,
            @RequestParam(name = "id") UUID id) throws ServerOfferNotFoundException {
        try {
            log.info("Запрос: POST borrower/ok server_offer_id={},  id={}", server_offer_id, id);
            Borrower borrower = authenticationService.getBorrower(id);
            boolean answer = logicService.createDeal(server_offer_id, borrower);
            return new Response(true, answer, 200, "OK");
        }
        catch(BorrowerNotFoundException e){
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
            return new Response(false, null, 403, "Forbidden");
        }
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/balance",
            produces = "application/json"
    )
    public Response balance (
            @RequestParam(name = "id") UUID id){
        try {
            log.info("Запрос: GET borrower/balance ,  id={}", id);
            Borrower borrower = authenticationService.getBorrower(id);
            return new Response(true, borrower.getBalance(), 200, "OK");
        }
        catch(BorrowerNotFoundException e){
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
            return new Response(false, null, 403, "Forbidden");
        }
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
        try {
            log.info("Запрос: POST borrower/pay sum={},  id={}", sum, id);
            Borrower borrower = authenticationService.getBorrower(id);
            logicService.payByBorrower(sum, borrower);
            return new Response(true, borrower.getBalance(), 200, "OK");
        }
        catch(BorrowerNotFoundException e){
            //throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
            return new Response(false, null, 403, "Forbidden");
        }
    }
}