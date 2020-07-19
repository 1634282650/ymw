package entity;
//订单细节
public class EasybuyOrderDetail {
    private Integer id;//主键
    private Integer orderId;//订单主键
    private Integer productId;//商品主键
    private Integer quantity;//数量
    private float cost;//消费

    public void setCost(float cost) {
        this.cost = cost;
    }

    public float getCost() {
        return cost;
    }

    public Integer getId() {
        return id;

    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }



}
