package learn.field_agent.domain;

import learn.field_agent.data.SecurityClearanceRepository;
import org.springframework.stereotype.Service;

@Service
public class SecurityClearanceService {
    private final SecurityClearanceRepository repository;


    public SecurityClearanceService(SecurityClearanceRepository repository) {
        this.repository = repository;
    }

    public boolean deleteById(int id) {
        return repository.deleteById(id);
    }
}
