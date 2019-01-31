package ru.cft.starterkit.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.ServerOffer;
import ru.cft.starterkit.exception.BorrowerNotFoundException;
import ru.cft.starterkit.exception.ServerOfferNotFoundException;
import ru.cft.starterkit.repository.BorrowerRepository;
import ru.cft.starterkit.service.AuthenticationService;
import ru.cft.starterkit.service.LogicService;

import java.util.UUID;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    private final LogicService logicService;
    private final BorrowerRepository borrowerRepository;
    private final AuthenticationService authenticationService;

    @Autowired
    public BorrowerController(LogicService logicService, BorrowerRepository borrowerRepository,
                              AuthenticationService authenticationService){
        this.logicService = logicService;
        this.borrowerRepository = borrowerRepository;
        this.authenticationService = authenticationService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/ask",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public ServerOffer.ServerOfferForUser ask(
            @RequestParam(name = "sum") Double sum,
            @RequestParam(name = "term") Integer term,
            @RequestParam(name = "id") UUID id){
        try {
            authenticationService.getBorrower(id);
        }
        catch(BorrowerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }

        ServerOffer serverOffer = logicService.createServerOffer(sum, term);
        if (serverOffer == null) return null;
        return logicService.createServerOffer(sum, term).getServerOfferForUser();
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/ok",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public boolean consent (
            @RequestParam(name = "server_offer_id") UUID server_offer_id,
            @RequestParam(name = "id") UUID id) throws ServerOfferNotFoundException {
        try {
            Borrower borrower = authenticationService.getBorrower(id);
            logicService.createDeal(server_offer_id, borrower);
        }
        catch(BorrowerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }
        return true;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/balance",
            produces = "application/json"
    )
    public double balance (
            @RequestParam(name = "id") UUID id) throws BorrowerNotFoundException{
        try {
            Borrower borrower = authenticationService.getBorrower(id);
            return borrower.getBalance();
        }
        catch(BorrowerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/pay",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public double pay (
            @RequestParam(name = "sum") double sum,
            @RequestParam(name = "id") UUID id){
        try {
            Borrower borrower = authenticationService.getBorrower(id);
            logicService.payByBorrower(sum, borrower);
            return borrower.getBalance();
        }
        catch(BorrowerNotFoundException e){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, e.getMessage(), e);
        }
    }
}