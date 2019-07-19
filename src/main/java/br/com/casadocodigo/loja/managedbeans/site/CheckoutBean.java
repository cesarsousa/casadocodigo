package br.com.casadocodigo.loja.managedbeans.site;

import java.io.IOException;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.casadocodigo.loja.models.ShoppingCart;

@Model
public class CheckoutBean {

	private SystemUser systemUser = new SystemUser();

	@Inject
	private SystemUserDAO systemUserDAO;
	
	@Inject
	private ShoppingCart cart;

	public SystemUser getSystemUser() {
		return systemUser;
	}

	public void setSystemUser(SystemUser systemUser) {
		this.systemUser = systemUser;
	}

	@Transactional
	public void checkout() throws IOException {
		systemUserDAO.save(systemUser);
	}

}
