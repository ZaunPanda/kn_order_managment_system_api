package kn.kn_order_managment_system_api.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductDTO {
    private int productId;
    private String productName;
    private String skuCode;
    private Integer unitPrice;

    public ProductDTO() {
    }

    public ProductDTO(int productId, String productName, String skuCode, Integer unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.skuCode = skuCode;
        this.unitPrice = unitPrice;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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

    public Integer getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Integer unitPrice) {
        this.unitPrice = unitPrice;
    }
}
