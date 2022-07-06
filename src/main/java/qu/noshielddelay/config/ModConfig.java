package qu.noshielddelay.config;

import com.electronwill.nightconfig.core.ConfigSpec;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import net.fabricmc.loader.api.FabricLoader;
import qu.noshielddelay.NoShieldDelay;

import java.util.Arrays;

public class ModConfig {

    public static final boolean ENABLED;
    public static final int DELAY;

    static {
        NoShieldDelay.LOGGER.info("Reading config file for " + NoShieldDelay.MOD_ID);
        CommentedFileConfig config = CommentedFileConfig.of(FabricLoader.getInstance().getConfigDir().resolve(NoShieldDelay.MOD_ID + ".toml"));
        config.load();
        checkConfig(config);
        ENABLED = config.get("Enabled");
        DELAY = config.get("RaiseTime");
        config.close();
        NoShieldDelay.LOGGER.info("Enabled: " + ENABLED + ", Raise Time: " + DELAY);
    }

    public static void init() {}

    private static void checkConfig(CommentedFileConfig config) {
        ConfigSpec spec = new ConfigSpec();
        spec.defineInList("Enabled", true, Arrays.asList(true, false));
        spec.defineInRange("RaiseTime", 0, 0, 5);
        if (!spec.isCorrect(config)) {
            NoShieldDelay.LOGGER.error("One or more config settings were incorrect, setting to default value(s)");
            config.setComment("Enabled", "This is a Fabric recreation of Revvilo's Responsive Shield's mod. Their mod is exclusively for Forge and I wanted a Fabric one for myself, so here you go.\n" +
                    "Minecraft by default has a hard-coded 5 tick delay between right-clicking with the shield and being able to block.\n" +
                    "Setting RaiseTime to 0 makes shields capable of blocking instantly. Setting it to 5 would be equivalent to vanilla.");
            ConfigSpec.CorrectionListener listener = (action, path, incorrectValue, correctedValue) -> {
                String pathString = String.join(",", path);
                NoShieldDelay.LOGGER.error("Corrected " + pathString + ": was " + incorrectValue + ", is now " + correctedValue);
            };
            spec.correct(config, listener);
            config.save();
        }
    }
}
