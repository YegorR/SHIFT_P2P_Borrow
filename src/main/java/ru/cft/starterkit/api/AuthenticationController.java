package ru.cft.starterkit.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.exception.BorrowerNotFoundException;
import ru.cft.starterkit.exception.InvestorNotFoundException;
import ru.cft.starterkit.repository.BorrowerRepository;
import ru.cft.starterkit.repository.InvestorRepository;
import ru.cft.starterkit.repository.TimerRepository;
import ru.cft.starterkit.service.AuthenticationService;
import ru.cft.starterkit.service.LogicService;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    private AuthenticationService authenticationService;
    private BorrowerRepository borrowerRepository;
    private InvestorRepository investorRepository;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService, BorrowerRepository borrowerRepository){
        this.authenticationService = authenticationService;
        this.borrowerRepository = borrowerRepository;
    }

    @RequestMapping(
            method = RequestMethod.POST,
            path = "",
            consumes = "application/x-www-form-urlencoded",
            produces = "application/json"
    )
    public UUID login(
            @RequestParam(name = "login") String login,
            @RequestParam(name = "password") String password,
            @RequestParam(name = "investor") boolean investor)  throws InvestorNotFoundException {
        try {
            return authenticationService.login(login, password, investor);
        }
        catch (BorrowerNotFoundException|InvestorNotFoundException e ){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, e.getMessage(), e);
        }

    }


}
