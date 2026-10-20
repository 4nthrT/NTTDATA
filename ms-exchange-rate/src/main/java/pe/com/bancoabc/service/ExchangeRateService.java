package pe.com.bancoabc.exchangerate.service;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import pe.com.bancoabc.exchangerate.entity.ExchangeRateQuery;
import pe.com.bancoabc.exchangerate.exception.DailyLimitExceededException;
import pe.com.bancoabc.exchangerate.proxy.ExchangeRateApiClient;
import pe.com.bancoabc.exchangerate.proxy.ExchangeRateProxy;
import pe.com.bancoabc.exchangerate.repository.ExchangeRateRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import org.jboss.logging.Logger;

@ApplicationScoped
public class ExchangeRateService {

    private static final Logger LOG =
            Logger.getLogger(ExchangeRateService.class);

    private static final ZoneId ZONE =
            ZoneId.of("America/Lima");

    @Inject
    ExchangeRateRepository repository;

    @Inject
    @RestClient
    ExchangeRateApiClient apiClient;

    @ConfigProperty(
            name = "exchange-rate.daily-limit",
            defaultValue = "10"
    )
    int dailyLimit;

    @Transactional
    public ExchangeRateProxy getRate(String dni) {

        LocalDate today = LocalDate.now(ZONE);

        LocalDateTime start = today.atStartOfDay();
        LocalDateTime end = today.plusDays(1).atStartOfDay();

        long count = repository.countToday(dni, start, end);

        if (count >= dailyLimit) {
            LOG.warn("Límite diario de consultas alcanzado");

            throw new DailyLimitExceededException(
                    "El cliente alcanzó el límite diario de consultas"
            );
        }

        ExchangeRateProxy rate = apiClient.getTodayRate();

        ExchangeRateQuery query = new ExchangeRateQuery();
        query.dni = dni;
        query.consultedAt = LocalDateTime.now(ZONE);
        query.success = true;

        repository.saveQuery(query);

        LOG.info("Consulta de tipo de cambio completada");

        return rate;
    }
}