package org.gomiles.rnd.influxdb.model;

import java.util.Date;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

import com.fasterxml.jackson.annotation.JsonFormat;

@Measurement(name = "memory")
public class MemoryPoint {

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSZ")
	@Column(name = "time")
	private Date time;
	@Column(name = "name")
	private String name;
	@Column(name = "free")
	private Integer free;
	@Column(name = "used")
	private Integer used;
	@Column(name = "buffer")
	private Integer buffer;

	public Date getTime() {
		return time;
	}

	public MemoryPoint setTime(Date time) {
		this.time = time;
		return this;
	}

	public String getName() {
		return name;
	}

	public MemoryPoint setName(String name) {
		this.name = name;
		return this;
	}

	public Integer getFree() {
		return free;
	}

	public MemoryPoint setFree(Integer free) {
		this.free = free;
		return this;
	}

	public Integer getUsed() {
		return used;
	}

	public MemoryPoint setUsed(Integer used) {
		this.used = used;
		return this;
	}

	public Integer getBuffer() {
		return buffer;
	}

	public MemoryPoint setBuffer(Integer buffer) {
		this.buffer = buffer;
		return this;
	}

}
