package group2JP2.dao.impls;

import group2JP2.dao.interfaces.IOrderTicketRepository;
import group2JP2.entities.OrderTicket;
import group2JP2.helper.Connector;

import java.sql.ResultSet;
import java.util.ArrayList;

public class OrderTicketRepository implements IOrderTicketRepository {
    @Override
    public ArrayList<OrderTicket> all() {
        ArrayList<OrderTicket> ls = new ArrayList<>();
        try{
            String sql ="select * from orderticket";
            Connector conn = Connector.getInstance();
            ResultSet rs = conn.query(sql);
            while (rs.next()){
                ls.add(new OrderTicket(
                        rs.getInt("oid"),
                        rs.getInt("qtyticket"),
                        rs.getInt("totalmoney")
                ));
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return ls;
    }

    @Override
    public boolean create(OrderTicket orderTicket) {
      try{
          String sql = "insert into orderticket(oid,qtyticket,totalmoney) values(?,?,?)";
          Connector conn = Connector.getInstance();
          ArrayList arr = new ArrayList();
          arr.add(orderTicket.getId());
          arr.add(orderTicket.getQty());
          arr.add(orderTicket.getTotalMoney());
          if(conn.execute(sql,arr)){
              return true;
          }

      }catch (Exception e){
          System.out.println(e.getMessage());
      }
      return false;

    }

    @Override
    public boolean update(OrderTicket orderTicket) {
        try{
            String sql ="update orderticket set qtyticket =?,totalmoney =? where oid =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList();
            arr.add(orderTicket.getQty());
            arr.add(orderTicket.getTotalMoney());
            arr.add(orderTicket.getId());
            if(conn.execute(sql,arr)){
                return true;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(OrderTicket orderTicket) {
        try{
            String sql ="delete from orderticket where oid =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList();
            arr.add(orderTicket.getId());
            if(conn.execute(sql,arr)){
                return true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return false;
    }

    @Override
    public OrderTicket findOne(Integer id) {
        try{
            String sql= "select * from orderticket where oid =?";
            Connector conn = Connector.getInstance();
            ArrayList arr = new ArrayList();
            arr.add(id);
            ResultSet rs = conn.executeQuery(sql,arr);
            while (rs.next()){
                return new OrderTicket(
                        rs.getInt("oid"),
                        rs.getInt("qtyseat"),
                        rs.getInt("totalmoney")
                );
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
