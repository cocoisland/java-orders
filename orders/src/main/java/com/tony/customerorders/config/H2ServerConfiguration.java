package com.tony.customerorders.config;

import java.sql.SQLException;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class H2ServerConfiguration {

	// provide TCP port connection between H2 server and client application
	@Value("${h2.tcp.port:9092}")
	private String h2TcpPort;
	
	// web port connection betw H2 Server and Rest api
	@Value("${h2.web.port:8082}")
	private String webPort;
	
	/**
     * TCP connection to connect with SQL clients to the embedded h2 database.
     * <p>
     * Connect to "jdbc:h2:tcp://localhost:9092/mem:testdb", username "sa", password empty.
     *
     * @return The created TcpServer needed to access H2.
     * @throws SQLException If the server cannot be created.
     */
	@Bean
	@ConditionalOnExpression("${h2.tcp.enabled:true}")
	public Server h2TcpServer() throws SQLException {
		
		return Server.createTcpServer("-tcp","-tcpAllowOthers","-tcpPort",h2TcpPort).start();
	}
	
}
