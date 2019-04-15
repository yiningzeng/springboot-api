package com.baymin.restroomapi.dao;

import com.baymin.restroomapi.entity.Blacklist;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

/**
 * Created by baymin on 17-8-7.
 */
public interface BlacklistDao extends JpaRepository<Blacklist, Integer>,JpaSpecificationExecutor<Blacklist> {

    @Override
    List<Blacklist> findAll(Specification<Blacklist> specification);
}
