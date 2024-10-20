package org.gjdd.prpe.component;

import net.minecraft.text.Text;

/**
 * 리소스팩에 의해 생성된 요소를 나타내는 인터페이스입니다.
 */
public interface Component {

    /**
     * 컴포넌트를 렌더합니다.
     *
     * @return 텍스트 객체
     */
    Text render();
}
