package org.gjdd.prpe.component;

import net.minecraft.text.Text;

/**
 * 컴포넌트의 공간을 조정하는 컴포넌트입니다.
 */
public final class Shifter implements Component {

    /**
     * 해당 컴포넌트를 생성한 컨피그입니다.
     */
    public final ShifterConfig config;

    /**
     * 해당 컴포넌트의 실제 값입니다.
     */
    public final String string;

    Shifter(ShifterConfig config, String string) {
        this.config = config;
        this.string = string;
    }

    /**
     * 쉬프터를 렌더합니다.
     *
     * @return 텍스트 객체
     */
    @Override
    public Text render() {
        return Text.literal(string);
    }
}
