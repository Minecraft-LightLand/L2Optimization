package dev.xkmc.l2optimization.mixin.entity;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.network.syncher.SynchedEntityData;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.concurrent.locks.Lock;

@Mixin(SynchedEntityData.class)
public class SynchedEntityDataMixin {

	@WrapOperation(method = "getItem", at = @At(value = "INVOKE", target = "Ljava/util/concurrent/locks/Lock;lock()V"))
	private void l2optimization$skipLock(Lock instance, Operation<Void> original) {
	}

	@WrapOperation(method = "getItem", at = @At(value = "INVOKE", target = "Ljava/util/concurrent/locks/Lock;unlock()V"))
	private void l2optimization$skipUnlock(Lock instance, Operation<Void> original) {

	}

}
