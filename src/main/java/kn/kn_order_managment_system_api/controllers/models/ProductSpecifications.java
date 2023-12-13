package kn.kn_order_managment_system_api.controllers.models;

import jakarta.persistence.criteria.Path;
import kn.kn_order_managment_system_api.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {
    public static Specification<Product> buildSpecification(ProductSearchCriteria criteria) {
        Specification<Product> specification = null;

        if (criteria.getProductId() != null) {
            specification = buildLikeSpecification(criteria.getProductId(), "productId");
        }

        if (criteria.getProductName() != null) {
            Specification<Product> idSpecification = buildLikeSpecification(criteria.getProductName(), "productName");
            specification = specification != null ? specification.and(idSpecification) : idSpecification;
        }

        if (criteria.getSkuCode() != null) {
            Specification<Product> dateSpecification = buildLikeSpecification(criteria.getSkuCode(), "skuCode");
            specification = specification != null ? specification.and(dateSpecification) : dateSpecification;
        }
        if (criteria.getUnitPrice() != null) {
            Specification<Product> dateSpecification = buildLikeSpecification(criteria.getUnitPrice()   , "unitPrice");
            specification = specification != null ? specification.and(dateSpecification) : dateSpecification;
        }

        return specification;
    }

    private static Specification<Product> buildLikeSpecification(String searchCriteria, String field) {
        return (root, query, builder) -> {
            Path<String> path = root.get(field);
            return builder.like(path, "" + searchCriteria + "");
        };
    }
}
