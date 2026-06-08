    package com.beautybook.model.binding;

    import jakarta.validation.constraints.NotNull;

    import java.time.LocalDateTime;
    import java.util.UUID;

    public class AppointmentBindingModel {

        @NotNull(message = "Appointment date is required")
        private LocalDateTime appointmentDateTime;

        @NotNull(message = "Client is required")
        private UUID clientId;

        @NotNull(message = "Hairdresser is required")
        private UUID hairdresserId;

        @NotNull(message = "Service is required")
        private UUID serviceId;

        public LocalDateTime getAppointmentDateTime() {
            return appointmentDateTime;
        }

        public AppointmentBindingModel setAppointmentDateTime(LocalDateTime appointmentDateTime) {
            this.appointmentDateTime = appointmentDateTime;
            return this;
        }

        public UUID getClientId() {
            return clientId;
        }

        public AppointmentBindingModel setClientId(UUID clientId) {
            this.clientId = clientId;
            return this;
        }

        public UUID getHairdresserId() {
            return hairdresserId;
        }

        public AppointmentBindingModel setHairdresserId(UUID hairdresserId) {
            this.hairdresserId = hairdresserId;
            return this;
        }

        public UUID getServiceId() {
            return serviceId;
        }

        public AppointmentBindingModel setServiceId(UUID serviceId) {
            this.serviceId = serviceId;
            return this;
        }
    }