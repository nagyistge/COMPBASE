package uzuzjmd.api_services;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

public class UZUZWebservice {
	protected Client client;

	public UZUZWebservice() {
		ClientConfig clientConfig = new DefaultClientConfig();
		client = Client.create(clientConfig);
	}

}
