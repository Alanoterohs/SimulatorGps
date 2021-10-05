package backend.sitrack.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import backend.sitrack.service.ReportalCredential;


@RestController
@RequestMapping("/api")
public class ApiGPS {

	private ReportalCredential reportalCredential;
	
	public ApiGPS(ReportalCredential reportalCredential) {
		this.reportalCredential = reportalCredential;
	}
	
	@PutMapping("/startgps")
	public ResponseEntity<?> createSimulation() {
		try {
			System.out.println("hola");
			return ResponseEntity
					.status(HttpStatus.OK)
					.body(reportalCredential.startGps());

		} catch(Exception e) {
			return (ResponseEntity<?>) ResponseEntity.badRequest();
		}
	}
	
}
