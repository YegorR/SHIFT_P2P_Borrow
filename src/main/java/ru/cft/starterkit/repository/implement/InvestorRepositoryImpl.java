package ru.cft.starterkit.repository.implement;

import com.sun.tools.jdeps.InverseDepsAnalyzer;
import org.springframework.stereotype.Repository;
import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.exception.InvestorNotFoundException;
import ru.cft.starterkit.repository.InvestorRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

@Repository
public class InvestorRepositoryImpl implements InvestorRepository {
    private ArrayList<Investor> list = new ArrayList();

    @Override
    public boolean add(Investor investor){
        Iterator<Investor> iter = list.iterator();
        while(iter.hasNext()){
            Investor inv = iter.next();
            if (inv.getLogin().equals(investor.getLogin())) return false;
        }
        list.add(investor);
        return true;
    }

    @Override
    public Investor get (String login) throws InvestorNotFoundException{
        Iterator<Investor> iter = list.iterator();
        while(iter.hasNext()){
            Investor inv = iter.next();
            if (inv.getLogin().equals(login)) return inv;
        }
        throw new InvestorNotFoundException("Инвестор "+login+"не найден");
    }

}
