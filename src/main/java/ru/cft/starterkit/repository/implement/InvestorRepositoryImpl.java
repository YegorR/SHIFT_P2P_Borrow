package ru.cft.starterkit.repository.implement;

//import com.sun.tools.jdeps.InverseDepsAnalyzer;
import org.springframework.stereotype.Repository;
import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.entity.ServerOffer;
import ru.cft.starterkit.exception.InvestorNotFoundException;
import ru.cft.starterkit.repository.InvestorRepository;

import java.util.*;

@Repository
public class InvestorRepositoryImpl implements InvestorRepository {
    private Map<Double, List<Investor>> groups = new TreeMap<>();
    private Set<Investor> notOffer = new HashSet<>();
    private Set<Investor> investors = new HashSet<>();

    @Override
    public boolean add(Investor investor){
        notOffer.add(investor);
        investors.add(investor);
        return true;
    }

    @Override
    public Investor get (String login) throws InvestorNotFoundException{
        Iterator<Investor> iter = investors.iterator();
        while(iter.hasNext()){
            Investor inv = iter.next();
            if (inv.getLogin().equals(login)) return inv;
        }

        throw new InvestorNotFoundException("Инвестор "+login+" не найден");
    }

    @Override
    public void toGroup(String login) throws InvestorNotFoundException{
        Investor investor = get(login);

        Iterator<List<Investor>> iter_g = groups.values().iterator();
        while(iter_g.hasNext()){
            iter_g.next().remove(investor);
        }

        if (investor.getOffer() == null){
            notOffer.add(investor);
            return;
        }

        notOffer.remove(investor);
        double percent = investor.getOffer().getPercent();
        List<Investor> group = groups.get(percent);
        if (group == null){
            group = new LinkedList();
            groups.put(percent, group);
        }

        for(int i=0; i < group.size();++i){
            if (group.get(i).getOffer().getSum() <= investor.getOffer().getSum()){
                group.add(i, investor);
                return;
            }
        }
        group.add(investor);
    }

    @Override
    public ServerOffer createServerOffer(double sum, int term){
        Iterator<Double> per_iter = groups.keySet().iterator();
        while(per_iter.hasNext()){
            double percent = per_iter.next();
            ArrayList<Investor> canditats = new ArrayList<>();
            Iterator<Investor> iter = groups.get(percent).iterator();
            L: while(iter.hasNext()){
                Investor inv = iter.next();
                canditats.add(inv);
                double part = sum / canditats.size();
                for(int i=0; i<canditats.size(); ++i){
                    if(canditats.get(i).getOffer().getSum()<part) continue L;
                }
                return new ServerOffer(canditats, part, percent, term, sum);
            }
        }
        return null;
    }

    @Override
    public ArrayList<Investor> showAll(){
        return new ArrayList<>(investors);
    }
}
