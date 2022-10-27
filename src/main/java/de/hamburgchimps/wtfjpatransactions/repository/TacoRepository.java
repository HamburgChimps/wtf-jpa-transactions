package de.hamburgchimps.wtfjpatransactions.repository;

import de.hamburgchimps.wtfjpatransactions.entity.Taco;
import de.hamburgchimps.wtfjpatransactions.type.TacoType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TacoRepository extends JpaRepository<Taco, Long> {
    List<Taco> findByTypeNot(TacoType type);
}
