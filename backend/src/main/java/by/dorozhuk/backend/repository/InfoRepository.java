package by.dorozhuk.backend.repository;

import by.dorozhuk.backend.entity.Info;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InfoRepository extends CrudRepository<Info, Integer> {
}
