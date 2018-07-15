package demo.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by p51 on 2018/5/29.
 */
@Controller
public class WebPageController {

    @RequestMapping(value = {"/wenanPart/formManage"}, method = RequestMethod.GET)
    public String fromManage() {
        return "/wenanPart/formManage";
    }

    @RequestMapping(value = {"/wenanPart/orderTable"}, method = RequestMethod.GET)
    public String orderTable() {
        return "/wenanPart/orderTable";
    }

    @RequestMapping(value = {"/wenanPart/orderFormAdd"}, method = RequestMethod.GET)
    public String orderAdd() {
        return "/wenanPart/orderFormAdd";
    }

    @RequestMapping(value = {"/wenanPart/workPay"}, method = RequestMethod.GET)
    public String workPay() {
        return "/wenanPart/workPay";
    }

    @RequestMapping(value = {"/wenanPart/workPayAdd"}, method = RequestMethod.GET)
    public String workPayAdd() {
        return "/wenanPart/workPayAdd";
    }

    @RequestMapping(value = {"/widget/audit"}, method = RequestMethod.GET)
    public String audit() {
        return "/widget/audit";
    }

    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public String test() {
        return "/test";
    }
}
