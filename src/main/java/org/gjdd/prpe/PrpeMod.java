package org.gjdd.prpe;

import net.fabricmc.api.ModInitializer;
import org.gjdd.prpe.component.Components;

public final class PrpeMod implements ModInitializer {
    @Override
    public void onInitialize() {
        Components.init();
    }
}
