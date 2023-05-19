package com.longcode.colorRecogniser.services.modelServices;

import com.longcode.colorRecogniser.models.BaseModel;
import com.longcode.colorRecogniser.models.requests.SearchRequest;
import com.longcode.colorRecogniser.models.shallowModels.SearchSpecification;
import com.longcode.colorRecogniser.repositories.BaseModelRepository;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;

@Getter
@RequiredArgsConstructor
public abstract class BaseModelService<T extends BaseModel> {
    private final BaseModelRepository<T> baseModelRepository;

    // Methods
    public T findById(long id) {
        return baseModelRepository.findById(id).orElseThrow();
    }

    public Page<T> search(SearchRequest searchRequest) {
        SearchSpecification<T> specification = new SearchSpecification<>(searchRequest);
        Pageable pageable = SearchSpecification.getPageable(searchRequest.getPage(), searchRequest.getSize());
        return baseModelRepository.findAll(specification, pageable);
    }

    @Transactional
    public T insert(@Valid T model) {
        model.setId(null);
        return baseModelRepository.save(model);
    }

    @Transactional
    public T update(@Valid T model) {
        if (!baseModelRepository.existsById(model.getId()))
            throw new NoSuchElementException();

        return baseModelRepository.save(model);
    }

    @Transactional
    public void deleteById(long id) {
        baseModelRepository.deleteById(id);
    }
}
