package demo.model.dto;

import demo.model.PartTimeUser;

import java.text.NumberFormat;

public class PartTimeUserDto extends PartTimeUser {
    private String problemRateStr;

    public String getProblemRateStr() {
        return problemRateStr;
    }

    public void setProblemRateStr(String problemRate) {
        if (problemRate != null) {
            NumberFormat numberFormat = NumberFormat.getInstance();
            // 设置精确到小数点后2位
            numberFormat.setMaximumFractionDigits(0);
            String percent = (numberFormat.format(Float.valueOf(problemRate) * 100)) + "%";
            this.problemRateStr = percent;
        }
    }
}
