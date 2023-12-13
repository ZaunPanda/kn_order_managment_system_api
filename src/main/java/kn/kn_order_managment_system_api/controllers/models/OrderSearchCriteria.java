package kn.kn_order_managment_system_api.controllers.models;

public class OrderSearchCriteria {
        private String orderId;
        private String customerId;
        private String submissionDate;

        public OrderSearchCriteria() {
        }

        public OrderSearchCriteria(String orderId, String customerId, String submissionDate) {
            this.orderId = orderId;
                this.customerId = customerId;
            this.submissionDate = submissionDate;
        }

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getCustomerId() {
            return customerId;
        }

        public void setCustomerId(String customerId) {
            this.customerId = customerId;
        }

        public String getSubmissionDate() {
            return submissionDate;
        }

        public void setSubmissionDate(String submissionDate) {
            this.submissionDate = submissionDate;
        }
    }
