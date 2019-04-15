package com.baymin.restroomapi.dao.specs;

import com.baymin.restroomapi.entity.Blacklist;
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
public class BlacklistSpecs {

    public Specification<Blacklist> listSpecsIni(Blacklist conditions) {
        return new Specification<Blacklist>() {
            @Override
            public Predicate toPredicate(Root<Blacklist> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                Optional.ofNullable(conditions.getText()).ifPresent(v->list.add(cb.like(root.get("text"), "%"+conditions.getText()+"%")));
                Optional.ofNullable(conditions.getUserNick()).ifPresent(v->list.add(cb.like(root.get("userNick"), "%"+conditions.getText()+"%")));
                return cb.or(list.toArray(new Predicate[list.size()]));
            }

        };
    }

}
