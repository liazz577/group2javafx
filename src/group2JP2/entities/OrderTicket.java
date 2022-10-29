package group2JP2.entities;



public class OrderTicket {
   public Integer id;
   public Integer qty;
   public Float totalMoney;

    public OrderTicket() {
    }

    public OrderTicket(Integer id, Integer qty, Float totalMoney) {
        this.id = id;
        this.qty = qty;
        this.totalMoney = totalMoney;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
    }
}
