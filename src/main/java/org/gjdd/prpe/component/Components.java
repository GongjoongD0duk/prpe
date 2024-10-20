package org.gjdd.prpe.component;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import eu.pb4.polymer.resourcepack.api.PolymerResourcePackUtils;
import eu.pb4.polymer.resourcepack.api.ResourcePackBuilder;
import org.jetbrains.annotations.ApiStatus;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * {@link Component}를 관리하는 객체입니다.
 */
public final class Components {
    private static final Map<ShifterConfig, Shifter> configToShifter = new HashMap<>();
    private static final Map<ImageConfig, Image> configToImage = new HashMap<>();

    private static int index = 0;

    static {
        for (var offset = -2048; offset <= 2048; offset++) {
            configToShifter.computeIfAbsent(new ShifterConfig(offset), key -> new Shifter(key, generateKey()));
        }

        for (var offset = -1.0; offset <= 1.0; offset += 0.001) {
            configToShifter.computeIfAbsent(new ShifterConfig(offset), key -> new Shifter(key, generateKey()));
        }
    }

    /**
     * 주어진 오프셋에 해당하는 쉬프터를 반환합니다.
     *
     * @param offset 오프셋
     * @return 쉬프터 객체
     */
    public static Shifter shifterOf(double offset) {
        return shifterOf(new ShifterConfig(offset));
    }

    /**
     * 주어진 컨피그에 해당하는 쉬프터를 반환합니다.
     *
     * @param config 쉬프터 컨피그 객체
     * @return 쉬프터 객체
     */
    public static Shifter shifterOf(ShifterConfig config) {
        double offset = config.offset();
        if (offset < -2048.0 || offset > 2048.0) {
            throw new IllegalArgumentException("Offset out of bounds");
        }

        var shifter = configToShifter.get(config);
        if (shifter != null) {
            return shifter;
        }

        var integer = (int) offset;
        var decimal = offset - integer;
        return new Shifter(
                config,
                configToShifter.get(new ShifterConfig(integer)).string +
                        configToShifter.get(new ShifterConfig(decimal)).string
        );
    }

    /**
     * 주어진 컨피그에 해당하는 이미지를 반환합니다.
     *
     * @param config 이미지 컨피그 객체
     * @return 이미지 객체
     */
    public static Image imageOf(ImageConfig config) {
        return configToImage.computeIfAbsent(config, key -> new Image(key, generateKey()));
    }

    @ApiStatus.Internal
    public static void init() {
        PolymerResourcePackUtils.addModAssets("prpe");
        PolymerResourcePackUtils.RESOURCE_PACK_CREATION_EVENT.register(Components::build);
    }

    private static void build(ResourcePackBuilder builder) {
        var advances = new JsonObject();
        configToShifter.forEach((config, shifter) ->
                advances.addProperty(
                        shifter.string,
                        config.offset()
                )
        );

        var space = new JsonObject();
        space.addProperty("type", "space");
        space.add("advances", advances);

        var images = new JsonArray();
        configToImage.forEach((config, image) -> {
            var chars = new JsonArray();
            chars.add(image.string);

            var provider = new JsonObject();
            provider.addProperty("type", "bitmap");
            provider.addProperty("file", config.file().toString());
            provider.addProperty("ascent", config.ascent());
            provider.addProperty("height", config.height());
            provider.add("chars", chars);

            images.add(provider);
        });

        var providers = new JsonArray();
        providers.add(space);
        providers.addAll(images);

        var font = new JsonObject();
        font.add("providers", providers);

        var data = new Gson().toJson(font).getBytes(StandardCharsets.UTF_8);
        builder.addData("assets/minecraft/font/default.json", data);
    }

    private static String generateKey() {
        return String.valueOf((char) (0xDB80 + (index >>> 7))) +
                (char) (0xDF80 + (index++ & 127));
    }
}
