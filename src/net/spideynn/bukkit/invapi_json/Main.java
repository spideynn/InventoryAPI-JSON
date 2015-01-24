package net.spideynn.bukkit.invapi_json;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.material.MaterialData;
import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONObject;

public class Main extends JavaPlugin {

	public void onEnable() {
	}

	public void onDisable() {
	}

	JSONObject obj = new JSONObject();

	@SuppressWarnings({ "unchecked", "deprecation" })
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {
		if (!(sender instanceof Player)
				&& command.getName().equalsIgnoreCase("bukkitguihelp")
				&& args[0].equals("inventory")) {

			PlayerInventory inventory = ((Player) Bukkit.getServer().getPlayer(
					args[1])).getInventory();
			JSONObject mainInv = new JSONObject();

            for (int i=39; i>0; i--) {
                ItemStack stack = inventory.getItem(i);
                JSONObject inv = new JSONObject();
                inv.put("id",Integer.valueOf(i));
             
                if (stack != null) {
                    inv.put("amount",Integer.valueOf(stack.getAmount()));
                    MaterialData stackData = stack.getData();
                    inv.put("type", stack.getTypeId() + "-" + stack.getDurability());
                    inv.put("name", stackData.toString());
                    mainInv.put(Integer.valueOf(i), inv);
                }
                else mainInv.put(Integer.valueOf(i), "null");
            }
			System.out.println(mainInv);
			return true;
		}
		return true;
	}
}
