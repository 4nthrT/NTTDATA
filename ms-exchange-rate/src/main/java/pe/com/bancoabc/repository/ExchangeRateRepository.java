package pe.com.bancoabc.exchangerate.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

import pe.com.bancoabc.exchangerate.entity.ExchangeRateQuery;

import java.time.LocalDateTime;

@ApplicationScoped
public class ExchangeRateRepository
        implements PanacheRepository<ExchangeRateQuery> {

    public long countToday(
            String dni,
            LocalDateTime start,
            LocalDateTime end) {

        return count(
                "dni = ?1 and consultedAt >= ?2 and consultedAt < ?3",
                dni, start, end
        );
    }

    public void saveQuery(ExchangeRateQuery query) {
        persist(query);
    }
}