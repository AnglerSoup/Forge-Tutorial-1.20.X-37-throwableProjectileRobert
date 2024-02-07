package net.anglersoup.tutorialmod.entity.custom;

import net.anglersoup.tutorialmod.entity.ModEntities;
import net.anglersoup.tutorialmod.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;

public class DiceProjectileEntity extends ThrowableItemProjectile {
    public DiceProjectileEntity(EntityType<? extends ThrowableItemProjectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    public DiceProjectileEntity(Level pLevel) {
        super(ModEntities.ROBERT_PROJECTILE.get(), pLevel);
    }

    public DiceProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.ROBERT_PROJECTILE.get(), livingEntity, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.ROBERT.get();
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        if (!this.level().isClientSide()) {
            this.level().broadcastEntityEvent(this, (byte) 3);
            this.level().explode(null, pResult.getBlockPos().getX(), pResult.getBlockPos().getY(), pResult.getBlockPos().getZ(), 30.0f, Level.ExplosionInteraction.BLOCK);
        }

    }
}
