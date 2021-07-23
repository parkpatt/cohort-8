package learn.avengers.domain;

import learn.avengers.data.AvengerRepository;
import learn.avengers.data.DataAccessException;
import learn.avengers.models.Avenger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AvengerService {

    private final AvengerRepository repository;

    public AvengerService(AvengerRepository repository) {
        this.repository = repository;
    }

    public List<Avenger> findAll() throws DataAccessException {
        return repository.findAll();
    }
}
