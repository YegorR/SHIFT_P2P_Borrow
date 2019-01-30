package ru.cft.starterkit.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.starterkit.entity.Deal;
import ru.cft.starterkit.entity.ServerOffer;
import ru.cft.starterkit.exception.BorrowerNotFoundException;
import ru.cft.starterkit.exception.ServerOfferNotFoundException;
import ru.cft.starterkit.repository.BorrowerRepository;
import ru.cft.starterkit.service.LogicService;

import java.util.UUID;

@RestController
@RequestMapping("/borrower")
public class BorrowerController {

    private static String TEST_LOGIN = "b1";

    private final LogicService logicService;
    private final BorrowerRepository borrowerRepository;

    @Autowired
    public BorrowerController(LogicService logicService, BorrowerRepository borrowerRepository){
        this.logicService = logicService;
        this.borrowerRepository = borrowerRepository;
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
            method = RequestMethod.POST,
            path = "/ok",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public boolean consent (
            @RequestParam(name = "server_offer_id") UUID server_offer_id,
            @RequestParam(name = "id") int id) throws BorrowerNotFoundException, ServerOfferNotFoundException {
        logicService.createDeal(server_offer_id, borrowerRepository.getBorrower(TEST_LOGIN));
        return true;
    }
}