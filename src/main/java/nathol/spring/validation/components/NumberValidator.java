package nathol.spring.validation.components;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

import nathol.spring.validation.base.DoubleValidate;
import nathol.spring.validation.base.FloatValidate;
import nathol.spring.validation.base.IntegerValidate;
import nathol.spring.validation.base.LongValidate;
import nathol.spring.validation.exception.InvalidException;

/**
 * 数值的校验抽象思路
 */
public abstract class NumberValidator<T extends Number> extends Validator<T> {

    protected T min;
    protected T max;
    protected final Set<T> forbids = new HashSet<>();

    /**
     * 传入参数, 参数类型必须为 Number 的子类
     * @param value 值
     */
    public NumberValidator(T value) {
        super(value);
    }

    /**
     * 传入的参数允许带有默认值
     * @param value 值
     * @param defaultValue 默认值
     */
    public NumberValidator(T value, T defaultValue) {
        super(value, defaultValue);
    }

    /**
     * 参阅 {@link Validator}, 此方法重写只为了方便链式调用
     */
    @Override
    public NumberValidator<T> wrapper(Predicate<? super T> wrapper) {
        super.wrapper(wrapper);
        return this;
    }

    /**
     * 参阅 {@link Validator}, 此方法重写只为了方便链式调用
     */
    @Override
    public NumberValidator<T> nullable() {
        super.nullable();
        return this;
    }

    /**
     * 设置如果出错的则会抛出的异常对象
     * @param exception 参阅 {@link InvalidException}
     */
    public NumberValidator<T> except(InvalidException exception) {
        super.except(exception);
        return this;
    }

    /**
     * 校验 value 的最小值, 如果不符合, 则抛出 {@link InvalidException}
     * @param min 值
     */
    public NumberValidator<T> min(T min) {
        this.min = min;
        return this;
    }

    /**
     * 校验 value 的最大值, 如果不符合, 则抛出 {@link InvalidException}
     * @param max 值
     */
    public NumberValidator<T> max(T max) {
        this.max = max;
        return this;
    }

    /**
     * 指定 value 的最大值和最小值, 如果不符合, 则抛出 {@link InvalidException}
     * @param min
     * @param max
     * @return
     */
    public NumberValidator<T> range(T min, T max) {
        this.min = min;
        this.max = max;
        return this;
    }

    /**
     * 禁止 value 等于 ....值
     * @param values 禁止的数值
     */
    @SuppressWarnings("unchecked")
    public NumberValidator<T> forbid(T... values) {
        for (T value : values) {
            this.forbids.add(value);
        }
        return this;
    }

    /**
     * 禁止 value 等于 ... 值
     * @param values 禁止的数值
     */
    public NumberValidator<T> forbid(Collection<T> values) {
        this.forbids.addAll(values);
        return this;
    }

    public static NumberValidator<Integer> of(Integer value) {
        return new IntegerValidate(value);
    }

    public static NumberValidator<Integer> of(Integer value, Integer defaultValue) {
        return new IntegerValidate(value, defaultValue);
    }

    public static NumberValidator<Long> of(Long value) {
        return new LongValidate(value);
    }

    public static NumberValidator<Long> of(Long value, Long defaultValue) {
        return new LongValidate(value, defaultValue);
    }

    public static NumberValidator<Float> of(Float value) {
        return new FloatValidate(value);
    }

    public static NumberValidator<Float> of(Float value, Float defualtValue) {
        return new FloatValidate(value, defualtValue);
    }

    public static NumberValidator<Double> of(Double value) {
        return new DoubleValidate(value);
    }

    public static NumberValidator<Double> of(Double value, Double defaultValue) {
        return new DoubleValidate(value, defaultValue);
    }

}