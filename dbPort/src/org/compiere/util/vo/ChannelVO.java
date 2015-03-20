package org.compiere.util.vo;

import java.sql.Timestamp;

import org.compiere.model.MHL7MessageConf;

public class ChannelVO {
	private String status;
	private String name;
	private int revision;
	private Timestamp lastDeployed;
	private int received;
	private int filtered;
	private int queued;
	private int sent;
	private int errored;
	private int alerted;
	private String connection;
	private String channelID;
	private MHL7MessageConf conf;
	
	public ChannelVO() {
		reset();
	}
	
	public void reset() {
		revision = 0;
		received = 0;
		filtered = 0;
		queued = 0;
		sent = 0;
		errored = 0;
		alerted = 0;
	}
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRev() {
		return revision;
	}
	public void setRev(int rev) {
		revision = rev;
	}
	public Timestamp getLastDeployed() {
		return lastDeployed;
	}
	public void setLastDeployed(Timestamp lastDeployed) {
		this.lastDeployed = lastDeployed;
	}
	public int getReceived() {
		return received;
	}
	public void setReceived(int received) {
		this.received = received;
	}
	public int getFiltered() {
		return filtered;
	}
	public void setFiltered(int filtered) {
		this.filtered = filtered;
	}
	public int getQueued() {
		return queued;
	}
	public void setQueued(int queued) {
		this.queued = queued;
	}
	public int getSent() {
		return sent;
	}
	public void setSent(int sent) {
		this.sent = sent;
	}
	public int getErrored() {
		return errored;
	}
	public void setErrored(int errored) {
		this.errored = errored;
	}
	public int getAlerted() {
		return alerted;
	}
	public void setAlerted(int alerted) {
		this.alerted = alerted;
	}
	public String getConnection() {
		return connection;
	}
	public void setConnection(String connection) {
		this.connection = connection;
	}

	public void setChannelID(String channelID) {
		this.channelID = channelID;
	}

	public String getChannelID() {
		return channelID;
	}

	public void setConf(MHL7MessageConf conf) {
		this.conf = conf;
	}

	public MHL7MessageConf getConf() {
		return conf;
	}
}
