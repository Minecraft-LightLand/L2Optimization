package dev.xkmc.l2optimization.mixin.crowd;

import dev.xkmc.l2optimization.api.CrowdHook;
import dev.xkmc.l2optimization.data.AnimalCrowdData;
import dev.xkmc.l2optimization.data.CrowdData;
import dev.xkmc.l2optimization.init.L2OConfig;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Animal.class)
public abstract class AnimalMixin extends AgeableMob implements CrowdHook {

	protected AnimalMixin(EntityType<? extends AgeableMob> p_146738_, Level p_146739_) {
		super(p_146738_, p_146739_);
	}

	@Unique
	private AnimalCrowdData l2optimization$crowdData;

	@Override
	public @NotNull CrowdData l2optimization$getCrowdHook() {
		if (l2optimization$crowdData == null)
			l2optimization$crowdData = new AnimalCrowdData(this);
		return l2optimization$crowdData;
	}

	@Inject(method = "canFallInLove", cancellable = true, at = @At("HEAD"))
	private void l2optimization$canBreed(CallbackInfoReturnable<Boolean> cir) {
		if (!L2OConfig.COMMON.doCrowdReduction.get()) return;
		l2optimization$getCrowdHook();
		if (!l2optimization$crowdData.canBreed())
			cir.setReturnValue(false);
	}

}
