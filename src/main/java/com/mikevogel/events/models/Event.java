package com.mikevogel.events.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="events")
public class Event {
	
	 @Id
	 @GeneratedValue
	 private Long id;
	 @Size(min=1, message="Event name cannot be blank")
	 private String name;
	 @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date date;
	 @Size(min=1, message="Location cannot be blank")
	 private String location;
	 @Size(min=1, message="State cannot be blank")
	 private String state;
	 private String host;
	 @Column(updatable=false)
	 private Date createdAt;
	 private Date updatedAt;
	 
	 @OneToMany(mappedBy="event", fetch= FetchType.LAZY)
	 private List<Message> messages;
	 
	 @ManyToMany(fetch=FetchType.LAZY)
	 @JoinTable(
			 name="events_users",
			 joinColumns = @JoinColumn(name="event_id"),
			 inverseJoinColumns = @JoinColumn(name="user_id")
			 )
	 private List<User> user;
	 
	 public Event() {
		 
	 }
	 
	 
	 
	 public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public Date getDate() {
		return date;
	}



	public void setDate(Date date) {
		this.date = date;
	}



	public String getLocation() {
		return location;
	}



	public void setLocation(String location) {
		this.location = location;
	}



	public String getState() {
		return state;
	}



	public void setState(String state) {
		this.state = state;
	}



	public String getHost() {
		return host;
	}



	public void setHost(String host) {
		this.host = host;
	}



	public Date getCreatedAt() {
		return createdAt;
	}



	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}



	public Date getUpdatedAt() {
		return updatedAt;
	}



	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}



	public List<Message> getMessages() {
		return messages;
	}



	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}



	public List<User> getUser() {
		return user;
	}



	public void setUser(List<User> user) {
		this.user = user;
	}



	@PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
}
