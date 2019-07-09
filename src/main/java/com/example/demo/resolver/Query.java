package com.example.demo.resolver;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.example.demo.pokemon.Pokemon;
import com.example.demo.pokemon.PokemonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class Query implements GraphQLQueryResolver {

    @Autowired
    private PokemonRepository repository;

    public Pokemon getPokemon(Long id) {
        Pokemon pokemon = repository.findById(id).orElse(null);
        return pokemon;
    }

}
