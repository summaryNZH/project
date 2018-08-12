package com.project.demo.utils;

/**
 * Created by nzh on 2017/8/1.
 */
public enum EnumUtil {
    /***** 重要订单的command要赋值clientDataId为本地订单号  clientDataVersion为订单的当前client_version
     *     否则上行数据没有顺序
     * *****/
    CASH_ORDER_PAY_1("CASH_ORDER_PAY", "线下订单成单支付"),
    NO_TICKET_REFUND("NO_TICKET_REFUND", "无小票退货"),
    PLACE_PAYCODE_ORDER("PLACE_PAYCODE_ORDER", "线上订单成单"),
    CANCEL_ORDER("CANCEL_ORDER", "取消订单"),
    INVALID_ORDER("INVALID_ORDER", "将订单置为无效"),
    CASH_ORDER_REFUND("CASH_ORDER_REFUND", "线下退货"),
    BOX_OPERATION("BOX_OPERATION", "钱箱数据上行"),;

    private String command;
    private String desc;

    EnumUtil(String command, String desc) {
        this.command = command;
        this.desc = desc;
    }

    public static EnumUtil fromCommand(String command) {
        for (EnumUtil eCommand : values()) {
            if (eCommand.getCommand().equals(command)) {
                return eCommand;
            }
        }

        throw new IllegalArgumentException("no command:" + command);
    }


    public String getCommand() {
        return command;
    }

    public String getDesc() {
        return desc;
    }

}
