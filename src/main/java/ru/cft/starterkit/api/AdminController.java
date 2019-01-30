package ru.cft.starterkit.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.entity.Offer;
import ru.cft.starterkit.exception.BorrowerNotFoundException;
import ru.cft.starterkit.exception.InvestorNotFoundException;
import ru.cft.starterkit.service.LogicService;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private LogicService logicService;

    @Autowired
    public AdminController(LogicService logicService){
        this.logicService = logicService;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/add/investor",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public Investor addInvestor(
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password)  throws InvestorNotFoundException {
        return logicService.createInvestor(login, password);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/add/borrower",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public Borrower addBorrower(
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password)  throws BorrowerNotFoundException {
        return logicService.createBorrower(login, password);
    }

    @RequestMapping(
            method = RequestMethod.GET,
            path = "/show/investor/all",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public ArrayList<Investor> showAllInvestors(){
        return logicService.showAllInvestors() ;
    }
}
