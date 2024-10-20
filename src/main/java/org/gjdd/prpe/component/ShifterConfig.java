package org.gjdd.prpe.component;

import java.util.Objects;

/**
 * {@link Shifter}를 생성하는 컨피그입니다.
 */
public final class ShifterConfig {
    private final int value;

    /**
     * 주어진 오프셋에 해당하는 컨피그를 생성합니다.
     *
     * @param offset 오프셋
     */
    public ShifterConfig(double offset) {
        this.value = (int) (offset * 1000.0);
    }

    /**
     * 컨피그의 오프셋 값을 반환합니다.
     *
     * @return 오프셋 값
     */
    public double offset() {
        return value / 1000.0;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (ShifterConfig) obj;
        return Objects.equals(this.value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
