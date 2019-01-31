package ru.cft.starterkit.repository.implement;

import org.springframework.stereotype.Repository;
import ru.cft.starterkit.entity.Borrower;
import ru.cft.starterkit.entity.Investor;
import ru.cft.starterkit.entity.User;
import ru.cft.starterkit.repository.UserRepositiry;

import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepositiry {

    private Map<UUID, Borrower> borrowerMap = new HashMap<>();
    private Map<UUID, Investor> investorMap = new HashMap<>();
    @Override
    public UUID addUser(User user) {
        if (user.getClass().equals(Borrower.class)){
            if(borrowerMap.values().contains(user)) return null;

            UUID id = UUID.randomUUID();
            borrowerMap.put(id, (Borrower)user);
            return id;
        }
        else if(user.getClass().equals(Investor.class)){
            if(investorMap.values().contains(user)) return null;

            UUID id = UUID.randomUUID();
            investorMap.put(id, (Investor) user);
            return id;
        }
        else return null;
    }

    @Override
    public Borrower getBorrower(UUID uuid) {
        return borrowerMap.get(uuid);
    }

    @Override
    public Investor getInvestor(UUID uuid) {
        return investorMap.get(uuid);
    }

    @Override
    public UUID getUUID(String login, String password, boolean isInvestor){
        if(isInvestor){
            Set<Map.Entry<UUID, Investor>> entryes = investorMap.entrySet();
            Iterator<Map.Entry<UUID, Investor>> iter = entryes.iterator();
            while(iter.hasNext()){
                Map.Entry<UUID, Investor> entry = iter.next();
                if((entry.getValue().getLogin().equals(login)) && (entry.getValue().getPassword().equals((password)))){
                    return entry.getKey();
                }
            }
        }
        else{
            Set<Map.Entry<UUID, Borrower>> entryes = borrowerMap.entrySet();
            Iterator<Map.Entry<UUID, Borrower>> iter = entryes.iterator();
            while(iter.hasNext()){
                Map.Entry<UUID, Borrower> entry = iter.next();
                if((entry.getValue().getLogin().equals(login)) && (entry.getValue().getPassword().equals((password)))){
                    return entry.getKey();
                }
            }
        }
        return null;
    }
}
