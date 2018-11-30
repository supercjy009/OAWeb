package demo.service;

import com.github.pagehelper.PageInfo;
import demo.model.dto.PartOrderReqVo;
import demo.model.PartTimeEntity;
import demo.model.dto.PartTimeDto;

/**
 * Created by p51 on 2018/5/16.
 */
public interface PartTimeService {
    PageInfo<PartTimeDto> queryAllOrder(PartOrderReqVo vo);

    int addOrder(PartTimeEntity order);

    int updateOrder(PartTimeEntity order);

}
