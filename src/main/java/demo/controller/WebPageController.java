package demo.controller;

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
        return "wenanPart/formManage";
    }

    @RequestMapping(value = {"/wenanPart/orderTable"}, method = RequestMethod.GET)
    public String orderTable() {
        return "wenanPart/orderTable";
    }

    @RequestMapping(value = {"/wenanPart/orderFormAdd"}, method = RequestMethod.GET)
    public String orderAdd() {
        return "wenanPart/orderFormAdd";
    }

    //办公支出
    @RequestMapping(value = {"/wenanPart/workPay"}, method = RequestMethod.GET)
    public String workPay() {
        return "wenanPart/workPay";
    }

    @RequestMapping(value = {"/wenanPart/workPayAdd"}, method = RequestMethod.GET)
    public String workPayAdd() {
        return "wenanPart/workPayAdd";
    }

    //主关键词
    @RequestMapping(value = {"/wenanPart/keyWord"}, method = RequestMethod.GET)
    public String keyWord() {
        return "wenanPart/keyWord";
    }

    @RequestMapping(value = {"/wenanPart/keyWordAdd"}, method = RequestMethod.GET)
    public String keyWordAdd() {
        return "wenanPart/keyWordAdd";
    }
    //文件柜
    @RequestMapping(value = {"/wenanPart/fileManage"}, method = RequestMethod.GET)
    public String fileManage() {
        return "wenanPart/fileManage";
    }

    @RequestMapping(value = {"/partTime/partUserTable"}, method = RequestMethod.GET)
    public String partUserTable() {
        return "partTime/partUserTable";
    }

    @RequestMapping(value = {"/partTime/partUserAdd"}, method = RequestMethod.GET)
    public String partUserAdd() {
        return "partTime/partUserAdd";
    }

    @RequestMapping(value = {"/partTime/partOrderTable"}, method = RequestMethod.GET)
    public String partOrderTable() {
        return "partTime/partOrderTable";
    }

    @RequestMapping(value = {"/partTime/partOrderEdit"}, method = RequestMethod.GET)
    public String partOrderEdit() {
        return "partTime/partOrderEdit";
    }

    @RequestMapping(value = {"/partTime/partOrderMoneyEdit"}, method = RequestMethod.GET)
    public String partOrderMoneyEdit() {
        return "partTime/partOrderMoneyEdit";
    }


    @RequestMapping(value = {"/widget/settleDate"}, method = RequestMethod.GET)
    public String settleDate() {
        return "widget/settleDate";
    }

    @RequestMapping(value = {"/widget/audit"}, method = RequestMethod.GET)
    public String audit() {
        return "widget/audit";
    }

    @RequestMapping(value = {"/widget/auditOrder"}, method = RequestMethod.GET)
    public String auditOrder() {
        return "widget/auditOrder";
    }

    @RequestMapping(value = {"/widget/auditPart"}, method = RequestMethod.GET)
    public String auditPart() {
        return "widget/auditPart";
    }

    @RequestMapping(value = {"/widget/addPartTime"}, method = RequestMethod.GET)
    public String addPartTime() {
        return "widget/addPartTime";
    }

    @RequestMapping(value = {"/widget/addPayProgress"}, method = RequestMethod.GET)
    public String addPayProgress() {
        return "widget/addPayProgress";
    }

    @RequestMapping(value = {"/widget/viewPayProgress"}, method = RequestMethod.GET)
    public String viewPayProgress() {
        return "widget/viewPayProgress";
    }

    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public String test() {
        return "test";
    }
}
