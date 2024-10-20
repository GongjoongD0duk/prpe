package org.gjdd.prpe.component;

import net.minecraft.util.Identifier;

/**
 * {@link Image}를 생성하는 컨피그입니다.
 *
 * @param file   이미지 파일의 경로입니다.
 * @param ascent 이미지의 수직 오프셋입니다.
 * @param height 이미지의 크기입니다.
 */
public record ImageConfig(Identifier file, int ascent, int height) {
}
