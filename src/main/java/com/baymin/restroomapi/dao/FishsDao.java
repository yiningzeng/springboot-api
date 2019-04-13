package com.baymin.restroomapi.dao;

import com.baymin.restroomapi.entity.Fishs;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Created by baymin on 17-8-7.
 */
public interface FishsDao extends JpaRepository<Fishs, Integer>,JpaSpecificationExecutor<Fishs> {


    Optional<Fishs> findById(String id);

    void deleteById(String id);

    Page<Fishs> findAllByAppointKey_Id(Integer id, Pageable pageable);
    Page<Fishs> findAllByAppointKey_IdAndAppointKeyStatus(Integer id, Integer status, Pageable pageable);

    Page<Fishs> findAllByAppointKeyStatus(Integer status, Pageable pageable);

}
