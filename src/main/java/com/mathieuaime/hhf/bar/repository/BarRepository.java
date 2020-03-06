package com.mathieuaime.hhf.bar.repository;

import com.mathieuaime.hhf.bar.model.BarDocument;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarRepository extends ReactiveCrudRepository<BarDocument, String> {

}
