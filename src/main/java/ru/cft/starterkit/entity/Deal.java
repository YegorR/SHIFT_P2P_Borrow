package ru.cft.starterkit.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class Deal {
    private ServerOffer serverOffer;
    private final Calendar start;
    private final Calendar deadline;
    private boolean expired = false;

    public Deal(ServerOffer serverOffer){
        this.serverOffer = serverOffer;
        start = new GregorianCalendar();
        deadline = new GregorianCalendar();
        deadline.add(Calendar.MONTH, serverOffer.getTerm());
    }

    public ServerOffer getServerOffer() {
        return serverOffer;
    }

    public Calendar getStart() {
        return start;
    }

    public Calendar getDeadline() {
        return deadline;
    }

    public boolean isExpired() {
        return expired;
    }
}
