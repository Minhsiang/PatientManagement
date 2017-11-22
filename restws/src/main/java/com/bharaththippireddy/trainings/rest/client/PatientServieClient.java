package com.bharaththippireddy.trainings.rest.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.bharaththippireddy.trainings.jaxrs.Patient;

public class PatientServieClient {

	private static final String PATIENT_SERVICE_URL = "http://localhost:8080/restws/services/patientservice";

	public static void main(String[] args) {

		Client client = ClientBuilder.newClient();

		// GET method
		WebTarget target = client.target(PATIENT_SERVICE_URL).path("/patients").path("/{id}").resolveTemplate("id",
				124);
		Patient patient = target.request().get(Patient.class);

		// PUT method
		WebTarget putTarget = client.target(PATIENT_SERVICE_URL).path("/patients");
		patient.setName("James");

		Response updateResponse = putTarget.request().put(Entity.entity(patient, MediaType.APPLICATION_XML));
		System.out.println("PUT method result:" + updateResponse.getStatus());
		updateResponse.close();

		// POST method
		Patient newPatient = new Patient();
		newPatient.setName("Nicky");

		WebTarget postTarget = client.target(PATIENT_SERVICE_URL).path("/patients");
		Patient resultPatient = postTarget.request().post(Entity.entity(newPatient, MediaType.APPLICATION_XML),
				Patient.class);
		System.out.println("POST method result: Patient ID=" + resultPatient.getId());

		// DELETE method
		WebTarget deleteTarget = client.target(PATIENT_SERVICE_URL).path("/patients").path("/{id}")
				.resolveTemplate("id", 123);
		Response deleteResponse = deleteTarget.request().delete();
		System.out.println("DELETE method result:" + deleteResponse.getStatus());
		deleteResponse.close();

		client.close();

	}

}
