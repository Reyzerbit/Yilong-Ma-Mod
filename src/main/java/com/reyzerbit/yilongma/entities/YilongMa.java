package com.reyzerbit.yilongma.entities;

import java.util.EnumSet;

import com.reyzerbit.yilongma.items.YilongItems;
import com.reyzerbit.yilongma.sounds.YilongSounds;

import lombok.Getter;
import lombok.Setter;
import net.minecraft.core.BlockPos;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.goal.InteractGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.MoveTowardsRestrictionGoal;
import net.minecraft.world.entity.ai.goal.PanicGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.monster.Evoker;
import net.minecraft.world.entity.monster.Illusioner;
import net.minecraft.world.entity.monster.Pillager;
import net.minecraft.world.entity.monster.Vex;
import net.minecraft.world.entity.monster.Vindicator;
import net.minecraft.world.entity.monster.Zoglin;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.Merchant;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class YilongMa extends PathfinderMob implements Merchant
{
	public static final String ENTITY_ID = "yilongma";
    @Getter @Setter private BlockPos wanderTarget;
    @Getter @Setter private Player tradingPlayer;
    public boolean isTrading() { return tradingPlayer != null; }
    
    private final ItemStack monkeyNFT;
    
	public static AttributeSupplier.Builder createAttributes() {	return Animal.createLivingAttributes()
		.add(Attributes.MAX_HEALTH, 20D)
		.add(Attributes.FOLLOW_RANGE, 24D)
		.add(Attributes.MOVEMENT_SPEED, 2.5D);
	}
	
	public YilongMa(EntityType<? extends YilongMa> entity, Level level)
	{
		super(entity, level);
		
		setCustomName(Component.literal("Yilong Ma"));
		setCustomNameVisible(true);
		
		monkeyNFT = new ItemStack(Items.PAINTING, 1);
		monkeyNFT.set(DataComponents.CUSTOM_NAME, Component.literal("Monkey NFT"));
	}
	
	@Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new TradeWithPlayerGoal(this));
        this.goalSelector.addGoal(1, new LookAtTradingPlayerGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zombie.class, 8.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Evoker.class, 12.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vindicator.class, 8.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vex.class, 8.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Pillager.class, 15.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Illusioner.class, 12.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zoglin.class, 10.0F, 0.5, 0.5));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.5));
        this.goalSelector.addGoal(2, new YilongMa.WanderToPositionGoal(this, 2.0, 0.35));
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 0.35));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.35));
        this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand)
    {
        if (this.isAlive() && !this.isBaby())
        {
            if (!this.level().isClientSide)
            {
                this.setTradingPlayer(player);
                this.openTradingScreen(player, this.getDisplayName(), 1);
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide);
        }
        else { return super.mobInteract(player, hand); }
    }

	@Override
	public MerchantOffers getOffers()
	{
		MerchantOffers yilongOffers = new MerchantOffers();
		
		yilongOffers.add(new MerchantOffer(new ItemCost(YilongItems.DOGE_COIN, 1), new ItemStack(Items.EMERALD, 10), Integer.MAX_VALUE, 0, 0f));
		yilongOffers.add(new MerchantOffer(new ItemCost(YilongItems.DOGE_COIN, 1), monkeyNFT, Integer.MAX_VALUE, 0, 0f));
		
		return yilongOffers;
	}

	@Override
	public void overrideOffers(MerchantOffers pOffers) { /*Dont allow offer override*/ }

	@Override
	public void notifyTrade(MerchantOffer pOffer) {}

	@Override
	public void notifyTradeUpdated(ItemStack pStack) {}

	// Yilong Ma doesn't have trade XP
	@Override public int getVillagerXp() { return 0; }
	@Override public void overrideXp(int pXp) {}
	@Override public boolean showProgressBar() { return false; }

	@Override
	public boolean isClientSide() { return this.level().isClientSide; }
	
	@Override
	public SoundEvent getNotifyTradeSound() { return YilongSounds.YILONG_TRADE_SOUND_EVENT.get(); }
	
	@Override
    protected SoundEvent getAmbientSound() { return YilongSounds.YILONG_PASSIVE_SOUND_EVENT.get(); }
	
	class WanderToPositionGoal extends Goal
	{
        final YilongMa yilong;
        final double stopDistance;
        final double speedModifier;

        WanderToPositionGoal(YilongMa yilong, double pStopDistance, double pSpeedModifier) {
            this.yilong = yilong;
            this.stopDistance = pStopDistance;
            this.speedModifier = pSpeedModifier;
            this.setFlags(EnumSet.of(Goal.Flag.MOVE));
        }

        @Override
        public void stop() {
            this.yilong.setWanderTarget(null);
            YilongMa.this.navigation.stop();
        }

        @Override
        public boolean canUse() {
            BlockPos blockpos = this.yilong.getWanderTarget();
            return blockpos != null && this.isTooFarAway(blockpos, this.stopDistance);
        }

        @Override
        public void tick() {
            BlockPos blockpos = this.yilong.getWanderTarget();
            if (blockpos != null && YilongMa.this.navigation.isDone()) {
                if (this.isTooFarAway(blockpos, 10.0)) {
                    Vec3 vec3 = new Vec3(
                            (double)blockpos.getX() - this.yilong.getX(),
                            (double)blockpos.getY() - this.yilong.getY(),
                            (double)blockpos.getZ() - this.yilong.getZ()
                        )
                        .normalize();
                    Vec3 vec31 = vec3.scale(10.0).add(this.yilong.getX(), this.yilong.getY(), this.yilong.getZ());
                    YilongMa.this.navigation.moveTo(vec31.x, vec31.y, vec31.z, this.speedModifier);
                } else {
                	YilongMa.this.navigation.moveTo((double)blockpos.getX(), (double)blockpos.getY(), (double)blockpos.getZ(), this.speedModifier);
                }
            }
        }

        private boolean isTooFarAway(BlockPos pPos, double pDistance) {
            return !pPos.closerToCenterThan(this.yilong.position(), pDistance);
        }
    }
	
	class TradeWithPlayerGoal extends Goal {
	    private final YilongMa mob;

	    public TradeWithPlayerGoal(YilongMa pMob) {
	        this.mob = pMob;
	        this.setFlags(EnumSet.of(Goal.Flag.JUMP, Goal.Flag.MOVE));
	    }

	    @Override
	    public boolean canUse() {
	        if (!this.mob.isAlive()) {
	            return false;
	        } else if (this.mob.isInWater()) {
	            return false;
	        } else if (!this.mob.onGround()) {
	            return false;
	        } else if (this.mob.hurtMarked) {
	            return false;
	        } else {
	            Player player = this.mob.getTradingPlayer();
	            if (player == null) {
	                return false;
	            } else {
	                return this.mob.distanceToSqr(player) > 16.0 ? false : player.containerMenu != null;
	            }
	        }
	    }

	    @Override
	    public void start() {
	        this.mob.getNavigation().stop();
	    }

	    @Override
	    public void stop() {
	        this.mob.setTradingPlayer(null);
	    }
	}
	
	class LookAtTradingPlayerGoal extends LookAtPlayerGoal {
	    private final YilongMa villager;

	    public LookAtTradingPlayerGoal(YilongMa pVillager) {
	        super(pVillager, Player.class, 8.0F);
	        this.villager = pVillager;
	    }

	    @Override
	    public boolean canUse() {
	        if (this.villager.isTrading()) {
	            this.lookAt = this.villager.getTradingPlayer();
	            return true;
	        } else {
	            return false;
	        }
	    }
	}
}
