package dev.xkmc.l2optimization.mixin.crowd;

import dev.xkmc.l2optimization.api.CrowdHook;
import dev.xkmc.l2optimization.data.CrowdData;
import dev.xkmc.l2optimization.data.VillagerCrowdData;
import dev.xkmc.l2optimization.init.L2OConfig;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Villager.class)
public abstract class VillagerMixin extends AbstractVillager implements CrowdHook {

	public VillagerMixin(EntityType<? extends AbstractVillager> p_35267_, Level p_35268_) {
		super(p_35267_, p_35268_);
	}

	@Unique
	private VillagerCrowdData l2optimization$crowdData;

	@Override
	public @NotNull CrowdData l2optimization$getCrowdHook() {
		if (l2optimization$crowdData == null)
			l2optimization$crowdData = new VillagerCrowdData(this);
		return l2optimization$crowdData;
	}

	@Inject(method = "canBreed", cancellable = true, at = @At("HEAD"))
	private void l2optimization$canBreed(CallbackInfoReturnable<Boolean> cir) {
		if (!L2OConfig.COMMON.doCrowdReduction.get()) return;
		l2optimization$getCrowdHook();
		if (!l2optimization$crowdData.canBreed())
			cir.setReturnValue(false);
	}

}
