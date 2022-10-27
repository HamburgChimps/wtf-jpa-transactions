package de.hamburgchimps.wtfjpatransactions;

import de.hamburgchimps.wtfjpatransactions.entity.Taco;
import de.hamburgchimps.wtfjpatransactions.repository.TacoRepository;
import de.hamburgchimps.wtfjpatransactions.service.TacoService;
import de.hamburgchimps.wtfjpatransactions.type.TacoType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.stream.Stream;

@SpringBootApplication
@EnableScheduling
public class WtfJpaTransactionsApplication {
    private final TacoRepository tacoRepository;
    private final TacoService tacoService;

    Logger log = LoggerFactory.getLogger(WtfJpaTransactionsApplication.class);

    public WtfJpaTransactionsApplication(TacoRepository tacoRepository, TacoService tacoService) {
        this.tacoRepository = tacoRepository;
        this.tacoService = tacoService;
    }

    public static void main(String[] args) {
        SpringApplication.run(WtfJpaTransactionsApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        Stream.of(TacoType.values())
                .peek(t -> log.debug("Creating taco of type: {}", t))
                .forEach(t -> tacoRepository.save(new Taco(t)));
    }

    @Scheduled(initialDelay = 1000, fixedDelay = 1000)
    public void changeToBeef() {
        log.debug("starting to change all tacos to beef");
        var tacoIds = tacoRepository
                .findByTypeNot(TacoType.BEEF)
                .stream()
                .map(Taco::getId)
                .toList();

        tacoService.changeToBeef(tacoIds);
    }

}
