package br.com.sura.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.sura.error.CustomException;
import br.com.sura.model.Cliente;
import br.com.sura.repository.IClienteRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	private final IClienteRepository repository;
	
	@Autowired
	public CustomUserDetailsService(IClienteRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Cliente user = Optional.ofNullable(repository.findByEmailIgnoreCaseContaining(username))
				.orElseThrow(() -> new CustomException("Usuário não encontrado"));
		
		List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");
		List<GrantedAuthority> authorityListUser = AuthorityUtils.createAuthorityList("ROLE_USER");

		return new User(user.getEmail(), user.getSenha(), user.isAdmin() ? authorityListAdmin : authorityListUser);
	}

}
