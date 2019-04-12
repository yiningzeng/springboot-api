package com.baymin.restroomapi.dao;

import com.baymin.restroomapi.entity.AppointKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

/**
 * Created by baymin on 17-8-7.
 */
public interface AppointKeyDao extends JpaRepository<AppointKey, Integer>,JpaSpecificationExecutor<AppointKey> {

}
