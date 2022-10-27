package de.hamburgchimps.wtfjpatransactions.service;

import de.hamburgchimps.wtfjpatransactions.repository.TacoRepository;
import de.hamburgchimps.wtfjpatransactions.type.TacoType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TacoService {
    private final TacoRepository repository;

    Logger log = LoggerFactory.getLogger(TacoService.class);

    public TacoService(TacoRepository repository) {
        this.repository = repository;
    }

    public void changeToBeef(List<Long> ids) {
        var tacos = repository.findAllById(ids);

        if (tacos.isEmpty()) {
            log.debug("no tacos to change");
        }

        for (var t: tacos) {
            log.debug("changing type for taco: {}", t);
            t.setType(TacoType.BEEF);
        }
        repository.saveAll(tacos);
        repository.flush();
    }
}
