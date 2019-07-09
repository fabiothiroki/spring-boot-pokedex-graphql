package com.example.demo.Pokemon;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

@RestController
public class PokemonController {

    @Value("${spring.datasource.url}")
    private String dbUrl;

    @Autowired
    private PokemonRepository repository;

    @RequestMapping("/db")
    String db(Map<String, Object> model) {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Pokemon>> typeReference = new TypeReference<List<Pokemon>>(){};
        InputStream inputStream = TypeReference.class.getResourceAsStream("/pokedex.json");
        try {
            repository.deleteAll();
            List<Pokemon> pokemons = mapper.readValue(inputStream,typeReference);
            for (Pokemon pokemon : pokemons) {
                repository.save(pokemon);
            }
            model.put("records", pokemons);
            return "db";
        } catch (IOException e){
            System.out.println("Unable to save pokemon: " + e.getMessage());
            return "error";
        }
    }
}
