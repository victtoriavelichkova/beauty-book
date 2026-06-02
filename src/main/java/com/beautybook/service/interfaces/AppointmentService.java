package com.beautybook.service.interfaces;

import com.beautybook.model.entity.Appointment;

import java.util.List;
import java.util.UUID;

public interface AppointmentService {

    Appointment createAppointment(Appointment appointment);

    List<Appointment> getAllAppointments();

    List<Appointment> getAppointmentsByClientId(UUID clientId);

    Appointment getById(UUID id);

    Appointment cancelAppointment(UUID id);

    Appointment updateAppointment(UUID id, Appointment appointment);
}