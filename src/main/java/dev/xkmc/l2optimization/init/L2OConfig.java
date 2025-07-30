package dev.xkmc.l2optimization.init;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class L2OConfig {

	public static class Common {

		public final ForgeConfigSpec.BooleanValue doEnderMaskCache;

		public final ForgeConfigSpec.BooleanValue doCrowdReduction;
		public final ForgeConfigSpec.IntValue globalCheckInterval;
		public final ForgeConfigSpec.IntValue crowdSearchInterval;
		public final ForgeConfigSpec.IntValue crowdSearchRange;
		public final ForgeConfigSpec.IntValue maxCrowdToBreed;
		public final ForgeConfigSpec.IntValue crowdAnimalRemoval;
		public final ForgeConfigSpec.IntValue crowdVillagerRemoval;

		public final ForgeConfigSpec.IntValue villagerCheckIntervalFast;
		public final ForgeConfigSpec.IntValue villagerCheckIntervalSlow;
		public final ForgeConfigSpec.IntValue villagerFastCrowd;
		public final ForgeConfigSpec.IntValue villagerSlowCrowd;
		public final ForgeConfigSpec.IntValue villagerFastStayTime;
		public final ForgeConfigSpec.IntValue villagerSlowStayTime;

		Common(ForgeConfigSpec.Builder builder) {
			doEnderMaskCache = builder.comment("Attempt to cache entity-inspecific ender mask")
					.define("doEnderMaskCache", true);
			builder.push("CrowdReduction");
			doCrowdReduction = builder.comment("Attempt to skip passive entity ticks when they are too crowded")
					.define("doCrowdReduction", true);
			globalCheckInterval = builder.comment("Crowd algorithm check interval in ticks")
					.defineInRange("globalCheckInterval", 4, 1, 100);
			crowdSearchInterval = builder.comment("Crowd detection interval in ticks")
					.defineInRange("crowdSearchInterval", 100, 1, 10000);
			crowdSearchRange = builder.comment("Crowd detection range in blocks")
					.defineInRange("crowdSearchRange", 4, 1, 16);
			maxCrowdToBreed = builder.comment("Maximum crowd size to still allow breeding")
					.defineInRange("maxCrowdToBreed", 8, 2, 100);
			crowdAnimalRemoval = builder.comment("When crowd size go above this number, randomly kills animals wuthout name")
							.defineInRange("crowdAnimalRemoval",10,4,1000);
			crowdVillagerRemoval = builder.comment("When crowd size go above this number, randomly kills villagers without name and job")
					.defineInRange("crowdVillagerRemoval",16,4,1000);
			builder.pop();
			builder.push("VillagerBrain");
			villagerCheckIntervalFast = builder.comment("Reduce Villager OneShot AI check to to once per n ticks in Fast Condition")
					.defineInRange("villagerCheckIntervalFast", 5, 1, 100);
			villagerCheckIntervalSlow = builder.comment("Reduce Villager OneShot AI check to to once per n ticks in Slow Condition")
					.defineInRange("villagerCheckIntervalSlow", 20, 1, 100);
			villagerFastCrowd = builder.comment("Villager crowd size for Fast Condition")
					.defineInRange("villagerFastCrowd", 6, 1, 32);
			villagerSlowCrowd = builder.comment("Villager crowd size for Slow Condition")
					.defineInRange("villagerSlowCrowd", 12, 1, 64);
			villagerFastStayTime = builder.comment("Time for Villager to not move to qualify Fast Condition")
					.defineInRange("villagerFastStayTime", 1200, 1, 100000);
			villagerSlowStayTime = builder.comment("Time for Villager to not move to qualify Slow Condition")
					.defineInRange("villagerSlowStayTime", 6000, 1, 100000);
			builder.pop();

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
