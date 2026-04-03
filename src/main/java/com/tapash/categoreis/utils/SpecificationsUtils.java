package com.tapash.categoreis.utils;

import com.tapash.categoreis.category_dtos.FilterDto;
import com.tapash.entity.category.CakeCategory;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SpecificationsUtils {
    public static Specification<CakeCategory> search(FilterDto dto){
        return (root,query,cb)->{
            List<Predicate>predicateList=new ArrayList<>();
            if(dto.getSearch()!=null && !dto.getSearch().isBlank()){
                String like="%"+dto.getSearch().toLowerCase()+"%";
                predicateList.add(cb.or(
                        cb.like(cb.lower(root.get("categoryName")),like),
                        cb.like(cb.lower(root.get("description")),like))
                );
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        };
    }
    public static Specification<CakeCategory> searchListOfTitle(String titles){
        return ((root, query, cb) -> {
          List<Predicate>predicateList=new ArrayList<>();
            if(titles!=null && !titles.isBlank()){
                String like="%"+titles.toLowerCase()+"%";
                predicateList.add(cb.like(cb.lower(root.get("categoryName")),like));
            }
            return cb.and(predicateList.toArray(new Predicate[0]));
        }
        );
    }
    public static Specification<CakeCategory> searchbyname(String titles){
        return ((root, query, cb) -> {

            if(titles!=null && !titles.isBlank()){
                String like="%"+titles.toLowerCase()+"%";
                return cb.like(cb.lower(root.get("categoryName")),like);
            }
            return cb.conjunction();// return true predicate if no filter
        }
        );
    }
}
