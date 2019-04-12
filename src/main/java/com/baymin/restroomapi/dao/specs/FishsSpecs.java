package com.baymin.restroomapi.dao.specs;

import com.baymin.restroomapi.entity.AppointKey;
import com.baymin.restroomapi.entity.Fishs;
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
public class FishsSpecs {

    public Specification<Fishs> listSpecsIni(Fishs conditions) {
        return new Specification<Fishs>() {
            @Override
            public Predicate toPredicate(Root<Fishs> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                Optional.ofNullable(conditions.getTitle()).ifPresent(v->list.add(cb.like(root.get("title"), "%"+conditions.getTitle()+"%")));
                Optional.ofNullable(conditions.getDesc()).ifPresent(v->list.add(cb.like(root.get("desc"), "%"+conditions.getDesc()+"%")));
                return cb.or(list.toArray(new Predicate[list.size()]));
            }

        };
    }

}
