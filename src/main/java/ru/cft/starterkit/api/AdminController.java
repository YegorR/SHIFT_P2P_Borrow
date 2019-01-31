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
import ru.cft.starterkit.repository.BorrowerRepository;
import ru.cft.starterkit.repository.TimerRepository;
import ru.cft.starterkit.service.LogicService;

import java.util.ArrayList;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private LogicService logicService;
    private BorrowerRepository borrowerRepository;
    private TimerRepository timerRepository;
    private static String TEST_LOGIN = "b1";

    @Autowired
    public AdminController(LogicService logicService, BorrowerRepository borrowerRepository,
                           TimerRepository timerRepository){
        this.logicService = logicService;
        this.borrowerRepository = borrowerRepository;
        this.timerRepository = timerRepository;
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

    @RequestMapping(
            method = RequestMethod.POST,
            path = "/time",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public double time(
            @RequestParam(name = "expired") boolean expired
    ) throws BorrowerNotFoundException{
        logicService.timeOn(borrowerRepository.getBorrower(TEST_LOGIN));
        return logicService.getBalanceBorrower(borrowerRepository.getBorrower(TEST_LOGIN));
    }
}
