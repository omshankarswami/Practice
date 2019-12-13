package com.swami.dev.conf;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

public class AuthServerConfigurations extends WebSecurityConfigurerAdapter
								implements AuthorizationServerConfigurer{

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer client) throws Exception {
		// TODO Auto-generated method stub
		client.inMemory().withClient(clientId:"web").secrete
		withClient(clientId:"mobile").secrete("webpass").scopes("READ","WRITE");
		
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
