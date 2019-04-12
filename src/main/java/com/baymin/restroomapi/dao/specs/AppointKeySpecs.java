package com.baymin.restroomapi.dao.specs;

import com.baymin.restroomapi.entity.AppointKey;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * demo
 * https://www.jianshu.com/p/a57cf5166012
 */
@Component
public class AppointKeySpecs {

    public Specification<AppointKey> listSpecsIni(AppointKey conditions) {
        return new Specification<AppointKey>() {
            @Override
            public Predicate toPredicate(Root<AppointKey> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                Optional.ofNullable(conditions.getKey()).ifPresent(v->list.add(cb.like(root.get("key"), "%"+conditions.getKey()+"%")));
                return cb.or(list.toArray(new Predicate[list.size()]));
            }

        };
    }

}
