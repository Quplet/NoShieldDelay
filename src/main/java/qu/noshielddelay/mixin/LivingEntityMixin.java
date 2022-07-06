package qu.noshielddelay.mixin;

import net.minecraft.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import qu.noshielddelay.config.ModConfig;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

    @ModifyConstant(method = "isBlocking", constant = @Constant(intValue = 5))
    private int setShieldUseDelay(int constant) {
        if (ModConfig.ENABLED) return ModConfig.DELAY;
        return constant;
    }
}
