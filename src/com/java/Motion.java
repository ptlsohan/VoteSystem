package com.java;

import java.time.LocalDateTime;

public class Motion {
 private String name;
 private String motionState;
 private LocalDateTime openTime;
 private LocalDateTime closeTime;
public LocalDateTime getOpenTime() {
	return openTime;
}
public void setOpenTime(LocalDateTime openTime) {
	this.openTime = openTime;
}
public LocalDateTime getCloseTime() {
	return closeTime;
}
public void setCloseTime(LocalDateTime closeTime) {
	this.closeTime = closeTime;
}
public Motion(String name) {
	super();
	this.name = name;
	this.motionState = "Created";
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getMotionState() {
	return motionState;
}
public void setMotionState(String motionState) {
	this.motionState = motionState;
}
 
 
 
}
