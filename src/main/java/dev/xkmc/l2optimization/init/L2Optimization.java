package dev.xkmc.l2optimization.init;

import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(L2Optimization.MODID)
@Mod.EventBusSubscriber(modid = L2Optimization.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class L2Optimization {

	public static final String MODID = "l2optimization";
	public static final Logger LOGGER = LogManager.getLogger();


	public L2Optimization() {
		L2OConfig.init();
	}

	@SubscribeEvent
	public static void registerCaps(RegisterCapabilitiesEvent event) {
	}

}
