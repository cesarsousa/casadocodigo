package br.com.casadocodigo.loja.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.casadocodigo.loja.daos.SecurityDAO;
import br.com.casadocodigo.loja.models.SystemUser;

@Model
public class CurrentUser {
	
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private SecurityDAO securityDAO;
	
	private SystemUser systemUser;
	
	public SystemUser get(){
		return this.systemUser;
	}
	
	public boolean hasRole(String name) {
		return request.isUserInRole(name);
	}
	
	@PostConstruct
	private void loadSystemUser() {
		Principal principal = request.getUserPrincipal();
		if (principal != null) {
			this.systemUser = securityDAO.loadUserByUsername(principal.getName());
		}
	}
}
