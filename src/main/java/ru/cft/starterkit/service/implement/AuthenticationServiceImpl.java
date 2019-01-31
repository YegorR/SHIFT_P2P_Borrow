package ru.cft.starterkit.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.exception.BorrowerNotFoundException;
import ru.cft.starterkit.exception.InvestorNotFoundException;
import ru.cft.starterkit.repository.BorrowerRepository;
import ru.cft.starterkit.repository.InvestorRepository;
import ru.cft.starterkit.repository.UserRepositiry;
import ru.cft.starterkit.service.AuthenticationService;

import java.util.UUID;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepositiry userRepositiry;
    private final InvestorRepository investorRepository;
    private final BorrowerRepository borrowerRepository;

    @Autowired
    public AuthenticationServiceImpl(UserRepositiry userRepositiry, InvestorRepository investorRepository,
                                     BorrowerRepository borrowerRepository){
        this.userRepositiry = userRepositiry;
        this.investorRepository = investorRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public UUID login(String login, String password, boolean isInvestor) throws InvestorNotFoundException,
            BorrowerNotFoundException {
        UUID uuid = userRepositiry.getUUID(login, password, isInvestor);
        if (uuid == null){
            if (isInvestor) throw new InvestorNotFoundException();
            else throw new BorrowerNotFoundException();
        }
        return uuid;
    }

    public Borrower getBorrower(UUID uuid) throws BorrowerNotFoundException{
        Borrower borrower = userRepositiry.getBorrower(uuid);
        if (borrower == null){
            throw new BorrowerNotFoundException();
        }
        return borrower;
    }
    public Investor getInvestor(UUID uuid) throws InvestorNotFoundException{
        Investor investor = userRepositiry.getInvestor(uuid);
        if (investor == null){
            throw new InvestorNotFoundException();
        }
        return investor;
    }
}
