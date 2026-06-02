package com.beautybook.service.impl;

import com.beautybook.model.entity.Appointment;
import com.beautybook.model.enums.AppointmentStatus;
import com.beautybook.repository.AppointmentRepository;
import com.beautybook.service.interfaces.AppointmentService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AppointmentServiceImpl implements AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public Appointment createAppointment(Appointment appointment) {

        // бизнес правило: новите записи винаги са PENDING
        appointment.setStatus(AppointmentStatus.PENDING);

        return appointmentRepository.save(appointment);
    }

    @Override
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @Override
    public List<Appointment> getAppointmentsByClientId(UUID clientId) {
        return appointmentRepository.findAllByClientId(clientId);
    }

    @Override
    public Appointment getById(UUID id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
    }

    @Override
    public Appointment cancelAppointment(UUID id) {
        Appointment appointment = getById(id);

        appointment.setStatus(AppointmentStatus.CANCELED);

        return appointmentRepository.save(appointment);
    }

    @Override
    public Appointment updateAppointment(UUID id, Appointment appointment) {
        Appointment existing = getById(id);

        existing.setAppointmentDateTime(appointment.getAppointmentDateTime());
        existing.setClient(appointment.getClient());
        existing.setHairdresser(appointment.getHairdresser());
        existing.setService(appointment.getService());
        existing.setStatus(appointment.getStatus());

        return appointmentRepository.save(existing);
    }
}