package com.example.gnomix.domain.dao;

public enum BedType {
    SINGLE(1),DOUBLE(2);
    private final int size;

    BedType( int size) {
        this.size = size;
    }
    public int getSize(){
        return this.size;
    }
}
