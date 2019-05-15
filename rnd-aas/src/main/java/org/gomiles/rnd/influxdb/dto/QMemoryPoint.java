package org.gomiles.rnd.influxdb.dto;

import java.util.Date;

import org.gomiles.rnd.influxdb.model.MemoryPoint;

public class QMemoryPoint {

	private String name;
	private Integer free;
	private Integer used;
	private Integer buffer;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getFree() {
		return free;
	}

	public void setFree(Integer free) {
		this.free = free;
	}

	public Integer getUsed() {
		return used;
	}

	public void setUsed(Integer used) {
		this.used = used;
	}

	public Integer getBuffer() {
		return buffer;
	}

	public void setBuffer(Integer buffer) {
		this.buffer = buffer;
	}

	public MemoryPoint toMemoryPoint() {
		return new MemoryPoint().setBuffer(buffer).setFree(free).setName(name)
				.setTime(new Date(System.currentTimeMillis())).setUsed(used);
	}

}
