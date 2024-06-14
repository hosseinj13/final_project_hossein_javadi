package base.service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import lombok.RequiredArgsConstructor;
import base.entity.BaseEntity;
import base.repository.BaseRepository;
import exception.NotFoundException;
import java.io.Serializable;
import java.util.Set;

@RequiredArgsConstructor
public class BaseServiceImpl<T extends BaseEntity<ID>,
        ID extends Serializable,
        R extends BaseRepository<T, ID>>
        implements BaseService<T, ID>{

    protected final R repository;
    ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
    Validator validator = validatorFactory.getValidator();

    @Override
    public T saveOrUpdate(T entity) {
        return repository.saveOrUpdate(entity);
    }

    @Override
    public T findById(ID id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new NotFoundException(String.format("entity with %s not found", id)));
    }

    @Override
    public void deleteById(ID id) {
        T foundedEntity = findById(id);
        repository.delete(foundedEntity);
    }

    @Override
    public void delete(T t) throws IllegalStateException {
            repository.delete(t);
    }

    public boolean validate(T entity) {
        Set<ConstraintViolation<T>> violations = validator.validate(entity);
        if (violations.isEmpty())
            return true;
        else {
            System.out.println("Invalid user data found:");
            for (ConstraintViolation<T> violation : violations) {
                System.out.println(violation.getMessage());
            }
        }
        return false;
    }
}
