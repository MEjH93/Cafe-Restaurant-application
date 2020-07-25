package com.comtrade.controllerBL;

import com.comtrade.systemOperation.GenericSystemOperation;
import com.comtrade.systemOperation.user.ReturnRolesService;
import com.comtrade.systemOperation.user.ReturnUserService;
import com.comtrade.systemOperation.user.UserLoginService;
import com.comtrade.systemOperation.user.UserSaveService;
import com.comtrade.systemOperationRestaurantTable.ReturnRestaurantTablesService;
import com.comtrade.transfer.TransferClass;

public class ControllerUser implements CommandBase{


    @Override
    public void execute(TransferClass transferClass) {
        GenericSystemOperation genericSystemOperation = null;
        switch (transferClass.getConstBL()){
            case POST:
                genericSystemOperation = new UserSaveService();
                break;
            case GET_LOGIN:
                genericSystemOperation = new UserLoginService();
                break;
            case RETURN_USERS:
                genericSystemOperation = new ReturnUserService();
                break;
            case RETURN_ROLES:
                genericSystemOperation = new ReturnRolesService();
                break;
            case RETURN_RESTAURANT_TABLE:
            	genericSystemOperation = new ReturnRestaurantTablesService();
            	break;
            default:
                break;
        }
        genericSystemOperation.executeSystemOperation(transferClass);
    }


}