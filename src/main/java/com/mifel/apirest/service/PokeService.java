package com.mifel.apirest.service;

import java.util.List;
import java.util.Optional;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.mifel.apirest.controller.PokeController;
import com.mifel.apirest.model.Poke;
import com.mifel.apirest.repository.PokeRepository;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Component
public class PokeService {

	@Autowired
	private PokeRepository pokeRepository;
	
	private static final String llave = "92AE31A79FEEB2A3";
	private static final String vectorInicial = "0123456789ABCDEF";
	
	
	
	public Poke createPoke(Poke poke) {
		
		return pokeRepository.save(poke);
	}
	
	public Poke getPokeById(int id) {
		
		Optional<Poke> optionalPoke = pokeRepository.findById(id);
		return optionalPoke.get();
	}
	
	public List<Poke> getAllPokes() {
		return pokeRepository.findAll();
	}
	
	public void deletePoke(int id) {
		pokeRepository.deleteById(id);
	}
	
	public String getApiExterna() {
		String uri = "https://pokeapi.co/api/v2/pokemon/ditto";
		
		RestTemplate restTemplate = new RestTemplate();
		String result = restTemplate.getForObject(uri, String.class);
		return result;
	}
	
	public String cifrarCadena(String cadena) {

		try {
	        IvParameterSpec iv = new IvParameterSpec(vectorInicial.getBytes("UTF-8"));
	        SecretKeySpec skeySpec = new SecretKeySpec(llave.getBytes(), "AES");
	 
	        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
	 
	        byte[] encrypted = cipher.doFinal(cadena.getBytes());
	        
	        return new String(Base64.encodeBase64String(encrypted));
	    } catch (Exception ex) {
	        ex.printStackTrace();
	    }
		
		return null;
	}
	
	public String descifrarCadena(String cadena) {
		try {
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            SecretKeySpec skeySpec = new SecretKeySpec(llave.getBytes(), "AES");
            IvParameterSpec ivParameterSpec = new IvParameterSpec(vectorInicial.getBytes());
            byte[] enc = Base64.decodeBase64(cadena);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);
            byte[] decrypted = cipher.doFinal(enc);
            return new String(decrypted);
		} catch (Exception ex) {
	        ex.printStackTrace();
	    }
		
		return null;
	}
	
	
}
