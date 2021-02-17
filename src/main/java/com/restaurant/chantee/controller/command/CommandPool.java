package com.restaurant.chantee.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class CommandPool {
    static final Logger LOG = LogManager.getLogger(CommandPool.class);
    private static final Map<String, Command> COMMANDS = new HashMap<>();


    static {
        COMMANDS.put("noCommand", new NoCommand());
        COMMANDS.put("generateProductList", new GenerateProductListCommand());
        COMMANDS.put("registerCommand", new RegisterCommand());
        COMMANDS.put("loginCommand", new LoginCommand());
        COMMANDS.put("addToCartCommand", new AddToCartCommand());
        COMMANDS.put("changeQuantityCommand", new ChangeQuantityCommand());
        COMMANDS.put("removeProductCommand", new RemoveProductCommand());
        COMMANDS.put("takeOrderCommand", new TakeOrderCommand());
        COMMANDS.put("endPurchaseCommand", new EndPurchaseCommand());
        COMMANDS.put("logOutCommand", new LogOutCommand());
        COMMANDS.put("prepareManagerData", new PrepareManagerDataCommand());
        COMMANDS.put("acceptOrderCommand", new AcceptOrderCommand());
        COMMANDS.put("discardOrderCommand", new DiscardOrderCommand());
        COMMANDS.put("toDeliveryCommand", new ToDeliveryCommand());
        COMMANDS.put("orderFinishedCommand", new OrderFinishedCommand());
        COMMANDS.put("previousPageCommand", new PreviousPageCommand());
        COMMANDS.put("nextPageCommand", new NextPageCommand());
        COMMANDS.put("makeReturn", new MakeReturnCommand());

        for ( String s : COMMANDS.keySet()){
            LOG.debug("Loaded command:" + COMMANDS.get(s).getClass().getName());
        }
    }

    public static Command getCommand(String commandName){
        LOG.debug(" Called getCommand(" + commandName + ")");
        if (commandName == null || !COMMANDS.containsKey(commandName)) {
            return COMMANDS.get("noCommand");
        }

        return COMMANDS.get(commandName);
    }

}
