package kn.kn_order_managment_system_api.OrderController.models;

public class ProductSearchCriteria {
    private String productId;
    private String productName;
    private String skuCode;
    private String unitPrice;

    public ProductSearchCriteria() {
    }

    public ProductSearchCriteria(String productId, String productName, String skuCode, String unitPrice) {
                this.productId = productId;
        this.productName = productName;
        this.skuCode = skuCode;
        this.unitPrice = unitPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }
}
