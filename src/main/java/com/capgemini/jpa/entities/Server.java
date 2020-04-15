package com.capgemini.jpa.entities;

import java.time.LocalDateTime;

import javax.persistence.*;

@Entity
public class Server {

	@Id
	@SequenceGenerator(name = "SERVER_ID_GENERATOR", sequenceName = "SERVER_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SERVER_ID_GENERATOR")
	private Integer id;

	@Column(nullable=false)
	private String name;
	@Column(nullable=false)
	private String ip;

	@Version
	private Long version;
	private LocalDateTime lastUpdateDate;

	protected Server() {
		super();
	}

	public Server(String name, String ip) {
		super();
		this.name = name;
		this.ip = ip;
	}

	public String getName() {
		return name;
	}

	public void setName(String serverName) {
		this.name = serverName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Integer getId() {
		return id;
	}

	public Long getVersion(){
		return version;
	}
	
	public LocalDateTime getLastUpdateDate(){
		return lastUpdateDate;
	}

	@PreUpdate
	public void preUpdate() {
		lastUpdateDate = LocalDateTime.now();
	}
}
