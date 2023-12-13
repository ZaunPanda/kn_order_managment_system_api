package kn.kn_order_managment_system_api.OrderController.models;

import jakarta.persistence.criteria.Path;
import kn.kn_order_managment_system_api.entity.OrderLine;
import org.springframework.data.jpa.domain.Specification;

public class OrderLineSpecifications {
    public static Specification<OrderLine> buildSpecification(kn.kn_order_managment_system_api.OrderController.models.OrderLineSearchCriteria criteria) {
        Specification<OrderLine> specification = null;

        if (criteria.getOrderId() != null) {
            specification = buildLikeSpecification(criteria.getOrderLineId(), "orderId");
        }
        if (criteria.getOrderLineId() != null) {
            specification = buildLikeSpecification(criteria.getOrderLineId(), "orderLineId");
        }

        if (criteria.getProductId() != null) {
            Specification<OrderLine> emailSpecification = buildLikeSpecification(criteria.getProductId(), "productId");
            specification = specification != null ? specification.and(emailSpecification) : emailSpecification;
        }

        if (criteria.getQuantity() != null) {
            Specification<OrderLine> telephoneSpecification = buildLikeSpecification(criteria.getQuantity(), "productId");
            specification = specification != null ? specification.and(telephoneSpecification) : telephoneSpecification;
        }

        return specification;
    }

    private static Specification<OrderLine> buildLikeSpecification(String searchCriteria, String field) {
        return (root, query, builder) -> {
            Path<String> path = root.get(field);
            return builder.like(path, "" + searchCriteria + "");
        };
    }
}