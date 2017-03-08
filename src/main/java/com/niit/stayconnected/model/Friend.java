package com.niit.stayconnected.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "Friend")
@Component
public class Friend {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private int friendId;

@Column(name="from_id")
private String fromId;

@Column(name="to_id")
private String toId;

@Column
private char friendStatus;//  'A'  , 'D',  'P'

public int getFriendId() {
	return friendId;
}

public void setFriendId(int friendId) {
	this.friendId = friendId;
}

public String getFromId() {
	return fromId;
}

public void setFromId(String fromId) {
	this.fromId = fromId;
}

public String getToId() {
	return toId;
}

public void setToId(String toId) {
	this.toId = toId;
}

public char getFriendStatus() {
	return friendStatus;
}

public void setFriendStatus(char friendStatus) {
	this.friendStatus = friendStatus;
}
}
