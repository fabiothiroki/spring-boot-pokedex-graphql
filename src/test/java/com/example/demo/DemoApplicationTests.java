package com.example.demo;

import com.example.demo.pokemon.Pokemon;
import com.example.demo.pokemon.PokemonRepository;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTest;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@GraphQLTest
public class DemoApplicationTests {

	@Autowired
	private GraphQLTestTemplate graphQLTestTemplate;

	@MockBean
	private PokemonRepository pokemonRepository;

	@Test
	public void getById() throws IOException {
		Pokemon pokemon = new Pokemon(1L, "Pikachu");
		when(pokemonRepository.findById(any()))
				.thenReturn(Optional.of(pokemon));

		GraphQLResponse response =
				graphQLTestTemplate.postForResource("get-pokemon-by-id.graphql");

		assertTrue(response.isOk());
		assertEquals("1", response.get("$.data.pokemon.id"));
		assertEquals("Pikachu", response.get("$.data.pokemon.name"));
	}

}
