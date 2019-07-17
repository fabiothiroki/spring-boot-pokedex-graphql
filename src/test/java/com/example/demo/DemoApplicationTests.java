package com.example.demo;

import com.example.demo.pokemon.PokemonRepository;
import com.graphql.spring.boot.test.GraphQLTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@GraphQLTest
public class DemoApplicationTests {

	@MockBean
	private PokemonRepository pokemonRepository;

	@Test
	public void contextLoads() {
	}

}
