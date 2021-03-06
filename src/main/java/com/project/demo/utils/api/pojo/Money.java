package com.project.demo.utils.api.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public final class Money implements Comparable<Money>, Serializable {

    private static final long serialVersionUID = -6657228621542472076L;

    private static final transient RoundingMode DEFAULT_ROUNDING = RoundingMode.HALF_EVEN;

    private static final transient MathContext DEF_MC = MathContext.DECIMAL64;

    private static final transient int PARSE_SCALE = 6;

    public static final Currency DEFAULT_CURRENCY = Currency.CNY;

    /**金额**/
    private BigDecimal amount;
    /**币种**/
    private Currency currency;

    private static BigDecimal createBigDecimalWithDefMC(String amount) {
        return new BigDecimal(amount, DEF_MC);
    }

    public static Money of(BigDecimal amount) {
        return new Money(amount,DEFAULT_CURRENCY);
    }

    public static Money of(BigDecimal amount,Currency currency) {
        return new Money(amount,currency);
    }

    public static Money of(String amount) {
        return new Money(createBigDecimalWithDefMC(amount),DEFAULT_CURRENCY);
    }

    public static Money of(String amount,Currency currency) {
        return new Money(createBigDecimalWithDefMC(amount),currency);
    }

    public static Money zero(Currency currency) {
        return new Money(BigDecimal.ZERO,currency);
    }

    public static Money total(Money... monies) {
        if (monies.length == 0) {
            throw new IllegalArgumentException("Money array must not be empty");
        }
        Money total = null;
        for (int i = 0; i < monies.length; i++) {
            if (monies[i] != null) {
                if (total == null) {
                    total = Money.zero(monies[i].getCurrency());
                }
                total = total.plus(monies[i]);
            }
        }
        return total;
    }

    //just for serialize/deserialize
    private Money() {

    }

    private Money(BigDecimal amount,Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    private Money with(BigDecimal newAmount) {
        if (newAmount.equals(amount)) {
            return this;
        }
        return new Money(newAmount,currency);
    }

    // -----------------------------------------------------------------------

    /**
     * Checks if the amount is zero.
     *
     * @return true if the amount is zero
     */
    @JsonIgnore
    public boolean isZero() {
        return amount.compareTo(BigDecimal.ZERO) == 0;
    }

    /**
     * Checks if the amount is greater than zero.
     *
     * @return true if the amount is greater than zero
     */
    @JsonIgnore
    public boolean isPositive() {
        return amount.compareTo(BigDecimal.ZERO) > 0;
    }

    /**
     * Checks if the amount is zero or greater.
     *
     * @return true if the amount is zero or greater
     */
    @JsonIgnore
    public boolean isPositiveOrZero() {
        return amount.compareTo(BigDecimal.ZERO) >= 0;
    }

    /**
     * Checks if the amount is less than zero.
     *
     * @return true if the amount is less than zero
     */
    @JsonIgnore
    public boolean isNegative() {
        return amount.compareTo(BigDecimal.ZERO) < 0;
    }

    /**
     * 检查 值 <= 0
     *
     * @return true if the amount is zero or less
     */
    @JsonIgnore
    public boolean isNegativeOrZero() {
        return amount.compareTo(BigDecimal.ZERO) <= 0;
    }


    public Money plus(Money... toAdds) {
        BigDecimal total = amount;
        if(toAdds == null){
            return with(total);
        }
        for (Money money : toAdds) {
            if(money == null) continue;
            if(!currency.equals(money.currency)){
                throw new IllegalArgumentException("Money currency not same !");
            }
            total = total.add(money.amount, DEF_MC);
        }
        return with(total);
    }

    public Money plus(BigDecimal amountToAdd) {
        if (amountToAdd.compareTo(BigDecimal.ZERO) == 0) {
            return this;
        }
        BigDecimal newAmount = amount.add(amountToAdd, DEF_MC);
        return with(newAmount);
    }

    public Money plus(double amountToAdd) {
        if (amountToAdd == 0) {
            return this;
        }
        BigDecimal newAmount = amount.add(BigDecimal.valueOf(amountToAdd), DEF_MC);
        return with(newAmount);
    }


    public Money minus(Money... accountMoneys) {
        BigDecimal total = amount;
        for (Money money : accountMoneys) {
            if(!currency.equals(money.currency)){
                throw new IllegalArgumentException("Money currency not same !");
            }
            total = total.subtract(money.amount, DEF_MC);
        }
        return with(total);
    }

    public Money minus(Money moneyToSubtract) {
        return minus(moneyToSubtract.amount);
    }

    public Money minus(BigDecimal amountToSubtract) {
        if (amountToSubtract.compareTo(BigDecimal.ZERO) == 0) {
            return this;
        }
        BigDecimal newAmount = amount.subtract(amountToSubtract, DEF_MC);
        return with(newAmount);
    }

    public Money minus(double amountToSubtract) {
        if (amountToSubtract == 0) {
            return this;
        }
        BigDecimal newAmount = amount.subtract(BigDecimal.valueOf(amountToSubtract), DEF_MC);
        return with(newAmount);
    }

    public Money minusMajor(long amountToSubtract) {
        if (amountToSubtract == 0) {
            return this;
        }
        BigDecimal newAmount = amount.subtract(BigDecimal.valueOf(amountToSubtract), DEF_MC);
        return with(newAmount);
    }

    public Money minusMinor(long amountToSubtract, int scale) {
        if (amountToSubtract == 0) {
            return this;
        }
        BigDecimal newAmount = amount.subtract(BigDecimal.valueOf(amountToSubtract, scale), DEF_MC);
        return with(newAmount);
    }

    public Money multiply(BigDecimal valueToMultiplyBy) {
        if (valueToMultiplyBy.compareTo(BigDecimal.ONE) == 0) {
            return this;
        }
        BigDecimal newAmount = amount.multiply(valueToMultiplyBy, DEF_MC);
        return with(newAmount);
    }

    public Money multiply(double valueToMultiplyBy) {
        if (valueToMultiplyBy == 1) {
            return this;
        }
        BigDecimal newAmount = amount.multiply(BigDecimal.valueOf(valueToMultiplyBy), DEF_MC);
        return with(newAmount);
    }

    public Money multiply(long valueToMultiplyBy) {
        if (valueToMultiplyBy == 1) {
            return this;
        }
        BigDecimal newAmount = amount.multiply(BigDecimal.valueOf(valueToMultiplyBy), DEF_MC);
        return with(newAmount);
    }


    public Money divide(BigDecimal value) {
        if (value.compareTo(BigDecimal.ONE) == 0) {
            return this;
        }
        BigDecimal newAmount = amount.divide(value, DEF_MC);
        return with(newAmount);
    }

    public Money divide(double value) {
        if (value == 1) {
            return this;
        }
        BigDecimal newAmount = amount.divide(BigDecimal.valueOf(value), DEF_MC);
        return with(newAmount);
    }


    public Money divide(long value) {
        if (value == 1) {
            return this;
        }
        BigDecimal newAmount = amount.divide(BigDecimal.valueOf(value), DEF_MC);

        return with(newAmount);
    }


    public Money negate() {
        if (isZero()) {
            return this;
        }
        return with(amount.negate());
    }

    public Money abs() {
        return (isNegative() ? negate() : this);
    }


    public boolean isGreaterThan(Money other) {
        return compareTo(other) > 0;
    }

    public boolean isLessThan(Money other) {
        return compareTo(other) < 0;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public int compareTo(Money o) {
        if(!currency.equals(o.currency)){
            throw new IllegalArgumentException("Money currency not same !");
        }
        return amount.compareTo(o.amount);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof Money) {
            Money otherMoney = (Money) other;
            return amount.compareTo(otherMoney.amount) == 0;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 7 * amount.hashCode();
    }

    public long toStoreValue() {
        return amount.movePointRight(PARSE_SCALE).setScale(0, DEFAULT_ROUNDING).longValue();
    }

    public BigDecimal toStoreDecimal() {
        return amount.setScale(PARSE_SCALE, DEFAULT_ROUNDING);
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

    public String toMoneyString() {
        return amount.setScale(2, DEFAULT_ROUNDING).toString();
    }

    public String toScaleMoneyString() {
        if (amount.scale() > 2) {
            amount = amount.setScale(2, DEFAULT_ROUNDING);
        }
        final StringBuilder sb = new StringBuilder();
        sb.append("{").append("\"amount\":").append(amount);
        sb.append(",\"currency\":").append("\"").append(currency).append("\"").append("}");
        return sb.toString();
    }
}
