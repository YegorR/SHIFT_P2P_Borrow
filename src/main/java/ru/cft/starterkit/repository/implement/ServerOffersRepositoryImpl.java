package ru.cft.starterkit.repository.implement;

import org.springframework.stereotype.Repository;
import ru.cft.starterkit.entity.ServerOffer;
import ru.cft.starterkit.exception.ServerOfferNotFoundException;
import ru.cft.starterkit.repository.ServerOffersRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class ServerOffersRepositoryImpl implements ServerOffersRepository {
    private Map<UUID, ServerOffer> storage = new HashMap<>();

    @Override
    public void add(ServerOffer serverOffer){
        storage.put(serverOffer.getId(), serverOffer);
    }

    @Override
    public ServerOffer get(UUID id) throws  ServerOfferNotFoundException{
        ServerOffer so = storage.get(id);
        if (so==null){
            throw new ServerOfferNotFoundException("Server offer not found");
        }
        return so;
    }
}
