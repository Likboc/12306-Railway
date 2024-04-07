package com.example.starter.cache.core;

@FunctionalInterface
public interface CacheLoader<T>{
    T load();
}
