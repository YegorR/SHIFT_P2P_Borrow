package ru.cft.starterkit.repository.implement;

import org.springframework.stereotype.Repository;
import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.Deal;
import ru.cft.starterkit.exception.BorrowerNotFoundException;
import ru.cft.starterkit.exception.DealNotFoundException;
import ru.cft.starterkit.repository.BorrowerRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

@Repository
public class BorrowerRepositoryImpl implements BorrowerRepository {
    private HashSet<Borrower> storage = new HashSet<>();
    private HashMap<String, Deal> deals = new HashMap<>();

    @Override
    public void addBorrower(Borrower borrower){
        storage.add(borrower);
    }

    @Override
    public Borrower getBorrower(String login) throws BorrowerNotFoundException {
        Iterator<Borrower> iter = storage.iterator();
        while (iter.hasNext()) {
            Borrower borrower = iter.next();
            if (borrower.getLogin().equals(login)) return borrower;
        }
        throw new BorrowerNotFoundException();
    }

    @Override
    public void addDeal(String login, Deal deal) throws BorrowerNotFoundException{
        Borrower borrower = getBorrower(login);
        deals.put(login, deal);
    }

    @Override
    public Deal getDeal(String login) throws BorrowerNotFoundException, DealNotFoundException {
        Borrower borrower = getBorrower(login);
        Deal d = deals.get(login);
        if (d == null) throw new DealNotFoundException();
        return d;
    }

}
