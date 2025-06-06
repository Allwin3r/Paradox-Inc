package com.example.paradox.mapper;

public interface Mapper <From , To> {
    To map(From object);

    default To map(From fromObject , To toObject) {
        return toObject;
    }
}