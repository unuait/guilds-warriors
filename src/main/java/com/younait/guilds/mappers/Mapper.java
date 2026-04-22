package com.younait.guilds.mappers;

public interface Mapper<A,B>{
    B mapTo(A a);
    A mapFrom(B b);
}
