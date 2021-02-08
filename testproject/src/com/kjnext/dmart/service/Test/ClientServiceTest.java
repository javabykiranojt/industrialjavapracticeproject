package com.kjnext.dmart.service.Test;

import junit.framework.TestCase;

import com.kjnext.dmart.hibernate.Clients;
import com.kjnext.dmart.service.ClientService;
import com.kjnext.dmart.service.Impl.ClientServiceImpl;

public class ClientServiceTest extends TestCase {
	ClientService clientService;

	Clients clients;

	@Override
	protected void setUp() throws Exception {
		clientService = new ClientServiceImpl();
		clients = new Clients();
	}

	@Override
	protected void tearDown() throws Exception {
		clientService = null;
		clients = null;
	}

	public void testaddClient() {

		clients.setClientId(2222);
		clients.setClientName("MacD");
		clientService.addClient(clients);
		Clients clients1 = clientService.loadClientDetails(2222);
		assertEquals(clients1.getClientName(), "MacD");
	}

	/*public void testupdateClient() {

		clients.setClientId(1111);
		clients.setClientName("Paradise");
		clientService.updateClient(clients);
		Clients clients1 = clientService.loadClientDetails(1111);
		assertEquals(clients1.getClientName(), "Paradise");
	}*/
	/*public void testremoveClient() {

		clients.setClientId(2222);
		
		clientService.removeClient(clients);
		//Clients clients1 = clientService.loadClientDetails(2222);
		//assertEquals(clients1.getClientName(), "");
	}*/
}
