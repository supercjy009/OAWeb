package demo.service;

import com.github.pagehelper.PageInfo;
import demo.dto.*;
import demo.model.OrderEntity;

import java.text.ParseException;
import java.util.List;

/**
 * Created by p51 on 2018/5/16.
 */
public interface OrderService {
    PageInfo<PartTimeOrderRes> queryAllOrder(OrderReqVo vo) throws ParseException;

    int addOrder(OrderVo order);

    int updateOrder(OrderVo order);

    int auditOrder(AuditVo vo);

    int appointPart(AppointPartVo vo);

    int deletePart(AppointPartVo vo);

    int editPart(AppointPartVo vo);
}
