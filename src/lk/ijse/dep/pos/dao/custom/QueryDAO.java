package lk.ijse.dep.pos.dao.custom;

import lk.ijse.dep.pos.dao.SuperDAO;
import lk.ijse.dep.pos.entity.CustomEntity;

import java.util.List;

public interface QueryDAO extends SuperDAO {

    CustomEntity getOrderInfo(int orderId) throws Exception;

    CustomEntity getOrderInfo2(int orderId) throws Exception;

    List<CustomEntity> getOrdersInfo3() throws Exception;

}
