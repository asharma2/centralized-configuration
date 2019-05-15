package org.gomiles.rnd.web;

import java.util.List;

import org.gomiles.exception.GoMilesException;
import org.gomiles.rnd.influxdb.dto.QMemoryPoint;
import org.gomiles.rnd.influxdb.model.MemoryPoint;
import org.gomiles.rnd.influxdb.operations.InfluxDBTemplate;
import org.influxdb.dto.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/memory")
public class MemoryPointController {

	@Autowired
	InfluxDBTemplate<MemoryPoint> influxDBTemplate;

	@PostMapping("/")
	@ResponseBody
	public MemoryPoint save(@RequestBody QMemoryPoint qMemoryPoint) throws GoMilesException {
		influxDBTemplate.createDatabase();
		influxDBTemplate.write(qMemoryPoint.toMemoryPoint());
		return qMemoryPoint.toMemoryPoint();
	}

	@GetMapping("/")
	@ResponseBody
	public List<MemoryPoint> get() throws GoMilesException {
		return influxDBTemplate.queryForList(new Query("SELECT * FROM memory"));
	}

}
