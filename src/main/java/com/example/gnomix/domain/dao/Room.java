package com.example.gnomix.domain.dao;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class Room {
    private final long id;
    private final String number;
    private final List<BedType> beds;
    private final int size;

    public Room(final String number, final List<BedType> beds) {
        if(beds==null){
            throw new  IllegalArgumentException("beds list can't be null");
        }
        this.id = UUID.randomUUID().getMostSignificantBits() & Long.MAX_VALUE;
        this.number = number;
        List<BedType> bedsField = new ArrayList<>(beds);
        this.beds = bedsField;
        this.size = this.beds.stream().mapToInt(BedType::getSize).sum();
    }
}
