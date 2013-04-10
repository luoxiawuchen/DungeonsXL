package com.dre.dungeonsxl.signs;

import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import com.dre.dungeonsxl.game.GameWorld;

public class SIGNMsg extends DSign{
	
	public static String name = "Msg";
	public String buildPermissions = "dxl.sign.msg";
	public boolean onDungeonInit = false;
	
	//Variables
	private String msg;
	private boolean initialized;
	
	public SIGNMsg(Sign sign, GameWorld gworld) {
		super(sign, gworld);
	}
	
	@Override
	public boolean check() {
		// TODO Auto-generated method stub
		
		return true;
	}

	@Override
	public void onInit() {
		String lines[] = sign.getLines();
		
		if(lines[1]!=""&&lines[2]!=""){
			String msg = gworld.config.getMsg(p.parseInt(lines[1]),true);
			if(msg!=null){
				this.msg = msg;
				sign.getBlock().setTypeId(0);
			}
		}
		
		initialized = true;
	}

	@Override
	public void onUpdate(int type,boolean powered) {
		if(initialized){
			setPowered(type,powered);
			if(!isDistanceTrigger()){
				if(isPowered()){
					onTrigger();
				}
			}
		}
	}

	@Override
	public void onTrigger() {
		if(initialized){
			for(Player player : gworld.world.getPlayers()){
				p.msg(player, msg);
			}
			
			this.gworld.dSigns.remove(this);
		}
	}
	
	@Override
	public String getPermissions() {
		return buildPermissions;
	}

	@Override
	public boolean isOnDungeonInit() {
		return onDungeonInit;
	}
}
