    package kn.kn_order_managment_system_api.controllers.models;

    import jakarta.persistence.criteria.Path;
    import kn.kn_order_managment_system_api.entity.Order;
    import org.springframework.data.jpa.domain.Specification;

    public class OrderSpecifications {

        public static Specification<Order> buildSpecification(OrderSearchCriteria criteria) {
            Specification<Order> specification = null;

            if (criteria.getOrderId() != null) {
                specification = buildLikeSpecification(criteria.getOrderId(), "orderId");
            }

            if (criteria.getCustomerId() != null) {
                Specification<Order> idSpecification = buildLikeSpecification(criteria.getCustomerId(), "customerId");
                specification = specification != null ? specification.and(idSpecification) : idSpecification;
            }

            if (criteria.getSubmissionDate() != null) {
                Specification<Order> dateSpecification = buildLikeSpecification(criteria.getSubmissionDate(), "submissionDate");
                specification = specification != null ? specification.and(dateSpecification) : dateSpecification;
            }

            return specification;
        }

        private static Specification<Order> buildLikeSpecification(String searchCriteria, String field) {
            return (root, query, builder) -> {
                Path<String> path = root.get(field);
                return builder.like(path, "" + searchCriteria + "");
            };
        }
    }
