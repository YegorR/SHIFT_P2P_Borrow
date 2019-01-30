package ru.cft.starterkit.service.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.cft.starterkit.data.PercentData;
import ru.cft.starterkit.entity.*;
import ru.cft.starterkit.exception.BorrowerNotFoundException;
import ru.cft.starterkit.exception.IncorrectSumException;
import ru.cft.starterkit.exception.InvestorNotFoundException;
import ru.cft.starterkit.exception.ServerOfferNotFoundException;
import ru.cft.starterkit.repository.BorrowerRepository;
import ru.cft.starterkit.repository.InvestorRepository;
import ru.cft.starterkit.repository.ServerOffersRepository;
import ru.cft.starterkit.service.LogicService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

@Service
public class LogicServiceImpl implements LogicService{

    private final InvestorRepository investorRepository;
    private final ServerOffersRepository serverOffersRepository;
    private final BorrowerRepository borrowerRepository;

    @Autowired
    public LogicServiceImpl(InvestorRepository investorRepository, ServerOffersRepository serverOffersRepository, BorrowerRepository borrowerRepository){
        this.investorRepository = investorRepository;
        this.serverOffersRepository = serverOffersRepository;
        this.borrowerRepository = borrowerRepository;
    }

    @Override
    public Investor createInvestor(String login, String password) throws InvestorNotFoundException {
        investorRepository.add(new Investor(login, password));
        return investorRepository.get(login);
    }

    @Override
    public Borrower createBorrower(String login, String password) throws BorrowerNotFoundException{
        borrowerRepository.addBorrower(new Borrower(login, password));
        return borrowerRepository.getBorrower(login);
    }

    @Override
    public Investor getInvestor(String login) throws InvestorNotFoundException{
        return investorRepository.get(login);
    }

    @Override
    public ArrayList<Investor> showAllInvestors(){
        return investorRepository.showAll();
    }

    @Override
    public Offer createOffer(double sum, Investor investor)throws IOException, IncorrectSumException, InvestorNotFoundException {
        PercentData pd = PercentData.getInstance();
        double percent = pd.getPercent(sum);
        investor.setOffer(new Offer(sum, percent));
        investor.setBalance(investor.getBalance() + sum);
        investorRepository.toGroup(investor.getLogin());
        return investor.getOffer();
    }

    public ServerOffer createServerOffer(double sum, int term){
        /*
            1. Репозиторий инвесторов сгруппирован по процентам, а в каждой группе сортировка по сумме
            2. Внешний цикл - перебор групп процентов
            3. Внутренний цикл - поочередно составляем список из инвесторов, пока они не кончились/
                не образовалась группа, где каждый, внеся равную долю, не покроет всю заявленную сумму
            4. Кончились люди - смотрим другую процентную группу
            5. Образовалась группа - создаём ServerOffer
            6. Кончились все группы - вариант не найден */

        ServerOffer serverOffer = investorRepository.createServerOffer(sum, term);
        if (serverOffer == null) return null;
        serverOffersRepository.add(serverOffer);
        return serverOffer;
    }

    @Override
    public Deal createDeal(UUID id, Borrower borrower) throws ServerOfferNotFoundException {
        ServerOffer serverOffer = serverOffersRepository.get(id);
        Deal deal = new Deal(serverOffer);
        borrower.setDeal(deal);
        borrower.setBalance(borrower.getBalance() - serverOffer.getSum()*(1 + serverOffer.getPercent()));
        return  deal;
    }
}
