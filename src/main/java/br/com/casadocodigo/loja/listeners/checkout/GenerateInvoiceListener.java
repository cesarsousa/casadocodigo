package br.com.casadocodigo.loja.listeners.checkout;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.casadocodigo.loja.daos.CheckoutDAO;
import br.com.casadocodigo.loja.models.Checkout;
import br.com.casadocodigo.loja.service.InvoiceGenerator;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "java:/jms/topics/checkoutsTopic")
	})
public class GenerateInvoiceListener implements MessageListener{
	
	@Inject
	private InvoiceGenerator invoiceGenerator;
	
	@Inject
	private CheckoutDAO checkoutDao;

	@Override
	public void onMessage(Message message) {
		TextMessage text = (TextMessage) message;
		try {
			Checkout checkout = checkoutDao.findByUuid(text.getText());
			invoiceGenerator.invoiceFor(checkout);
		} catch (JMSException e) {
			throw new RuntimeException("Problema na geracao da nota fiscal");
		}
		
	}

}
