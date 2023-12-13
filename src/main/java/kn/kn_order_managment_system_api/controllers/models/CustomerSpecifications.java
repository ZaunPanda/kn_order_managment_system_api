package kn.kn_order_managment_system_api.controllers.models;

import jakarta.persistence.criteria.Path;
import kn.kn_order_managment_system_api.entity.Customer;
import org.springframework.data.jpa.domain.Specification;

public class CustomerSpecifications {

    public static Specification<Customer> buildSpecification(CustomerSearchCriteria criteria) {
        Specification<Customer> specification = null;

        if (criteria.getFullName() != null) {
            specification = buildLikeSpecification(criteria.getFullName(), "fullName");
        }

        if (criteria.getEmail() != null) {
            Specification<Customer> emailSpecification = buildLikeSpecification(criteria.getEmail(), "email");
            specification = specification != null ? specification.and(emailSpecification) : emailSpecification;
        }

        if (criteria.getTelephone() != null) {
            Specification<Customer> telephoneSpecification = buildLikeSpecification(criteria.getTelephone(), "telephone");
            specification = specification != null ? specification.and(telephoneSpecification) : telephoneSpecification;
        }

        return specification;
    }

    private static Specification<Customer> buildLikeSpecification(String searchCriteria, String field) {
        return (root, query, builder) -> {
            Path<String> path = root.get(field);
            return builder.like(path, "%" + searchCriteria + "%");
        };
    }
}