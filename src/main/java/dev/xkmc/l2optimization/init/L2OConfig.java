package dev.xkmc.l2optimization.init;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class L2OConfig {

	public static class Common {

		public final ForgeConfigSpec.BooleanValue doEnderMaskCache;

		Common(ForgeConfigSpec.Builder builder) {
			doEnderMaskCache = builder.comment("Attempt to cache entity-inspecific ender mask")
					.define("doEnderMaskCache", true);
		}

	}

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {

		final Pair<Common, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = common.getRight();
		COMMON = common.getLeft();
	}

	/**
	 * Registers any relevant listeners for config
	 */
	public static void init() {
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
	}


}
