package dev.xkmc.l2optimization.init;

import dev.xkmc.l2optimization.data.CrowdData;
import net.minecraft.server.level.ServerLevel;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class L2OEventHanders {

	@SubscribeEvent
	public static void onLivingTick(LivingEvent.LivingTickEvent event) {
		if (!L2OConfig.COMMON.doCrowdReduction.get()) return;
		var e = event.getEntity();
		if (!(e.level() instanceof ServerLevel sl)) return;
		int itv = L2OConfig.COMMON.globalCheckInterval.get();
		if (e.tickCount % itv != 0) return;
		var data = CrowdData.of(e);
		if (data == null) return;
		//EntityCountData.of(sl).onTick(data);
		data.tick();
	}

}
