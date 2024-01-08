package com.mifel.apirest.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mifel.apirest.model.AuthenticationReq;
import com.mifel.apirest.model.Poke;
import com.mifel.apirest.model.TokenInfo;
import com.mifel.apirest.service.JwtUtilService;
import com.mifel.apirest.service.PokeService;



@RestController
@RequestMapping("api/poke")
public class PokeController {
	
	@Autowired
	private PokeService pokeService;
	
	@Autowired
	 private AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService usuarioDetailsService;
	
	@Autowired
	 private JwtUtilService jwtUtilService;
	
	private static final Logger logger = LoggerFactory.getLogger(PokeController.class);
	
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping
	public Poke createPoke(@Validated @RequestBody Poke poke) {
		
		return pokeService.createPoke(poke);
	}
	
	@GetMapping
	public List<Poke> getAllPokes() {
		
		return pokeService.getAllPokes();
	}
	
	@GetMapping("{id}")
	public Poke searchPokeById(@PathVariable("id") int id) {
		
		return pokeService.getPokeById(id);
	}
	
	@DeleteMapping("{id}")
	public void deletePokeById(@PathVariable("id") int id) {
		
		pokeService.deletePoke(id);
	}
	
	@GetMapping("/getApiExterna")
	public String getApiExterna() {
		
		return pokeService.getApiExterna();
	}
	
	@GetMapping("/getCadenaCifrada/{cadena}")
	public String getCadenaCifrada(@PathVariable("cadena") String cadena) {
		
		return pokeService.cifrarCadena(cadena);
	}
	
	@GetMapping("/getCadenaDescifrada/{cadena}")
	public String getCadenaDescifrada(@PathVariable("cadena") String cadena) {
		
		return pokeService.cifrarCadena(cadena);
	}
	
	
	@PostMapping("/publico/authenticate")
	public ResponseEntity<TokenInfo> authenticate(@RequestBody AuthenticationReq authenticationReq) {
	   logger.info("Autenticando al usuario {}", authenticationReq.getUsuario());
	
	  authenticationManager.authenticate(
	      new UsernamePasswordAuthenticationToken(authenticationReq.getUsuario(),
	          authenticationReq.getClave()));
	
	  final UserDetails userDetails = usuarioDetailsService.loadUserByUsername(
	      authenticationReq.getUsuario());
	
	  final String jwt = jwtUtilService.generateToken(userDetails);
	
	  return ResponseEntity.ok(new TokenInfo(jwt));
	}
}















