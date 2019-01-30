package ru.cft.starterkit.repository;

import ru.cft.starterkit.entity.ServerOffer;
import ru.cft.starterkit.exception.ServerOfferNotFoundException;

import java.util.UUID;

public interface ServerOffersRepository {
    void add(ServerOffer serverOffer);
    ServerOffer get(UUID id) throws ServerOfferNotFoundException;
}
