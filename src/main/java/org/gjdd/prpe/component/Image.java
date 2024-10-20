package org.gjdd.prpe.component;

import net.minecraft.text.Text;

/**
 * 이미지를 나타내는 컴포넌트입니다.
 */
public final class Image implements Component {

    /**
     * 해당 컴포넌트를 생성한 컨피그입니다.
     */
    public final ImageConfig config;

    /**
     * 해당 컴포넌트의 실제 값입니다.
     */
    public final String string;

    Image(ImageConfig config, String string) {
        this.config = config;
        this.string = string;
    }

    /**
     * 이미지를 렌더합니다.
     *
     * @return 텍스트 객체
     */
    @Override
    public Text render() {
        return Text.literal(string);
    }
}
