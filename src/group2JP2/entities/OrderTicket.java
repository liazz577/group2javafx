package group2JP2.entities;

import javafx.scene.control.Button;

public class OrderTicket {
    public Integer id;
    public Integer qty;
    public Float totalMoney;
//    public Button choose;

    public OrderTicket() {
    }

    public OrderTicket(Integer id, Integer qty, Float totalMoney) {
        this.id = id;
        this.qty = qty;
        this.totalMoney = totalMoney;
//        this.choose = new Button("Edit");
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

//    public Button getChoose() {
//        return choose;
//    }
}
